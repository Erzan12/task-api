package com.erzan.task_api.dto.api_response;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}