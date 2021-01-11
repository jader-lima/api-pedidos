package br.com.pedidos.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pedidos.dao.DaoEstoque;
import br.com.pedidos.dao.DaoMovimento;
import br.com.pedidos.dao.DaoProdutos;
import br.com.pedidos.dto.ProdutoDto;
import br.com.pedidos.form.MovimentoForm;
import br.com.pedidos.form.ProdutosForm;

import br.com.pedidos.model.Estoque;
import br.com.pedidos.model.Movimento;
import br.com.pedidos.model.Produto;

@RestController
@RequestMapping("/movimento")
public class MovimentoController {
	
	@Autowired
	private DaoMovimento daoMovimento;
	
	@Autowired
	private DaoProdutos daoProduto;
	
	@Autowired
	private DaoEstoque daoEstoque;
	
	@GetMapping
	public List<Movimento> lista(String descricao) {
//		if(descricao == null) {
		return daoMovimento.getListaEstoquebyProdutosbyDescription(descricao);
//		}else {
//			return ProdutoDto.converter(daoProdutos.getListaProdutosbyDescription(descricao));
//		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Movimento> cadastrar(@RequestBody @Valid MovimentoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = new Produto();
		try {
			produto = (Produto)daoProduto.findbyId(Produto.class, form.getIdProduto());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Movimento movimento = form.converter(form);
		movimento.setProduto(produto);
		
		if(movimento.getTipo().equals("S") && movimento.getQuantidade() > 0) {
			movimento.setQuantidade(movimento.getQuantidade() * -1);
		}
		
		if(movimento.getTipo().equals("E") && movimento.getQuantidade() < 0) {
			movimento.setQuantidade(movimento.getQuantidade() * -1);
		}
		
		Estoque estoque = (Estoque)daoEstoque.getEstoquebyProdutosbyId(movimento.getProduto().getId());
		
		System.out.println(movimento.getTipo());
		System.out.println(movimento.getQuantidade());
		if(movimento.getQuantidade() <= estoque.getQuantidade()) {		
			
			movimento = (Movimento)daoMovimento.save(movimento);	
			
			Estoque e = new Estoque();
			e.setProduto(produto);
			
			System.out.println("antes da soma");
			System.out.println(estoque.getQuantidade());
			System.out.println(movimento.getQuantidade());
			System.out.println("depois da soma");
			e.setQuantidade(estoque.getQuantidade() + movimento.getQuantidade());
			System.out.println(e.getQuantidade());
			
			
			
			e.setDt_inclusao(new Date());
			
			daoEstoque.save(e);			
			
			URI uri = uriBuilder.path("/movimento/{id}").buildAndExpand(movimento.getId()).toUri();
			return ResponseEntity.created(uri).body(movimento);
		}
		
	
		
		return ResponseEntity.notFound().build();
		
	}

}
