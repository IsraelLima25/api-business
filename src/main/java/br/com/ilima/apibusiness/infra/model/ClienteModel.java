package br.com.ilima.apibusiness.infra.model;

import br.com.ilima.apibusiness.infra.dto.ClienteViewDTO;
import br.com.ilima.apibusiness.domain.entity.Cliente;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Deprecated
    public ClienteModel() { }

    public ClienteModel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public Cliente toCliente() {
        return new Cliente(id, nome);
    }

    public ClienteViewDTO toClienteViewDTO() {
        return new ClienteViewDTO(id, nome);
    }
}
