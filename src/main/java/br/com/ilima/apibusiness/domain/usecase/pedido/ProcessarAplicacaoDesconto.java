package br.com.ilima.apibusiness.domain.usecase.pedido;

import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.entity.desconto.Desconto;
import br.com.ilima.apibusiness.domain.entity.desconto.DescontoParaPedidoComMaisDeCincoProdutos;
import br.com.ilima.apibusiness.domain.entity.desconto.DescontoParaPedidoComMaisDeDezProdutos;
import br.com.ilima.apibusiness.domain.entity.desconto.DescontoZero;

import java.util.List;

public class ProcessarAplicacaoDesconto extends Processador {


    public ProcessarAplicacaoDesconto() {
        super(null);
    }

    @Override
    public List<Pedido> processar(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos){
            Desconto desconto = new DescontoParaPedidoComMaisDeCincoProdutos(
                    new DescontoParaPedidoComMaisDeDezProdutos(
                            new DescontoZero()
                    )
            );
            pedido.adicionarDesconto(desconto.calcular(pedido));
        }

        return pedidos;
    }
}
