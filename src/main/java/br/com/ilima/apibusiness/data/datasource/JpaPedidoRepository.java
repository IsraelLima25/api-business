package br.com.ilima.apibusiness.data.datasource;

import br.com.ilima.apibusiness.data.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPedidoRepository extends JpaRepository<PedidoModel, Long> { }
