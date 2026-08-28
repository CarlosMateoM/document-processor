package com.mateomartinez.docprocessor.doc_processor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mateomartinez.docprocessor.doc_processor.dto.CreateDocumentRequest;
import com.mateomartinez.docprocessor.doc_processor.dto.UpdateDocumentRequest;
import com.mateomartinez.docprocessor.doc_processor.exception.DocumentNotFoundException;
import com.mateomartinez.docprocessor.doc_processor.model.Document;
import com.mateomartinez.docprocessor.doc_processor.repository.DocumentRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    /* 
    TODO:
    - validaciones del request  [X]
    - formateo de respuestas    []
    - repositorio               []
    - guardar archivos          []
    */

    private final DocumentRepository documentRepository;

    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostMapping
    public Document create(
        @Valid @RequestBody CreateDocumentRequest request 
    ) {
        Document document = new Document();

        document.setFilename(request.filename());
        document.setSize(request.size());

        return documentRepository.save(document);
    }

    @GetMapping("/{id}")
    public Document getById(
        @PathVariable Long id
    ) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
        
        return document;
    }

    @GetMapping
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @PutMapping("/{id}")
    public void update(
        @PathVariable Long id, 
        @Valid @RequestBody UpdateDocumentRequest request
    ) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        document.setFilename(request.filename());
        document.setSize(request.size());

        documentRepository.save(document);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable Long id
    ) {
        documentRepository.deleteById(id);
    }

}