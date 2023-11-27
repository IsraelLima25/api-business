package br.com.ilima.apibusiness.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record PedidoFormDTO(
        @NotNull
        @Positive
        Long codigo,
        LocalDate dataCadastro,
        @NotNull
        List<ProdutoPedidoFormDTO> produtos,
        ClientePedidoFormDTO cliente
) { }
