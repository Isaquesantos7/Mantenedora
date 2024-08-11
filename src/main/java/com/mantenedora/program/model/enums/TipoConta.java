package com.mantenedora.program.model.enums;

public enum TipoConta {
    CORRENTE(1, "Conta corrente"),
    POPANCA(2, "Conta popança");

    private final Integer code;
    private final String descricao;

    TipoConta(Integer code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoConta toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (TipoConta x: TipoConta.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalid: " + code);
    }
}
