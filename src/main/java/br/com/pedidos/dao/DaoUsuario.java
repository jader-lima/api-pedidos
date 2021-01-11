package br.com.pedidos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.pedidos.jdbc.JpaFactory;
import br.com.pedidos.model.Produto;
import br.com.pedidos.model.Usuario;
@Service
public class DaoUsuario extends DaoGenerico {
	
	public Usuario findbyEmail(String username) {
		EntityManager em = new JpaFactory().getEntityManager();		
		Usuario usuario = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM Usuario a"
					+ " where a.email = :username");	
			query.setParameter("username", username );
		
			usuario = (Usuario)query.getSingleResult();			
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		System.out.println(usuario.getEmail());
		return usuario;
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
	public void saveoAll(ArrayList<Object> list) throws Exception {
		// TODO Auto-generated method stub
		super.saveoAll(list);
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
