package com.finangen.domains.enums;

public enum TipoConta {

    CONTACORRENTE(0, "CONTACORRENTE"),
    CONTAINVESTIMENTO(1, "CONTAINVESTIMENTO"),
    CARTAOCREDITO(2, "CARTAOCREDITO"),
    ALIMENTACAO(3, "ALIMENTACAO"),
    CONTAPOUPANCA(4, "CONTAPOUPANCA");

    private Integer id;
    private String situacao;

    TipoConta() {
    }

    TipoConta(Integer id, String situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public static TipoConta toEnum(Integer id){
        if(id==null) return null;
        for(TipoConta x : TipoConta.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Tipo de conta inválida!");

    }
}
