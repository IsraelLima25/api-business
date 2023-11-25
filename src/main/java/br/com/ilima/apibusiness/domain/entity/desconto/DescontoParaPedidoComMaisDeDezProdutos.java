package br.com.ilima.apibusiness.domain.entity.desconto;

import br.com.ilima.apibusiness.domain.entity.Pedido;

import java.math.BigDecimal;

public class DescontoParaPedidoComMaisDeDezProdutos extends Desconto{
    
    public DescontoParaPedidoComMaisDeDezProdutos(Desconto next){
        super(next);
    }
    
    @Override
    public BigDecimal calcular(Pedido pedido) {

        if(pedido.getQuantidadeProduto() >= 10){
            return pedido.getValorTotal().multiply(new BigDecimal(0.1));
        }

        return next.calcular(pedido);
    }
}
