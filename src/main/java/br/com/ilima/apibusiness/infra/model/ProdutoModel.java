package br.com.ilima.apibusiness.infra.model;

import br.com.ilima.apibusiness.infra.dto.ProdutoViewDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Deprecated
    public ProdutoModel() { }

    public ProdutoModel(String nome, BigDecimal valor, Integer quantidade) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public ProdutoViewDTO toProdutoViewDTO() {
        return new ProdutoViewDTO(id, nome, valor, quantidade);
    }
}