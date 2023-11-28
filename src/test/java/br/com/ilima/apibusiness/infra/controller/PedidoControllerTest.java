package br.com.ilima.apibusiness.infra.controller;

import br.com.ilima.apibusiness.infra.configuration.FormatConverter;
import br.com.ilima.apibusiness.infra.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.infra.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.infra.dto.*;
import br.com.ilima.apibusiness.infra.model.ClienteModel;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import br.com.ilima.apibusiness.infra.model.ProdutoModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class PedidoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PedidoDataSourceLocal pedidoDataSourceLocal;

    @Autowired
    ClienteDataSourceLocal clienteDataSourceLocal;

    @Autowired
    FormatConverter formatConverter;

    List<PedidoFormDTO> pedidos = new ArrayList<>();

    ClienteModel clienteModel;

    PedidoModel pedidoModel;

    @BeforeEach
    void setupInitial(){
        loadingData();
    }

    @AfterEach
    void setupFinal() {
        pedidoDataSourceLocal.deleteAll();
    }

    @Test
    void pedidosEnviadoEmFormatoJSONDeveSerProcessadoRetornar200() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos/enviar")
                        .content(formatConverter.toJSON(pedidos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void pedidosEnviadoEmFormatoXMLDeveSerProcessadoRetornar200() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos/enviar")
                        .content(formatConverter.toXML(pedidos))
                        .contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void pedidoComCodigoRepetidoNaoDeveSerProcessadoRetornar400() throws Exception{

        this.pedidoDataSourceLocal.create(pedidoModel);

        List<PedidoFormDTO> pedidos = List.of(
                new PedidoFormDTO(
                        1250L,
                        LocalDate.of(2023, 11, 28),
                        List.of(
                                new ProdutoPedidoFormDTO("Intel core", new BigDecimal(1000.0), 2)
                        ), new ClientePedidoFormDTO(2L))
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos/enviar")
                        .content(formatConverter.toJSON(pedidos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void pedidoComCodigoClienteNaoCadastradoNaoDeveSerProcessadoRetornar404() throws Exception{

        List<PedidoFormDTO> pedidos = List.of(
                new PedidoFormDTO(
                        1250L,
                        LocalDate.of(2023, 11, 28),
                        List.of(
                                new ProdutoPedidoFormDTO("Intel core", new BigDecimal(1000.0), 2)
                        ), new ClientePedidoFormDTO(100L))
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos/enviar")
                        .content(formatConverter.toJSON(pedidos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveFiltrarPedidoPorTodosCamposRetornar200() throws Exception{

        this.pedidoDataSourceLocal.create(pedidoModel);
        PedidoFilterParamDTO pedidoFilterParamDTO = new PedidoFilterParamDTO(1250L, LocalDate.now(), new ClienteFilterParamDTO("Laeah"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/pedidos/filtrar")
                        .content(formatConverter.toJSON(pedidoFilterParamDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void loadingData() {
        PedidoFormDTO pedidoFormDTO = new PedidoFormDTO(
                1230L,
                LocalDate.of(2023, 11, 28),
                List.of(
                        new ProdutoPedidoFormDTO("Intel core", new BigDecimal(1000.0), 2)
                ), new ClientePedidoFormDTO(2L));

        pedidos.add(pedidoFormDTO);

        clienteModel = clienteDataSourceLocal.buscarPorId(1L).get();

        pedidoModel = new PedidoModel(1250L, LocalDate.now(), BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.ZERO,clienteModel,
                List.of(new ProdutoModel("Processador",new BigDecimal(1000.0),2)));
    }

}