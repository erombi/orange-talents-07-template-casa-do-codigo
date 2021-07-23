package br.com.zupacademy.eduardo.casadocodigo.config.validacao;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class ErroPadraoDTO {

    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String erro;
    private String path;

    public ErroPadraoDTO(int status, String erro, String path) {
        this.status = status;
        this.erro = erro;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getPath() {
        return path;
    }
}
