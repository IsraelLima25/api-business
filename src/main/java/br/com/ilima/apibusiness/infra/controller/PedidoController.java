package br.com.ilima.apibusiness.infra.controller;

import br.com.ilima.apibusiness.infra.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.infra.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.infra.dto.PedidoFilterParamDTO;
import br.com.ilima.apibusiness.infra.dto.PedidoFormDTO;
import br.com.ilima.apibusiness.infra.dto.PedidoViewDTO;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.usecase.pedido.ProcessadorPedidoUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/pedidos")
@Validated
public class PedidoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

    private ProcessadorPedidoUseCase processadorPedidoUseCase;
    private ClienteDataSourceLocal clienteDataSourceLocal;
    private PedidoDataSourceLocal pedidoDataSourceLocal;

    public PedidoController(ProcessadorPedidoUseCase processadorPedidoUseCase, ClienteDataSourceLocal clienteDataSourceLocal, PedidoDataSourceLocal pedidoDataSourceLocal) {
        this.processadorPedidoUseCase = processadorPedidoUseCase;
        this.clienteDataSourceLocal = clienteDataSourceLocal;
        this.pedidoDataSourceLocal = pedidoDataSourceLocal;
    }

    @Transactional
    @PostMapping(path = "/enviar", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido processado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    public ResponseEntity<Void> enviarPedidos(@RequestBody List<@Valid PedidoFormDTO> pedidosFormDTO){
        
        LOGGER.info("Iniciando processamento de pedidos");
        List<Pedido> pedidos = pedidosFormDTO.stream().map(pedidosDTO -> pedidosDTO.toPedido(clienteDataSourceLocal)).collect(Collectors.toList());
        processadorPedidoUseCase.processar(pedidos);
        LOGGER.info(pedidosFormDTO.size()+" pedido(s) processados com sucesso");
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/filtrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro processado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    public ResponseEntity<List<PedidoViewDTO>> filtrarPedido(@RequestBody PedidoFilterParamDTO filterDTO){

        LOGGER.info("Iniciando busca de pedidos por filtro");
        List<PedidoModel> pedidos = this.pedidoDataSourceLocal.filterByParam(filterDTO);
        List<PedidoViewDTO> pedidosView = pedidos.stream().map(PedidoModel::toPedidoView).toList();
        LOGGER.info("Filtro de busca processado com sucesso");
        return ResponseEntity.ok(pedidosView);
    }

}
