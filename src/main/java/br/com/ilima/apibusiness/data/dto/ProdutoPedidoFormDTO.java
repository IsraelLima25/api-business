package br.com.ilima.apibusiness.data.dto;

import br.com.ilima.apibusiness.domain.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoPedidoFormDTO(

    @NotBlank
    String nome,
    @NotNull
    @Positive
    BigDecimal valor,
    int quantidade

){

    public Produto toProduto(){

        Produto.Builder builder = new Produto.Builder();

        Produto produto = builder.setNome(nome)
                .setQuantidade(quantidade)
                .setValor(valor)
                .build();

        return produto;
    }
}
