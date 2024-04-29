package com.enigmacamp.simplecrudapidemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    private List<String> records = new ArrayList<>();

    @GetMapping() // Annotation to get method
    // We can use to spring init (start.spring.io) to automatically generate
    // untuk method harus public dan mengembalikan json, xml, etc..
    public ResponseEntity<String> getHelloWorld() {
        String message =    "This is Simple CRUD API Demo!<br>" +
                            "This API allows you to perform CRUD operations on entities.<br>" +
                            "For example, create, read, update, and delete records.<br>";

        return ResponseEntity.status(200).body(message);
    }

    @PostMapping
    public ResponseEntity<String> postHelloWorld() {
        return ResponseEntity.status(200).body("HELLO WORLD POST!");
    }

    @PutMapping("/put-handler")
    public ResponseEntity<String> putHandler() {
        return ResponseEntity.status(200).body("HELLO WORLD PUT!");
    }

    @DeleteMapping("/delete-handler/{id}")
    public ResponseEntity<String> deleteHandler(@PathVariable Long id) {
        return ResponseEntity.status(200).body("Hello World DELETE! id :  " + id);
    }
}
