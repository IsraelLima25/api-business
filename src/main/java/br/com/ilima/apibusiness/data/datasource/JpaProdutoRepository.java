package br.com.ilima.apibusiness.data.datasource;

import br.com.ilima.apibusiness.data.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProdutoRepository extends JpaRepository<ProdutoModel, Long> { }
