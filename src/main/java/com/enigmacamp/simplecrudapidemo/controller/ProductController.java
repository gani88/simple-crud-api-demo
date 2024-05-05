package com.enigmacamp.simplecrudapidemo.controller;


import com.enigmacamp.simplecrudapidemo.entity.Product;
import com.enigmacamp.simplecrudapidemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(Product request) {
        Product response = productService.addProduct(request);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<Product>> showAllProduct() {
        List<Product> response = productService.getAllProduct();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/prodcut/{productId}")
    public ResponseEntity<Product> showProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> editProduct(@PathVariable Long productId, @RequestBody Product updateProduct) {
        Product preUpdate = productService.getProductById(productId);

        if (productId != null) {
            preUpdate.setId(preUpdate.getId());
            Product update = productService.updateProduct(productId, updateProduct);

            if(update != null) {
                return ResponseEntity.ok(update);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductById(@RequestParam(name="id") String id){
        Long longId = Long.parseLong(id);
        productService.deleteById(longId);
        String response = "Product has been deleted successfully";

        return ResponseEntity.ok().body(response);
    }
}
