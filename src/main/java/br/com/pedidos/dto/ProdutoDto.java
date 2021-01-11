package br.com.pedidos.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.pedidos.model.Produto;



public class ProdutoDto {	
	
	private int id;	
	
	private String descricao;	
	
	private float valor;	
	
	private Date  dt_alteracao;	
	
	private Date dt_inclusao;
	
	private long quantidade;
	
	private String tipo;

	public ProdutoDto(Produto produto) {
		
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.dt_alteracao = produto.getDt_alteracao();
		this.dt_inclusao = produto.getDt_inclusao();
	}
	
	public ProdutoDto(Produto produto,long quantidade) {
		
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.dt_alteracao = produto.getDt_alteracao();
		this.dt_inclusao = produto.getDt_inclusao();
		this.quantidade = quantidade;
	}
	
public ProdutoDto(Produto produto,String tipo,long quantidade) {
		
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.dt_alteracao = produto.getDt_alteracao();
		this.dt_inclusao = produto.getDt_inclusao();
		this.tipo = tipo;
		this.quantidade = quantidade;
		
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
	
	
	
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public static List<ProdutoDto> converter(List<Produto> produtos){
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
	public static ProdutoDto converter(Produto produto,int quantidade){
		return new ProdutoDto(produto, quantidade);
	}
	

}
