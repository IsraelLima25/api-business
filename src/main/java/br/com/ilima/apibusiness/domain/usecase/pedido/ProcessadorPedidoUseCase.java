package br.com.ilima.apibusiness.domain.usecase.pedido;

import br.com.ilima.apibusiness.domain.entity.*;
import br.com.ilima.apibusiness.domain.repository.PedidoRepository;

import java.util.List;

public class ProcessadorPedidoUseCase {

    public PedidoRepository pedidoRepository;

    public ProcessadorPedidoUseCase(){ }
    public ProcessadorPedidoUseCase(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public void processar(List<Pedido> pedidos){
        Processador processador = new ProcessarValidacaoQuantidadePedidos(
                new ProcessarAplicacaoDesconto()
        );
        List<Pedido> pedidosProcessados = processador.processar(pedidos);
        pedidosProcessados.forEach(pedido -> pedidoRepository.create(pedido));
    }
}
