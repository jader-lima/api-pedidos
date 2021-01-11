package br.com.pedidos.test;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.http.ResponseEntity;

import br.com.pedidos.model.Estoque;
import br.com.pedidos.model.Movimento;
import br.com.pedidos.model.Produto;
import br.com.pedidos.model.Usuario;
import br.com.pedidos.dao.DaoEstoque;
import br.com.pedidos.dao.DaoMovimento;
import br.com.pedidos.dao.DaoProdutos;
import br.com.pedidos.dao.DaoUsuario;
import br.com.pedidos.dto.ProdutoDto;
import br.com.pedidos.jdbc.JpaFactory;


public class TesteDao {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//ListarProdutos();
		//QuantidadeEstoque(1);
		//DaoUsuario daoUsuario = new DaoUsuario();
		
		//System.out.println(((Usuario)daoUsuario.findbyId(Usuario.class, 1)).getEmail() );
		//QuantidadeEstoque(1);
		//TesteCadastroMovimento();
		retornaProduto();
	}
	
	public static void retornaProduto() throws Exception {
		Produto produto = null;
		DaoProdutos daoProdutos = new DaoProdutos();
		int id = 1;
		produto = (Produto)daoProdutos.findbyId(Produto.class, id);
		
		System.out.println(produto.getDescricao());
	}
	
	public static void TesteCadastroMovimento() {
		Produto produto = new Produto();
		
		DaoProdutos daoProduto = new DaoProdutos();
		DaoEstoque daoEstoque = new DaoEstoque();
		DaoMovimento daoMovimento = new DaoMovimento();
		
		Movimento movimento = new Movimento();
		movimento.setQuantidade(1);
		movimento.setTipo("E");
		movimento.setDt_inclusao(new Date());
		
		try {
			produto = (Produto)daoProduto.findbyId(Produto.class, 1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		movimento.setProduto(produto);
		
		if(movimento.getTipo().equals("S") && movimento.getQuantidade() > 0) {
			movimento.setQuantidade(movimento.getQuantidade() * -1);
		}
		
		if(movimento.getTipo().equals("E") && movimento.getQuantidade() < 0) {
			movimento.setQuantidade(movimento.getQuantidade() * -1);
		}
		
		Estoque estoque = (Estoque)daoEstoque.getEstoquebyProdutosbyId(movimento.getProduto().getId());
		
		
		if(movimento.getQuantidade() <= estoque.getQuantidade()) {		
			
			movimento = (Movimento)daoMovimento.save(movimento);	
			
			Estoque e = new Estoque();
			e.setProduto(produto);
			
			
			System.out.println(movimento.getTipo());
			System.out.println(movimento.getQuantidade());
			if(movimento.getTipo().equals("E")) {
				System.out.println("antes da soma");
				System.out.println(estoque.getQuantidade());
				System.out.println(movimento.getQuantidade());
				System.out.println("depois da soma");
				e.setQuantidade(estoque.getQuantidade() + movimento.getQuantidade());
				System.out.println(e.getQuantidade());
			
			}else {
				System.out.println("antes da soma");
				System.out.println(estoque.getQuantidade());
				System.out.println(movimento.getQuantidade());
				System.out.println("depois da substracao");
				e.setQuantidade(estoque.getQuantidade() - movimento.getQuantidade());
				
				System.out.println(e.getQuantidade());
			}
			
			e.setDt_inclusao(new Date());
			e.setDt_inclusao(new Date());
			
			daoEstoque.save(e);			
			
//			URI uri = uriBuilder.path("/estoque/{id}").buildAndExpand(movimento.getId()).toUri();
//			return ResponseEntity.created(uri).body(movimento);
		}
	}
	
	public static void QuantidadeEstoque(int id) {
		
		DaoEstoque daoEstoque = new DaoEstoque();
		Estoque estoque = (Estoque)daoEstoque.getEstoquebyProdutosbyId(id);
		

		System.out.println(estoque.getQuantidade());
		
	}
	
	public static void QuantidadeMovimento(int idProduto) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		ProdutoDto o = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT new br.com.pedidos.dto.ProdutoDto(a.produto, SUM(a.quantidade) )  FROM Estoque a "
					+ " inner join a.produto b   " 
					+ " where b.id = :id "
					+ " group by a.produto");	
			
			query.setParameter("id", idProduto );		
			o = (ProdutoDto)query.getSingleResult();		
			
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}	
		
		System.out.println(o.getDescricao());
		System.out.println(o.getQuantidade());

	}

	
	public static void ListarProdutos() {
		EntityManager em = new JpaFactory().getEntityManager();
		
		List<Produto> list = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM Produto a");
		
			list = query.getResultList();
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		for (Produto produto : list) {
			System.out.println(produto.getDescricao());
		}
	}
}
