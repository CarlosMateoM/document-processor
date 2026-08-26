package com.mateomartinez.docprocessor.doc_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateomartinez.docprocessor.doc_processor.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}