package br.com.ilima.apibusiness.data.datasource.impl;

import br.com.ilima.apibusiness.data.datasource.JpaProdutoRepository;
import br.com.ilima.apibusiness.data.datasource.ProdutoDatasourceLocal;
import br.com.ilima.apibusiness.data.model.ProdutoModel;
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
