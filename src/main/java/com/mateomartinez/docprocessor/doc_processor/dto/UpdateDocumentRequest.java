package com.mateomartinez.docprocessor.doc_processor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateDocumentRequest(
    @NotNull(message = "size is requiered")
    @Positive(message = "size must be greater than zero")
    Long size, 
    
    @NotBlank(message = "Filename is requiered")
    String filename
) {}
