package br.com.ilima.apibusiness.infra.dto;

import br.com.ilima.apibusiness.infra.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.infra.model.ClienteModel;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import br.com.ilima.apibusiness.infra.validator.UniqueValue;
import br.com.ilima.apibusiness.domain.VO.DataCadastro;
import br.com.ilima.apibusiness.domain.entity.Cliente;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record PedidoFormDTO(

        @NotNull
        @Positive
        @UniqueValue(domainClass = PedidoModel.class, fieldName = "id")
        Long codigo,
        LocalDate dataCadastro,
        @NotNull
        List<ProdutoPedidoFormDTO> produtos,
        @Valid
        ClientePedidoFormDTO cliente
) {
    public Pedido toPedido(ClienteDataSourceLocal clienteDataSourceLocal) {

        Pedido.Builder builder = new Pedido.Builder();

        Optional<ClienteModel> possivelCliente = clienteDataSourceLocal.buscarPorId(cliente.id());
        Pedido pedido = builder.setCodigo(codigo)
                .setDataCadastro(new DataCadastro(dataCadastro))
                .setProdutos(produtos.stream().map(ProdutoPedidoFormDTO::toProduto).toList())
                .setCliente(new Cliente(possivelCliente.get().getId(), possivelCliente.get().getNome()))
                .build();

        return pedido;
    }
}
