package br.com.ilima.apibusiness.data.datasource;

import br.com.ilima.apibusiness.data.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClienteRepository extends JpaRepository<ClienteModel, Long> { }
