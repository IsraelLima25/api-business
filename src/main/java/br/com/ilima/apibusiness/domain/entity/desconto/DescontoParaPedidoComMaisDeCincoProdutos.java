package br.com.ilima.apibusiness.domain.entity.desconto;

import br.com.ilima.apibusiness.domain.entity.Pedido;

import java.math.BigDecimal;

public class DescontoParaPedidoComMaisDeCincoProdutos extends Desconto {

    public DescontoParaPedidoComMaisDeCincoProdutos(Desconto next){
        super(next);
    }

    @Override
    public BigDecimal calcular(Pedido pedido) {
        if(pedido.getQuantidadeProduto() > 5 && pedido.getQuantidadeProduto() < 10){
            return pedido.getValorTotal().multiply(new BigDecimal(0.05));
        }

        return next.calcular(pedido);
    }
}
