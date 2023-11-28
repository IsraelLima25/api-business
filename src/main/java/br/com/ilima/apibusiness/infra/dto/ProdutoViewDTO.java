package br.com.ilima.apibusiness.infra.dto;

import java.math.BigDecimal;

public record ProdutoViewDTO(
        Long id,
        String nome,
        BigDecimal valor,
        Integer quantidade
) { }
