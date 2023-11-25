package br.com.ilima.apibusiness.domain.entity;

import br.com.ilima.apibusiness.domain.VO.Cpf;

public class Cliente {

    private String nome;
    private Cpf cpf;

    private Cliente(String nome, Cpf cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public static class Builder {

        private String nome;
        private Cpf cpf;

        public Builder setNome(String nome){
            this.nome = nome;
            return this;
        }

        public Builder setCpf(Cpf cpf){
            this.cpf = cpf;
            return this;
        }

        public Cliente build(){
            return new Cliente(nome, cpf);
        }
    }
}
