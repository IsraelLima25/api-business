package br.com.ilima.apibusiness.domain.entity;

import br.com.ilima.apibusiness.domain.VO.DataCadastro;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoModelTest {

    @Test
    void dadoPedidoQuandoDataCadastroNaoEnviadaEntaoDataCadastroDeveSerIgualADataAtual(){

        Pedido.Builder builder = new Pedido.Builder();

        Pedido pedido = builder
                .setCodigo(0101L)
                .setCliente(
                        new Cliente(1L,"Sizenando")
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

        assertEquals(LocalDate.now(), pedido.getDataCadastro().getData());
    }

    @Test
    void dadoPedidoQuandoDataCadastroEnviadaEntaoDataCadastroDeveSerIgualADataEnviada(){

        Pedido.Builder builder = new Pedido.Builder();

        Pedido pedido = builder
                .setCodigo(0101L)
                .setCliente(
                        new Cliente(1L,"Sizenando")
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
                .setDataCadastro(new DataCadastro(LocalDate.of(2023, 10, 15)))
                .build();

        assertEquals(LocalDate.of(2023, 10, 15), pedido.getDataCadastro().getData());
        assertNotEquals(LocalDate.now(), pedido.getDataCadastro().getData());
    }

    @Test
    void dadoPedidoQuandoContemProdutosEntaoValorTotalPedidoDeveSerCalculado(){

        Pedido.Builder builderPedido = new Pedido.Builder();

        Pedido pedido = builderPedido
                .setCodigo(0101L)
                .setCliente(
                        new Cliente(1L,"Sizenando")
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
                .setDataCadastro(new DataCadastro(LocalDate.of(2023, 10, 15)))
                .build();

        Produto.Builder builderProduto = new Produto.Builder();
        Produto novoProduto = builderProduto
                .setNome("Core intel i7")
                .setQuantidade(2)
                .setValor(new BigDecimal(1500.0))
                .build();
        pedido.adicionarProduto(novoProduto);

        BigDecimal valorTotalPedido = pedido.getValorTotal();

        assertEquals(new BigDecimal(4000.0), valorTotalPedido);
        assertEquals(2, pedido.getQuantidadeProduto());
    }
}