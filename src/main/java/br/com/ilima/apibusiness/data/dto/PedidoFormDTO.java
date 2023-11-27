package br.com.ilima.apibusiness.data.dto;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.data.model.ClienteModel;
import br.com.ilima.apibusiness.data.model.PedidoModel;
import br.com.ilima.apibusiness.data.validator.UniqueValue;
import br.com.ilima.apibusiness.domain.VO.DataCadastro;
import br.com.ilima.apibusiness.domain.entity.Cliente;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record PedidoFormDTO(

        @NotNull
        @Positive
        @UniqueValue(domainClass = PedidoModel.class, fieldName = "codigo")
        Long codigo,
        LocalDate dataCadastro,
        @NotNull
        List<ProdutoPedidoFormDTO> produtos,
        @Valid
        ClientePedidoFormDTO cliente
) {
    public Pedido toPedido(ClienteDataSourceLocal clienteDataSourceLocal) {

        Pedido.Builder builder = new Pedido.Builder();

        Optional<ClienteModel> possivelCliente = clienteDataSourceLocal.buscarPorId(cliente.codigo());
        // TODO Lançar exception caso cliente não exista

        Pedido pedido = builder.setCodigo(codigo)
                .setDataCadastro(new DataCadastro(dataCadastro))
                .setProdutos(produtos.stream().map(ProdutoPedidoFormDTO::toProduto).toList())
                .setCliente(new Cliente(possivelCliente.get().getId(), possivelCliente.get().getNome()))
                .build();

        return pedido;
    }
}
