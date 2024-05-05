package com.enigmacamp.simplecrudapidemo.services;


import com.enigmacamp.simplecrudapidemo.entity.Product;
import com.enigmacamp.simplecrudapidemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
    }

    public Product updateProduct(Long productId, Product updateProduct) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product toBeUpdated = optionalProduct.get();
            toBeUpdated.setProductName(updateProduct.getProductName());
            toBeUpdated.setQuantity(updateProduct.getQuantity());
            toBeUpdated.setPrice(updateProduct.getPrice());

            return productRepository.save(toBeUpdated);
        } else {
            return null;
        }
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
