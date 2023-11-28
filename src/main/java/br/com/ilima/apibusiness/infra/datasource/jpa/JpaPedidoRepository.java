package br.com.ilima.apibusiness.infra.datasource.jpa;

import br.com.ilima.apibusiness.infra.datasource.PedidoRepositoryCustom;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPedidoRepository extends JpaRepository<PedidoModel, Long>, PedidoRepositoryCustom { }
