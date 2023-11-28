package br.com.ilima.apibusiness.infra.datasource.impl;

import br.com.ilima.apibusiness.infra.datasource.jpa.JpaProdutoRepository;
import br.com.ilima.apibusiness.infra.datasource.ProdutoDatasourceLocal;
import br.com.ilima.apibusiness.infra.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDatasourceLocalImpl implements ProdutoDatasourceLocal {

    private JpaProdutoRepository produtoRepository;

    public ProdutoDatasourceLocalImpl(JpaProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void create(ProdutoModel produtoModel) {
        produtoRepository.save(produtoModel);
    }
}
