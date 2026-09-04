package com.mateomartinez.docprocessor.doc_processor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.mateomartinez.docprocessor.doc_processor.dto.CreateDocumentRequest;
import com.mateomartinez.docprocessor.doc_processor.dto.DocumentResponse;
import com.mateomartinez.docprocessor.doc_processor.dto.UpdateDocumentRequest;
import com.mateomartinez.docprocessor.doc_processor.exception.ResourceNotFoundException;
import com.mateomartinez.docprocessor.doc_processor.model.Document;
import com.mateomartinez.docprocessor.doc_processor.repository.DocumentRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DocumentResponse> create(
            @Valid @RequestBody CreateDocumentRequest request
    ) {
        Document document = new Document();

        document.setFilename(request.filename());
        document.setSize(request.size());

        Document savedDocument = documentRepository.save(document);

        DocumentResponse documentResponse = DocumentResponse.from(savedDocument);

        return ResponseEntity.ok(documentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getById(
            @PathVariable Long id
    ) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        DocumentResponse documentResponse = DocumentResponse.from(document);

        return ResponseEntity.ok(documentResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DocumentResponse>> getAll(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Document> documents = documentRepository.findAll(pageable);

        Page<DocumentResponse> response = documents.map(DocumentResponse::from);

        return ResponseEntity.ok(response);
    }

 
    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDocumentRequest request
    ) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        document.setFilename(request.filename());
        document.setSize(request.size());

        documentRepository.save(document);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {
        this.getById(id);

        documentRepository.deleteById(id);
    }

}