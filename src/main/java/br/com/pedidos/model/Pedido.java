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
@Table(name = "Pedidos")
public class Pedido implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Sk_Pedido")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Pedido", unique = true, nullable = false, length = 10)
	private Date dt_pedido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Alteracao", unique = true, nullable = false, length = 10)
	private Date dt_alteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Dt_Inclusao", unique = true, nullable = false, length = 10)
	private Date dt_inclusao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SK_Cliente", nullable = true)
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<ItemPedido> itempedidos = new ArrayList<ItemPedido>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDt_inclusao() {
		return dt_inclusao;
	}

	public void setDt_inclusao(Date dt_inclusao) {
		this.dt_inclusao = dt_inclusao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItempedidos() {
		return itempedidos;
	}

	public void setItempedidos(List<ItemPedido> itempedidos) {
		this.itempedidos = itempedidos;
	}
	
	
	
//	[Sk_Pedido] [int] IDENTITY(1,1) NOT NULL,	
//	[Dt_Pedido] [datetime] NULL,	
//	[Dt_Alteracao] [datetime] NULL,
//	[Dt_Inclusao] [datetime] NULL,
//	[Sk_Cliente] [int] NOT NULL,

}
