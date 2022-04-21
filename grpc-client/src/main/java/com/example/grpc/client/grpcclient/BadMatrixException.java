package com.example.grpc.client.grpcclient;

public class BadMatrixException extends Exception {
    public BadMatrixException(String errorMessage) {
        super(errorMessage);
    }
}
