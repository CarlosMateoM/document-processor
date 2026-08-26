package com.mateomartinez.docprocessor.doc_processor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mateomartinez.docprocessor.doc_processor.exception.DocumentNotFoundException;
import com.mateomartinez.docprocessor.doc_processor.model.Document;
import com.mateomartinez.docprocessor.doc_processor.repository.DocumentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentRepository documentRepository;

    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostMapping
    public Document create(Long size, String filename) {
        Document document = documentRepository.save(new Document(size, filename));
        return document;
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

    @PutMapping
    public void update(Long id, Long size, String filename) {

        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));

        document.setFilename(filename);
        document.setSize(size);

        documentRepository.save(document);
    }

    @DeleteMapping
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

}