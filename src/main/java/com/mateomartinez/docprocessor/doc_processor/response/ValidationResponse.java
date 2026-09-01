package com.mateomartinez.docprocessor.doc_processor.response;

import java.util.Map;

public record ValidationResponse (
    String message,
    Map<String, String> errors
) {
    
}
