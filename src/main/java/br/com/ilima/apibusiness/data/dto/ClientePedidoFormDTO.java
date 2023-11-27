package br.com.ilima.apibusiness.data.dto;

import br.com.ilima.apibusiness.domain.entity.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClientePedidoFormDTO(
    @NotNull
    @Positive
    Long codigo
) { }
