package br.com.ilima.apibusiness.domain.usecase.pedido;

import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.exception.BusinessException;

import java.util.List;

public class ProcessarValidacaoQuantidadePedidos extends Processador {
    public ProcessarValidacaoQuantidadePedidos(Processador processador) {
        super(processador);
    }

    @Override
    public List<Pedido> processar(List<Pedido> pedidos) {
        if(pedidos.size() < 1 || pedidos.size() > 10){
            throw new BusinessException("A quantidade de pedidos processados não devem ser menor que 1 ou maior que 10");
        }
        return next.processar(pedidos);
    }
}
