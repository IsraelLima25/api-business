package br.com.ilima.apibusiness.infra.datasource;

import br.com.ilima.apibusiness.infra.dto.PedidoFilterParamDTO;
import br.com.ilima.apibusiness.infra.model.PedidoModel;

import java.util.List;

public interface PedidoDataSourceLocal {

    void create(PedidoModel pedidoModel);
    List<PedidoModel> filterByParam(PedidoFilterParamDTO filterParamDTO);

    void deleteAll();
}
