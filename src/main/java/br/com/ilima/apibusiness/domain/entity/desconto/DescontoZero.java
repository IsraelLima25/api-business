package br.com.ilima.apibusiness.domain.entity.desconto;

import br.com.ilima.apibusiness.domain.entity.Pedido;

import java.math.BigDecimal;

public class DescontoZero extends Desconto {

    public DescontoZero(){
        super(null);
    }

    @Override
    public BigDecimal calcular(Pedido pedido) {
        return BigDecimal.ZERO;
    }
}
