package br.com.ilima.apibusiness.domain.usecase.pedido;

import br.com.ilima.apibusiness.domain.entity.Pedido;

import java.util.List;

public abstract class Processador {

    protected Processador next;

    public Processador(Processador next){
        this.next = next;
    }

    public abstract List<Pedido> processar(List<Pedido> pedidos);
}
