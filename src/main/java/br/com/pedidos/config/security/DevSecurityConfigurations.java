package br.com.pedidos.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {


	
	@Override/*Configurações de autorização - urls, perfils de acesso, quem acesso qual recurso */
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers( "/**").permitAll()		
		.anyRequest().authenticated()
		.and().csrf().disable();//cross site request fordery, para evitar um tipo de ataque hacker 
		
		
	}
		
	
}
