package br.ifma.edu.imobiliaria.api.exceptionhandler;

public class Erro {
    private String campo;
    private String mensagem;

    public Erro(String campo, String erro) {
        this.campo = campo;
        this.mensagem = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
