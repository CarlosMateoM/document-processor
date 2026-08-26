package com.mateomartinez.docprocessor.doc_processor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateomartinez.docprocessor.doc_processor.response.ErrorResponse;

@RestController
public class WelcomeController {

    @GetMapping("/hello-document")
    public ResponseEntity<String> getDocument() {
        return ResponseEntity.ok("Document content goes here");
    }

    @GetMapping("/hello-not-found")
    public ResponseEntity<ErrorResponse> getDocumentNotFound() {

        return ResponseEntity
                .status(404)
                .body(new ErrorResponse("Document not found"));

    }
}
