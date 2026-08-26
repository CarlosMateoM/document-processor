package com.mateomartinez.docprocessor.doc_processor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Document {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Long id;

    private Long size;
    private String filename;

    public Document() {
        size=0L;
        filename="";
    }

    public Document(Long size, String filename) {
        this.size = size;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
