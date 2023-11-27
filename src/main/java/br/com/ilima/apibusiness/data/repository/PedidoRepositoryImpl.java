package br.com.ilima.apibusiness.data.repository;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.data.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.data.datasource.ProdutoDatasourceLocal;
import br.com.ilima.apibusiness.data.model.ClienteModel;
import br.com.ilima.apibusiness.data.model.PedidoModel;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import br.com.ilima.apibusiness.domain.repository.PedidoRepository;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepositoryImpl implements PedidoRepository {

    private PedidoDataSourceLocal pedidoDataSourceLocal;
    private ClienteDataSourceLocal clienteDataSourceLocal;
    private ProdutoDatasourceLocal produtoDatasourceLocal;

    public PedidoRepositoryImpl(PedidoDataSourceLocal pedidoDataSourceLocal, ClienteDataSourceLocal clienteDataSourceLocal, ProdutoDatasourceLocal produtoDatasourceLocal) {
        this.pedidoDataSourceLocal = pedidoDataSourceLocal;
        this.clienteDataSourceLocal = clienteDataSourceLocal;
        this.produtoDatasourceLocal = produtoDatasourceLocal;
    }

    @Override
    public void create(Pedido pedido) {
        ClienteModel clienteModel = clienteDataSourceLocal.buscarPorId(pedido.getCliente().getId()).get();
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel = pedidoModel.toPedidoModel(pedido, clienteModel);
        pedidoModel.getProdutos().forEach(produtoModel -> produtoDatasourceLocal.create(produtoModel));
        pedidoDataSourceLocal.create(pedidoModel);
    }
}
