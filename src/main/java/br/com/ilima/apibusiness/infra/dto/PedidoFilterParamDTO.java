package br.com.ilima.apibusiness.infra.dto;

import java.time.LocalDate;

public record PedidoFilterParamDTO (
        Long codigo,
        LocalDate dataCadastro,
        ClienteFilterParamDTO cliente
){ }
