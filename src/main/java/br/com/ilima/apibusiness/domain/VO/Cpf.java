package br.com.ilima.apibusiness.domain.VO;

import br.com.ilima.apibusiness.domain.exception.ValidatorException;

public class Cpf {

    private String value;

    public Cpf(String value) {
        if(!value.matches("(^\\d{3}\\d{3}\\d{3}\\d{2}$)")){
            throw new ValidatorException("Cpf","Formato cpf inválido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
