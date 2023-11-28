package br.com.ilima.apibusiness.infra.dto;

import br.com.ilima.apibusiness.infra.model.ClienteModel;
import br.com.ilima.apibusiness.infra.validator.ExistsCliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClientePedidoFormDTO(
    @NotNull
    @Positive
    @ExistsCliente(domainClass = ClienteModel.class, fieldName = "id")
    Long id
) { }
