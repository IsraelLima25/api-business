package br.com.ilima.apibusiness.infra.datasource;

import br.com.ilima.apibusiness.infra.model.ProdutoModel;

public interface ProdutoDatasourceLocal {

    void create(ProdutoModel produtoModel);
}
