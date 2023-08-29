package com.example.restapiproduct.controller;

import com.example.restapiproduct.entity.Message;
import com.example.restapiproduct.entity.Product;
import com.example.restapiproduct.entity.Response;
import com.example.restapiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(path = "/api/products")
public class ProductController implements Serializable {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("")
    public ResponseEntity<Response<List<Product>>> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(new Response("success","Get all product successfully!",products));
    }
    @GetMapping("/{code}")
    public ResponseEntity<Response<Product>> getProductById(@PathVariable String code){
        Optional<Product> product = productRepository.findByCode(code);
        if (product.isPresent()){
            return ResponseEntity.ok(new Response("success","Get product successfully!",product));
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("")
    public ResponseEntity<Response<Product>> insertProduct(@RequestBody Product product){
        if (productRepository.existsByCode(product.getCode())){
            return ResponseEntity.ok(new Response("failure","This code is already used!",null));
        }
        return ResponseEntity.ok(new Response("success","Add new product successfully!",productRepository.save(product)));
    }
    @PutMapping("/{code}")
    public ResponseEntity<Response<Product>> upadteProduct(@PathVariable String code, @RequestBody Product newProduct){
        Optional<Product> p = productRepository.findByCode(code);
        if (!p.isPresent()){
            return ResponseEntity.ok(new Response("failure","Cann't find the product with this code!",null));
        }
        else{
            Product product = p.get();
            product.setName(newProduct.getName());
            product.setCategory(newProduct.getCategory());
            product.setBrand(newProduct.getBrand());
            product.setType(newProduct.getType());
            product.setDescription(newProduct.getDescription());
            return ResponseEntity.ok(new Response("success","Save product successfully!",productRepository.save(product)));
        }
    }
    @DeleteMapping("/{code}")
    public ResponseEntity<Response<Product>> deleteProduct(@PathVariable String code){
        boolean isExist = productRepository.existsByCode(code);
        if (isExist){
            productRepository.deleteByCode(code);
            return ResponseEntity.ok(new Response("success","Delete product successfully!",null));
        }
        else {
            return ResponseEntity.ok(new Response("success","Cann't find this product!",null));
        }
    }

}
