package br.com.pedidos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "Movimento")
public class Movimento implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sk_Movimento")
	private int id;
	
	@Column(name="Quantidade")
	private int quantidade;
	
	@Column(name="Tipo")
	private String tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_inclusao", unique = true, nullable = false, length = 10)
	private Date dt_inclusao = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sk_Produto", nullable = false)
	private Produto produto;
	
	public Movimento() {
		
	}

	public Movimento(int quantidade, String tipo) {
		this.quantidade = quantidade;
		this.tipo = tipo;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDt_inclusao() {
		return dt_inclusao;
	}

	public void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	


}
