package br.com.ilima.apibusiness.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoModelTest {

    @Test
    void dadoProdutoQuandoQuantidadeNaoEnviadaEntaoQuantidadeDeveSerIgualAUm(){
        Produto.Builder builder = new Produto.Builder();
        Produto produto = builder.setValor(new BigDecimal(1000.0))
                .setNome("Intel Core i5")
                .build();

        assertEquals(1, produto.getQuantidade());
    }

    @Test
    void dadoProdutoQuandoQuantidadeEnviadaEntaoQuantidadeDeveSerIgualAQuantidadeEnviada(){
        Produto.Builder builder = new Produto.Builder();
        Produto produto = builder.setValor(new BigDecimal(1000.0))
                .setNome("Intel Core i5")
                .setQuantidade(20)
                .build();

        assertEquals(20, produto.getQuantidade());
        assertNotEquals(1, produto.getQuantidade());
    }

}