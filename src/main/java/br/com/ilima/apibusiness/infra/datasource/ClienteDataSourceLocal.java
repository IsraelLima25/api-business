package br.com.ilima.apibusiness.infra.datasource;

import br.com.ilima.apibusiness.infra.model.ClienteModel;

import java.util.Optional;

public interface ClienteDataSourceLocal {

    Optional<ClienteModel> buscarPorId(Long id);
}
