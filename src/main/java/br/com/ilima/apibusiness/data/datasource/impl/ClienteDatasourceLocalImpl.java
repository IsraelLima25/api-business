package br.com.ilima.apibusiness.data.datasource.impl;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.data.datasource.JpaClienteRepository;
import br.com.ilima.apibusiness.data.model.ClienteModel;
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
