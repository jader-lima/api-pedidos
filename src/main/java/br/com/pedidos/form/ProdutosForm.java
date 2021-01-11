package br.com.pedidos.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.util.http.FastHttpDateFormat;
import org.hibernate.validator.constraints.Length;

import br.com.pedidos.dao.DaoProdutos;
import br.com.pedidos.model.Produto;

public class ProdutosForm {	

	@NotNull @NotEmpty @Length(max = 100) 
	private String descricao;	
	@NotNull 
	private float valor;
	
	

	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Produto converter(ProdutosForm form) {		
		return new Produto(form.getDescricao(),form.getValor()) ;
	}
	
	 
	

}
