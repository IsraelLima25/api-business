package br.com.ilima.apibusiness.domain.entity;

public class Cliente {

    private Long id;
    private String nome;

    public Cliente(Long id, String nome){
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
