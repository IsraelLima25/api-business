package br.com.ilima.apibusiness.domain.entity;

import br.com.ilima.apibusiness.domain.VO.DataCadastro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Long codigo;
    private DataCadastro dataCadastro;
    private List<Produto> produtos = new ArrayList<>();
    private Cliente cliente;
    private BigDecimal valorTotal;
    private BigDecimal valorDesconto;
    private BigDecimal valorPagamento;

    private Pedido(Long codigo, DataCadastro dataCadastro, List<Produto> produtos, Cliente cliente) {
        this.codigo = codigo;
        produtos.forEach(produto -> adicionarProduto(produto));
        this.cliente = cliente;
        this.dataCadastro = dataCadastro;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public DataCadastro getDataCadastro() {
        return dataCadastro;
    }

    public BigDecimal getValorTotal(){
        this.valorTotal = this.produtos
                .stream()
                .map(produto -> produto.getValor().multiply(new BigDecimal(produto.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return valorTotal;
    }

    public BigDecimal getValorPagamento(){
        if(valorDesconto != null){
            this.valorPagamento = getValorTotal().subtract(valorDesconto);
        }else{
            this.valorPagamento = valorTotal;
        }
        return this.valorPagamento;
    }

    public int getQuantidadeProduto(){
        return this.produtos.size();
    }

    public BigDecimal getValorDesconto() {
        if(valorDesconto == null){
            return BigDecimal.ZERO;
        }
        return valorDesconto;
    }

    public void adicionarDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public static class Builder {

        private Long codigo;
        private DataCadastro dataCadastro;
        private List<Produto> produtos;
        private Cliente cliente;

        public Builder setCodigo(Long codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder setDataCadastro(DataCadastro dataCadastro) {
            this.dataCadastro = dataCadastro;
            return this;
        }

        public Builder setProdutos(List<Produto> produtos) {
            this.produtos = produtos;
            return this;
        }

        public Builder setCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Pedido build() {
            return new Pedido(codigo, dataCadastro, produtos, cliente);
        }
    }
}
