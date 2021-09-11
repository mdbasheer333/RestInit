package com.restinit.core.pojos;

public class Token {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Token{" +
                "error='" + error + '\'' +
                '}';
    }
}
