package br.com.ilima.apibusiness.domain.VO;

import java.time.LocalDate;

public class DataCadastro {

    private LocalDate value;

    public DataCadastro(LocalDate dataCadastro){
        if(dataCadastro == null){
            this.value = LocalDate.now();
        }else{
            this.value = dataCadastro;
        }
    }

    public LocalDate getData(){
        return this.value;
    }
}
