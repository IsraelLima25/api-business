package br.com.ilima.apibusiness.infra.validator;

import br.com.ilima.apibusiness.domain.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsClienteValidator implements ConstraintValidator<ExistsCliente, Long> {

    @PersistenceContext
    private EntityManager manager;

    private String domainAttribute;
    private Class<?> klass;


    @Override
    public void initialize(ExistsCliente params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if(id == null) {
            return true;
        }
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", id);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Erro grave!! Foi encontrado mais de um registro com o id="+id);
        if(list.isEmpty()){
            throw new NotFoundException("idCliente","Não existe cliente cadastrado com este id");
        }
        return true;
        //return !list.isEmpty();
    }
}
