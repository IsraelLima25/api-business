package br.com.ilima.apibusiness.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClientePedidoFormDTO(
    @NotNull
    @Positive
    Integer codigo
) { }
