package br.com.pedidos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.pedidos.dto.ProdutoDto;
import br.com.pedidos.jdbc.JpaFactory;
import br.com.pedidos.model.Estoque;
import br.com.pedidos.model.Movimento;
@Service
public class DaoEstoque  extends DaoGenerico  {
	
	public Estoque getEstoquebyProdutosbyId(int  id) {
		EntityManager em = new JpaFactory().getEntityManager();
		
		Estoque estoque = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM Estoque a "
					+ " inner join fetch a.produto b   " 
					+ " where a.dt_inclusao = "
					+ "( select max(a.dt_inclusao) from Estoque a "
					+ " inner join a.produto b "
					+ " where b.id = :id )");	
			query.setParameter("id", id );	


			estoque = (Estoque)query.getSingleResult();	
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		
		return estoque;
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
}
