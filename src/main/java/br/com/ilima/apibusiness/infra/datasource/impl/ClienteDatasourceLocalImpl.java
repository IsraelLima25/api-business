package br.com.ilima.apibusiness.infra.datasource.impl;

import br.com.ilima.apibusiness.infra.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.infra.datasource.jpa.JpaClienteRepository;
import br.com.ilima.apibusiness.infra.model.ClienteModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteDatasourceLocalImpl implements ClienteDataSourceLocal {

    private JpaClienteRepository clienteRepository;

    public ClienteDatasourceLocalImpl(JpaClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<ClienteModel> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
}
