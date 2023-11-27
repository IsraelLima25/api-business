package br.com.ilima.apibusiness.domain.usecase;

import br.com.ilima.apibusiness.domain.VO.Cpf;
import br.com.ilima.apibusiness.domain.VO.DataCadastro;
import br.com.ilima.apibusiness.domain.entity.Cliente;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.entity.Produto;
import br.com.ilima.apibusiness.domain.exception.BusinessException;
import br.com.ilima.apibusiness.domain.repository.PedidoRepository;
import br.com.ilima.apibusiness.domain.usecase.pedido.ProcessadorPedidoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProcessadorPedidoUseCaseTest {

    @Mock
    PedidoRepository pedidoRepository;

    @Test
    void dadoProcessamentoPedidoQuandoQuantidadeMaiorQueDezEntaoBusinessExceptionDeveSerLancada(){

        List<Pedido> pedidos = new ArrayList<>();
        for(int i=0; i<11; i++){
            Pedido.Builder builder = new Pedido.Builder();
            Pedido pedido = builder
                    .setCodigo(0101L)
                    .setCliente(
                            new Cliente.Builder()
                                    .setNome("Sizenando")
                                    .setCpf(new Cpf("21158648081"))
                                    .build()
                    )
                    .setProdutos(
                            List.of(
                                    new Produto.Builder()
                                            .setNome("Core intel i5")
                                            .setValor(new BigDecimal(1000.0))
                                            .setQuantidade(1)
                                            .build()
                            )
                    )
                    .setDataCadastro(new DataCadastro(null))
                    .build();
            pedidos.add(pedido);
        }

        assertThrows(BusinessException.class, () -> {
            ProcessadorPedidoUseCase processadorPedidoUseCase = new ProcessadorPedidoUseCase(pedidoRepository);
            processadorPedidoUseCase.processar(pedidos);
        });
    }

    @Test
    void dadoProcessamentoPedidoQuandoQuantidadeMenorQueUmEntaoBusinessExceptionDeveSerLancada(){
        List<Pedido> pedidos = new ArrayList<>();
        assertThrows(BusinessException.class, () -> {
            ProcessadorPedidoUseCase processadorPedidoUseCase = new ProcessadorPedidoUseCase(pedidoRepository);
            processadorPedidoUseCase.processar(pedidos);
        });
    }

    @Test
    void dadoPedidoQuandoContemMaisQueCincoProdutosEntaoNoValorTotalPedidoDeveSerAplicadoDescontoDeCincoPorcento(){

        Pedido.Builder builderPedido = new Pedido.Builder();
        Pedido pedido = builderPedido
                .setCodigo(0101L)
                .setCliente(
                        new Cliente.Builder()
                                .setNome("Sizenando")
                                .setCpf(new Cpf("21158648081"))
                                .build()
                )
                .setProdutos(new ArrayList<>())
                .setDataCadastro(new DataCadastro(LocalDate.of(2023, 10, 15)))
                .build();

        for(int i=0; i<6; i++){
            pedido.adicionarProduto(
                    new Produto.Builder()
                            .setNome("Core intel i5")
                            .setValor(new BigDecimal(1000.0))
                            .setQuantidade(1)
                            .build()
            );
        }

        ProcessadorPedidoUseCase processadorPedidoUseCase = new ProcessadorPedidoUseCase(pedidoRepository);
        processadorPedidoUseCase.processar(List.of(pedido));

        assertEquals(6, pedido.getQuantidadeProduto());
        assertEquals(new BigDecimal(300.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorDesconto().setScale(1, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(6000.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorTotal().setScale(1, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(5700.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorPagamento().setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void dadoPedidoQuandoContemMaisQueDezProdutosEntaoNoValorTotalPedidoDeveSerAplicadoDescontoDeDezPorcento(){

        Pedido.Builder builderPedido = new Pedido.Builder();
        Pedido pedido = builderPedido
                .setCodigo(0101L)
                .setCliente(
                        new Cliente.Builder()
                                .setNome("Sizenando")
                                .setCpf(new Cpf("21158648081"))
                                .build()
                )
                .setProdutos(new ArrayList<>())
                .setDataCadastro(new DataCadastro(LocalDate.of(2023, 10, 15)))
                .build();

        for(int i=0; i<11; i++){
            pedido.adicionarProduto(
                    new Produto.Builder()
                            .setNome("Core intel i5")
                            .setValor(new BigDecimal(1000.0))
                            .setQuantidade(1)
                            .build()
            );
        }
        ProcessadorPedidoUseCase processadorPedidoUseCase = new ProcessadorPedidoUseCase(pedidoRepository);
        processadorPedidoUseCase.processar(List.of(pedido));

        assertEquals(11, pedido.getQuantidadeProduto());
        assertEquals(new BigDecimal(11000.0), pedido.getValorTotal());
        assertEquals(new BigDecimal(1100.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorDesconto().setScale(1, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(9900.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorPagamento().setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void dadoPedidoQuandoContemQuatroProdutosEntaoNoValorTotalPedidoNaoDeveSerAplicadoDesconto(){

        Pedido.Builder builderPedido = new Pedido.Builder();
        Pedido pedido = builderPedido
                .setCodigo(0101L)
                .setCliente(
                        new Cliente.Builder()
                                .setNome("Sizenando")
                                .setCpf(new Cpf("21158648081"))
                                .build()
                )
                .setProdutos(new ArrayList<>())
                .setDataCadastro(new DataCadastro(LocalDate.of(2023, 10, 15)))
                .build();

        for(int i=0; i<4; i++){
            pedido.adicionarProduto(
                    new Produto.Builder()
                            .setNome("Core intel i5")
                            .setValor(new BigDecimal(1000.0))
                            .setQuantidade(1)
                            .build()
            );
        }
        ProcessadorPedidoUseCase processadorPedidoUseCase = new ProcessadorPedidoUseCase(pedidoRepository);
        processadorPedidoUseCase.processar(List.of(pedido));

        assertEquals(4, pedido.getQuantidadeProduto());
        assertEquals(new BigDecimal(4000.0), pedido.getValorTotal());
        assertEquals(BigDecimal.ZERO, pedido.getValorDesconto());
        assertEquals(new BigDecimal(4000.0).setScale(1, RoundingMode.HALF_UP), pedido.getValorPagamento().setScale(1, RoundingMode.HALF_UP));
    }
}