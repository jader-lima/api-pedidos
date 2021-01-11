package br.com.pedidos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.pedidos.dao.DaoProdutos;
import br.com.pedidos.dto.ProdutoDto;
import br.com.pedidos.form.ProdutosAtualizarForm;
import br.com.pedidos.form.ProdutosForm;
import br.com.pedidos.model.Produto;

@RestController
@RequestMapping("/produtos")
public class produtoController {
	
	@Autowired
	private DaoProdutos daoProdutos;
	
	@GetMapping
	@Cacheable(value = "listadeProdutos")
	public List<ProdutoDto> lista(@RequestParam(required = false)String descricao) {
		/*verificar como fazer paginação com jpa entity manager
		 * @RequestParam(required = false)String descricao,@RequestParam int pagina, @RequestParam int qtd)
		 * */
		if(descricao == null) {
			return ProdutoDto.converter(daoProdutos.getListaProdutos());
		}else {
			return ProdutoDto.converter(daoProdutos.getListaProdutosbyDescription(descricao));
		}
		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listadeProdutos",allEntries = true)
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutosForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter(form);
		produto = (Produto)daoProdutos.save(produto);		
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listadeProdutos",allEntries = true)
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable int id,@RequestBody @Valid ProdutosAtualizarForm form, UriComponentsBuilder uriBuilder) {
		
		Produto produto = form.atualizar(id, daoProdutos);		
		return ResponseEntity.ok(new ProdutoDto(produto));
	}
	
	@DeleteMapping("/{id}")	
	@Transactional
	@CacheEvict(value = "listadeProdutos",allEntries = true)
	public ResponseEntity<?> remover(@PathVariable int id) {//? somente para o warning não aparecer
		
		Produto produto = new Produto();
		try {
			produto.setId(id);
			daoProdutos.remove(produto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().build();
		
	}
	
//	@GetMapping("/{id}")
//	public ProdutoDto detalhar(@PathVariable int id) {
//		Produto produto = new Produto();
//		try {
//			produto = (Produto)daoProdutos.findbyId(Produto.class, id);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		} 
//		return new ProdutoDto(produto);
//	}	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> detalhar(@PathVariable int id) {
		Produto produto = new Produto();		
		try {
			produto = (Produto)daoProdutos.findbyId(Produto.class, id);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(produto);
		}		
		
	}
	
	@GetMapping("/totalmovimento/{idProduto}")
	public ProdutoDto TotalEstoque(@PathVariable int idProduto) {
		ProdutoDto produto = null;
		try {
			produto = daoProdutos.findQuantidadeMovimentoPorProduto(idProduto);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return produto;
	}
	
	@GetMapping("/movimentoproduto/{idProduto}")
	public List<ProdutoDto> TotalEstoqueFinal(@PathVariable int idProduto) {
		List<ProdutoDto> list = null;
		try {
			list = daoProdutos.findListaMovimentoPorProduto(idProduto);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return list;
	}
	


}
