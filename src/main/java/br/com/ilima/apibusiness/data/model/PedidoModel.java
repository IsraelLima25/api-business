package br.com.ilima.apibusiness.data.model;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.domain.entity.Cliente;
import br.com.ilima.apibusiness.domain.entity.Pedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_pedido")
public class PedidoModel {

    @Id
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Column(name = "valor_pagamento")
    private BigDecimal valorPagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private ClienteModel clienteModel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ProdutoModel> produtos = new ArrayList<>();

    @Deprecated
    public PedidoModel() { }

    public Long getCodigo() {
        return codigo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public ClienteModel getCliente() {
        return clienteModel;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public PedidoModel(Long codigo, LocalDate dataCadastro, BigDecimal valorTotal, BigDecimal valorDesconto, BigDecimal valorPagamento, ClienteModel clienteModel, List<ProdutoModel> produtos) {
        this.codigo = codigo;
        this.dataCadastro = dataCadastro;
        this.valorTotal = valorTotal;
        this.valorDesconto = valorDesconto;
        this.valorPagamento = valorPagamento;
        this.clienteModel = clienteModel;
        produtos.forEach(produtoModel -> this.produtos.add(produtoModel));
    }


    public PedidoModel toPedidoModel(Pedido pedido, ClienteModel clienteModel) {

        List<ProdutoModel> produtosModel = pedido.getProdutos().stream().map(produto -> new ProdutoModel(produto.getNome(), produto.getValor(), produto.getQuantidade())).toList();
        PedidoModel pedidoModel = new PedidoModel(pedido.getCodigo(), pedido.getDataCadastro().getData(), pedido.getValorTotal(), pedido.getValorDesconto(), pedido.getValorPagamento(), clienteModel, produtosModel);
        return pedidoModel;
    }
}
