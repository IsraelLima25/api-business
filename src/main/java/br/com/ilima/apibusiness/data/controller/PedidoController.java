package br.com.ilima.apibusiness.data.controller;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.data.dto.PedidoFormDTO;
import br.com.ilima.apibusiness.data.model.ClienteModel;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.exception.NotFoundException;
import br.com.ilima.apibusiness.domain.usecase.pedido.ProcessadorPedidoUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/pedidos")
@Validated
public class PedidoController {

    private ProcessadorPedidoUseCase processadorPedidoUseCase;
    private ClienteDataSourceLocal clienteDataSourceLocal;

    public PedidoController(ProcessadorPedidoUseCase processadorPedidoUseCase, ClienteDataSourceLocal clienteDataSourceLocal) {
        this.processadorPedidoUseCase = processadorPedidoUseCase;
        this.clienteDataSourceLocal = clienteDataSourceLocal;
    }

    @Transactional
    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> enviarPedidos(@RequestBody List<@Valid PedidoFormDTO> pedidosFormDTO ){

        List<Pedido> pedidos = pedidosFormDTO.stream().map(pedidosDTO -> {
            Optional<ClienteModel> possivelCliente = clienteDataSourceLocal.buscarPorId(pedidosDTO.cliente().codigo());
            if (!possivelCliente.isPresent()) {
                throw new NotFoundException("idCliente","A busca por id não encontrou nenhum cliente cadastrado");
            }
            return pedidosDTO.toPedido(clienteDataSourceLocal);
        }).collect(Collectors.toList());

        processadorPedidoUseCase.processar(pedidos);

        return ResponseEntity.ok().build();
    }
}
