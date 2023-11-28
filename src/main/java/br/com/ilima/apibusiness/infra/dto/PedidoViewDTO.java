package br.com.ilima.apibusiness.infra.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoViewDTO(

        Long codigo,
        LocalDate dataCadastro,
        BigDecimal valorTotal,
        BigDecimal valorDesconto,
        BigDecimal valorPagamento,
        ClienteViewDTO cliente,
        List<ProdutoViewDTO> produtos
) { }
