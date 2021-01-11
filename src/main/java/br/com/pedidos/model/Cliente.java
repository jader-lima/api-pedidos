package br.com.pedidos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sk_Cliente")
	private int id;
	
	@Column(name="Nome")
	private String nome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_alteracao", unique = true, nullable = false, length = 10)
	private Date dt_alteracao
	;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_inclusao", unique = true, nullable = false, length = 10)
	private Date dt_inclusao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	

}
