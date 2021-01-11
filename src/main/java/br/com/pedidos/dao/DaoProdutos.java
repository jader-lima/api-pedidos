package br.com.pedidos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.pedidos.dto.ProdutoDto;
import br.com.pedidos.jdbc.JpaFactory;
import br.com.pedidos.model.Produto;

@Service
public class DaoProdutos extends DaoGenerico {
	
	public List<Produto> getListaProdutos() {
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
		
		return list;
	}
	
	public Produto findProdutosbyDescription(String descricao) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		Produto produto = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM Produto a"
					+ " where a.descricao like :descricao");	
			query.setParameter("descricao","%" + descricao + "%" );
		
			produto = (Produto)query.getSingleResult();
			if(produto == null){
				produto = new Produto();
			}
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		
		return produto;
	}
	
	public List<Produto> getListaProdutosbyDescription(String descricao) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		List<Produto> list = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM Produto a"
					+ " where a.descricao like :descricao");	
			query.setParameter("descricao","%" + descricao + "%" );
		
			list = query.getResultList();
			if(list == null){
				list = new ArrayList<Produto>();
			}
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		
		return list;
	}
	
	@Override
	public Object save(Object o) {
		// TODO Auto-generated method stub
		return super.save(o);
	}

	@Override
	public Object saveOrUpdate(Object o) {
		// TODO Auto-generated method stub
		return super.saveOrUpdate(o);
	}

	@Override
	public void saveorUpdateAll(ArrayList<Object> list) throws Exception {
		// TODO Auto-generated method stub
		super.saveorUpdateAll(list);
	}

	@Override
	public void remove(Object o) {
		// TODO Auto-generated method stub
		super.remove(o);
	}

	@Override
	public void removeAll(ArrayList<Object> list) throws Exception {
		// TODO Auto-generated method stub
		super.removeAll(list);
	}

	@Override
	public List findAll(Class classe) {
		// TODO Auto-generated method stub
		return super.findAll(classe);
	}

	@Override
	public Object findbyId(Class classe, Object id) throws Exception {
		// TODO Auto-generated method stub
		return super.findbyId(classe, id);
	}

	@Override
	public Object findByUniqueConstraints(Class classe, ArrayList<Object> ids) throws Exception {
		// TODO Auto-generated method stub
		return super.findByUniqueConstraints(classe, ids);
	}
	
	
	@SuppressWarnings("rawtypes")
	public ProdutoDto findQuantidadeMovimentoPorProduto(int idProduto) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		ProdutoDto produtoDto = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT new br.com.pedidos.dto.ProdutoDto("
					+ " a.produto, SUM(a.quantidade) )  "
					+ " FROM Movimento a "
					+ " inner join a.produto b   " 
					+ " where b.id = :id "
					+ " group by a.produto");		
			
			query.setParameter("id", idProduto );		
			produtoDto = (ProdutoDto)query.getSingleResult();		
			
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}	
		
		return produtoDto;

	}
	
	@SuppressWarnings("rawtypes")
	public List<ProdutoDto> findListaMovimentoPorProduto(int idProduto) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		List<ProdutoDto> list = null;
		try{
			em.getTransaction().begin(); 
//			Query query = em.createQuery("SELECT new br.com.pedidos.dto.ProdutoDto(a.produto, SUM(a.quantidade) )  FROM Estoque a "
//					+ " inner join a.produto b   " 
//					+ " where b.id = :id "
//					+ " group by a.produto");	
			Query query = em.createQuery("SELECT new br.com.pedidos.dto.ProdutoDto(a.produto,a.tipo, SUM(a.quantidade) )  FROM Movimento a "
					+ " inner join a.produto b   " 
					+ " where b.id = :id "
					+ " group by a.produto, a.tipo");	
			
			query.setParameter("id", idProduto );		
			list = (List<ProdutoDto>)query.getResultList();
			
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}	
		
		return list;

	}
	
	
	

	
	
}
