package br.com.ilima.apibusiness.infra.datasource.jpa;

import br.com.ilima.apibusiness.infra.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProdutoRepository extends JpaRepository<ProdutoModel, Long> { }
