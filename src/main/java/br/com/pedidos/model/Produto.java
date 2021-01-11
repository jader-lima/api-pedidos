package br.com.pedidos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "Produtos")
public class Produto implements Serializable  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sk_Produto")
	private int id;
	
	@Column(name="Descricao")
	private String descricao;
	
	@Column(name="Valor")
	private float valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Alteracao", unique = true, nullable = false, length = 10)
	private Date  dt_alteracao = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_inclusao", unique = true, nullable = false, length = 10)
	private Date dt_inclusao = new Date();
	
	public Produto() {		
	}
	
	public Produto(String descricao, float valor) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public Produto(String descricao, float valor, Date dt_atualizacao) {
		this.descricao = descricao;
		this.valor = valor;
		this.dt_alteracao = dt_atualizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getDt_alteracao() {
		return dt_alteracao;
	}

	public void setDt_alteracao(Date dt_alteracao) {
		this.dt_alteracao = dt_alteracao;
	}

	public Date getDt_inclusao() {
		return dt_inclusao;
	}

	public void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}
	
	

}
