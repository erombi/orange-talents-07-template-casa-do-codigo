package br.com.zupacademy.eduardo.casadocodigo.config.validacao;

public class ErroDeFormularioDTO {

    private String campo;
    private String erro;

    public ErroDeFormularioDTO() {    }

    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
