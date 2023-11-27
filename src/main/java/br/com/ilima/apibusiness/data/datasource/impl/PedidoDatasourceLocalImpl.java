package br.com.ilima.apibusiness.data.datasource.impl;

import br.com.ilima.apibusiness.data.datasource.JpaPedidoRepository;
import br.com.ilima.apibusiness.data.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.data.model.PedidoModel;
import org.springframework.stereotype.Component;

@Component
public class PedidoDatasourceLocalImpl implements PedidoDataSourceLocal {

    private JpaPedidoRepository pedidoRepository;

    public PedidoDatasourceLocalImpl(JpaPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void create(PedidoModel pedidoModel) {
        pedidoRepository.save(pedidoModel);
    }
}
