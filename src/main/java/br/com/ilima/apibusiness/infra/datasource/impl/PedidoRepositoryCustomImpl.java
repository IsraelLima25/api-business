package br.com.ilima.apibusiness.infra.datasource.impl;

import br.com.ilima.apibusiness.infra.datasource.PedidoRepositoryCustom;
import br.com.ilima.apibusiness.infra.dto.PedidoFilterParamDTO;
import br.com.ilima.apibusiness.infra.model.PedidoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoRepositoryCustomImpl implements PedidoRepositoryCustom {

    private EntityManager entityManager;

    public PedidoRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PedidoModel> getWithFilter(PedidoFilterParamDTO params) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<PedidoModel> query = criteriaBuilder.createQuery(PedidoModel.class);

        Root<PedidoModel> pedido = query.from(PedidoModel.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.codigo() != null) {
            predicates.add(criteriaBuilder.equal(pedido.<Long>get("id"), params.codigo()));
        }
        if (params.dataCadastro() != null) {
            predicates.add(criteriaBuilder.equal(pedido.<LocalDate>get("dataCadastro"), params.dataCadastro()));
        }
        if (params.cliente().nome() != null) {
            predicates.add(criteriaBuilder.equal(pedido.get("clienteModel").get("nome"), params.cliente().nome()));
        }

        if (!predicates.isEmpty()) {
            query.where( predicates.stream().toArray(Predicate[]::new));
        }
        TypedQuery<PedidoModel> queryResult = this.entityManager.createQuery(query);

        return queryResult.getResultList();
    }
}
