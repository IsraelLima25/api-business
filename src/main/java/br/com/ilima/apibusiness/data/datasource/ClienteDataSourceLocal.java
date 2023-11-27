package br.com.ilima.apibusiness.data.datasource;

import br.com.ilima.apibusiness.data.model.ClienteModel;

import java.util.Optional;

public interface ClienteDataSourceLocal {

    Optional<ClienteModel> buscarPorId(Long id);
}
