package com.mateomartinez.docprocessor.doc_processor.response;

public class ApiResponse<T> {

    private T data;
    private String message;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    

    
}
