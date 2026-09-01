package com.mateomartinez.docprocessor.doc_processor.dto;

import com.mateomartinez.docprocessor.doc_processor.model.Document;

public record DocumentResponse(
        Long id,
        String filename,
        Long size) {

    public static DocumentResponse from(Document document) {
        return new DocumentResponse(
                document.getId(),
                document.getFilename(),
                document.getSize());
    }

}
