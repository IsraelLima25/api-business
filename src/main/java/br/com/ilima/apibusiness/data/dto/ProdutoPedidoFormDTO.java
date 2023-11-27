package br.com.ilima.apibusiness.data.dto;

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

){ }
