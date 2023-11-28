package br.com.ilima.apibusiness.infra.datasource.impl;

import br.com.ilima.apibusiness.infra.datasource.jpa.JpaPedidoRepository;
import br.com.ilima.apibusiness.infra.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.infra.dto.PedidoFilterParamDTO;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<PedidoModel> filterByParam(PedidoFilterParamDTO filterParamDTO) {
        return pedidoRepository.getWithFilter(filterParamDTO);
    }

    @Override
    public void deleteAll() {
        pedidoRepository.deleteAll();
    }
}
