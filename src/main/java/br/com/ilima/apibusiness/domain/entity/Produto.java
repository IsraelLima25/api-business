package br.com.ilima.apibusiness.domain.entity;

import java.math.BigDecimal;

public class Produto {

    private String nome;
    private BigDecimal valor;
    private Integer quantidade;

    private Produto(String nome, BigDecimal valor, Integer quantidade){
        this.nome = nome;
        this.valor = valor;
        if(quantidade == null){
            this.quantidade = 1;
        }else{
            this.quantidade = quantidade;
        }
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public static class Builder {

        private String nome;
        private BigDecimal valor;
        private Integer quantidade;

        public Builder setNome(String nome){
            this.nome = nome;
            return this;
        }

        public Builder setValor(BigDecimal valor){
            this.valor = valor;
            return this;
        }

        public Builder setQuantidade(Integer quantidade){
            this.quantidade = quantidade;
            return this;
        }

        public Produto build(){
            return new Produto(nome, valor, quantidade);
        }
    }
}
