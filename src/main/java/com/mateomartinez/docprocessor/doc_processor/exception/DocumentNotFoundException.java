package com.mateomartinez.docprocessor.doc_processor.exception;

public class DocumentNotFoundException extends RuntimeException {
    
    public DocumentNotFoundException (Long id) {
        super("Document not found: " + id);
    }
}