package br.com.ilima.apibusiness.domain.entity.desconto;

import br.com.ilima.apibusiness.domain.entity.Pedido;

import java.math.BigDecimal;

public abstract class Desconto {

    protected Desconto next;

    public Desconto(Desconto next){
        this.next = next;
    }

    public abstract BigDecimal calcular(Pedido pedido);
}
