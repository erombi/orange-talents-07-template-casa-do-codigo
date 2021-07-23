package br.com.zupacademy.eduardo.casadocodigo.config.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String path;

    public ResourceNotFoundException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
