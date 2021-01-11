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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "ItensPedidos")
public class ItemPedido implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sk_ItensPedido")
	private int id;
	
	@Column(name="Qtd")
	private int quantidade;
	
	@Column(name="Valor")
	private float valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Pedido", unique = true, nullable = false, length = 10)
	private Date dt_pedido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Alteracao", unique = true, nullable = false, length = 10)
	private Date dt_alteracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sk_Produto", nullable = false)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SK_Pedido", nullable = false)
	private Pedido pedido;

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

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getDt_pedido() {
		return dt_pedido;
	}

	public void setDt_pedido(Date dt_pedido) {
		this.dt_pedido = dt_pedido;
	}

	public Date getDt_alteracao() {
		return dt_alteracao;
	}

	public void setDt_alteracao(Date dt_alteracao) {
		this.dt_alteracao = dt_alteracao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
	
	
	
//	[Sk_ItensPedido] [int] IDENTITY(1,1) NOT NULL,	
//	Qtd int not null,
//	Valor float not null,
//	[Dt_Alteracao] [datetime] NULL,
//	[Dt_Inclusao] [datetime] NULL,
//	[Sk_Produto] [int]  NOT NULL,	
//	[Sk_Pedido] [int]  NOT NULL,	

}
