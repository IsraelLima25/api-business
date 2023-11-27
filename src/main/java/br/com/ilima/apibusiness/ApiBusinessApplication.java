package br.com.ilima.apibusiness;

import br.com.ilima.apibusiness.data.datasource.ClienteDataSourceLocal;
import br.com.ilima.apibusiness.data.datasource.PedidoDataSourceLocal;
import br.com.ilima.apibusiness.data.datasource.ProdutoDatasourceLocal;
import br.com.ilima.apibusiness.data.model.ClienteModel;
import br.com.ilima.apibusiness.data.model.PedidoModel;
import br.com.ilima.apibusiness.data.model.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class ApiBusinessApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiBusinessApplication.class, args);
	}
}


