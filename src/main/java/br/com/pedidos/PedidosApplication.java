package br.com.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class PedidosApplication {
	/*
	 * Para fazer deploy em um servidor web existente com arquivo war, deploy tradicional
	 * adicionar na classe extends SpringBootServletInitializer
	 * 
	 * sobrescrever metodo 
	 * protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
	 * 	return builder.sources(PedidosApplication.class);
	 * }
	 * 
	 * */

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

}
