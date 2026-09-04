package com.mateomartinez.docprocessor.doc_processor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDocumentRequest(
    @NotNull(message = "size is required.")
    @Positive(message = "size must be greater than zero")
    Long size, 
    
    @NotBlank(message = "Filename is required.")
    String filename
) {}
