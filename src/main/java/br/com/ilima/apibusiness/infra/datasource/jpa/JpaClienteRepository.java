package br.com.ilima.apibusiness.infra.datasource.jpa;

import br.com.ilima.apibusiness.infra.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClienteRepository extends JpaRepository<ClienteModel, Long> { }
