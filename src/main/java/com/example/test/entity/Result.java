package com.example.test.entity;

public class Result<T> {
    int success;
    T result;

    public T getResult() {
        return result;
    }

    public int getSuccess() {
        return success;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
