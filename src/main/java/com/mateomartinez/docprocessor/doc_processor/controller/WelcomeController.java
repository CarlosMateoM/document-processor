package com.mateomartinez.docprocessor.doc_processor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
public class WelcomeController {

    @GetMapping("/hello-document")
    public ResponseEntity<String> getDocument() {
        return ResponseEntity.ok("Document content goes here");
    }

    @GetMapping("/hello-not-found")
    public ResponseEntity<String> getDocumentNotFound() {

        return ResponseEntity
                .status(404)
                .body("document not found");

    }
}
