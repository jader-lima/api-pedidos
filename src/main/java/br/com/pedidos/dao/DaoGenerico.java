package br.com.pedidos.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Service;

import br.com.pedidos.jdbc.JpaFactory;
import br.com.pedidos.utils.AnnotationIntrospector;
@Service
public class DaoGenerico {
	
	
	public Object save(Object o){
		
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin(); 
			em.persist(o); ;		
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return o;
	}
	
	public Object saveOrUpdate(Object o){
		
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin(); 
			em.merge(o);		
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		return o;
	}
	
	
	/**
	  * Salva ou Atualiza dependendo se jï¿½ existe ou nï¿½o o objeto no banco de dados. 
	  * @param list: lista das Entidades a serem Salvas (se ainda nï¿½o existir) ou a serem Atualizadas (se jï¿½ existir)
	  * @return Retorna a Entidade passada como parï¿½metro.
	  * @throws Exception
	  */
	public void saveoAll(ArrayList<Object> list) throws Exception{
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin(); 
			for(Object o : list){
				em.persist(
						o);
			}
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			em.close();
		}
	}
	
	
	 /**
	  * Salva ou Atualiza dependendo se jï¿½ existe ou nï¿½o o objeto no banco de dados. 
	  * @param list: lista das Entidades a serem Salvas (se ainda nï¿½o existir) ou a serem Atualizadas (se jï¿½ existir)
	  * @return Retorna a Entidade passada como parï¿½metro.
	  * @throws Exception
	  */
	public void saveorUpdateAll(ArrayList<Object> list) throws Exception{
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin(); 
			for(Object o : list){
				em.merge(o);
			}
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			em.close();
		}
	}
	
	/**
	  * Remove a Entidade do banco de dados.
	  * @param o: Entidade a ser removido do banco de dados.
	  * @throws Exception
	  */
	public void remove(Object o){
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin();			
			em.remove(em.contains(o));
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		
	}
	
	/**
	  * Remove a Entidade do banco de dados.
	  * @param list: Lista de Entidades a serem removidas do banco de dados.
	  * @throws Exception
	  */
	public void removeAll(ArrayList<Object> list) throws Exception{
		EntityManager em = new JpaFactory().getEntityManager();
		try{
			em.getTransaction().begin();
			for(Object o : list){
				em.remove(em.contains(o));
			}
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw e;
		}finally{
			em.close();
		}
	}
	
	/**
	  * Retorna a lista de itens salvo no banco de dados de acordo com a Entidade passada como parï¿½metro.
	  * @param classe : Entidade que deverï¿½ buscar no banco de dados.
	  * @return List contendo todos os objetos encontrados da entidade passada como parï¿½metro.
	  */
	@SuppressWarnings({ "rawtypes", "static-access"})
	public List findAll(Class classe){
					
		EntityManager em = new JpaFactory().getEntityManager();
		List list = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM ".concat(classe.getSimpleName()).concat(" a"));
		
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
	
	/**
	  * Faz a busca no banco pelo ID da entidade
	  * @param classe : Entidade a ser buscada
	  * @param id : valor a ser procurado
	  * @return Objeto encontrado no banco, caso nï¿½o encontre serï¿½ retornado NULL
	  * @throws Exception
	  */
	@SuppressWarnings({ "rawtypes", "static-access"})
	public Object findbyId(Class classe,Object id) throws Exception{
		//Object o = null;
		if(classe ==null){
			throw new Exception("classe nï¿½o pode ser nula.");
		}
		
		if(id == null){
			throw new Exception("id nï¿½o pode ser nulo.");
		}
		
		AnnotationIntrospector ai = new AnnotationIntrospector();
		
		Field[] fields = classe.getDeclaredFields();
		//Method[] methods = classe.getDeclaredMethods();
		Annotation[] annotations;
		
		String chave = "";		
		
		for(Field field : fields){
			if(field.getAnnotation(Id.class) != null){
				chave = field.getName();
				break;
			}
		}
		
		if(chave.equals("")){
			throw new Exception("Annotation @Id nï¿½o encontrada na classe " + classe.getName());
		}
		
		EntityManager em = new JpaFactory().getEntityManager();
		List list = null;
		Object o = null;
		try{
			em.getTransaction().begin(); 
			Query query = em.createQuery("SELECT a FROM ".concat(classe.getSimpleName())
					.concat(" a").concat(" where a.").concat(chave).concat(" = :id")).setParameter("id",id);
			query.setMaxResults(1);
			o = query.getSingleResult();
			em.getTransaction().commit();			
		}catch(Exception e){
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();			
		}finally{
			em.close();
		}
		
		return o;
		
		
		
	}
	
	/**
	  * Retorna objeto de acordo com a UniqueConstraint definida na Entidade
	  * @param classe : Entidade a ser buscada
	  * @param ids : Array contendo os IDS a serem buscados (na mesma ordem em que a UniqueConstraint foi definido)
	  * @return Objeto encontrado no banco, caso nï¿½o encontre serï¿½ retornado NULL 
	  * @throws Exception
	  */
	
	@SuppressWarnings("static-access")
	public Object findByUniqueConstraints(Class classe, ArrayList<Object> ids) throws Exception {
		 //Object o = null;
			if(classe ==null){
				throw new Exception("classe nï¿½o pode ser nula.");
			}
			
			if(ids == null){
				throw new Exception("id nï¿½o pode ser nulo.");
			}
			
			ArrayList<String> chaves = new ArrayList<String>();
			ArrayList<Class> tipos = new ArrayList<Class>();
			Annotation annotation = classe.getAnnotation(Table.class);
			
			if(annotation != null){
				Table table = (Table) annotation;
				for(UniqueConstraint uniqueConstraint : table.uniqueConstraints()){
					for(String column : uniqueConstraint.columnNames()){
						tipos.add(classe.getDeclaredField(column).getType());
						chaves.add(column);
					}
				}
			}else{
				throw new Exception("Annotation @Table nï¿½o encontrada na classe " + classe.getName());
			}
			
			if(chaves.size()==0){
				throw new Exception("UniqueConstraints nï¿½o encontrada na annotation @Table da classe " + classe.getName());
			}
			 
			if (chaves.size() != ids.size()) {
				throw new Exception("O nï¿½mero de parï¿½metros passados nï¿½o confere com o nï¿½mero de UniqueConstraints encontrados na classe " + classe.getName() + ". " + ids.size() + " foram passados, mas " + chaves.size() + " eram esparados.");
			}
			
			String str = "";
			for(int i= 0;i < chaves.size();i++){
				if(!ids.get(i).getClass().equals(tipos.get(i))){
					 throw new Exception("Tipos de dados incompatï¿½veis. O parï¿½metro nï¿½mero " + i + " ï¿½ incompatï¿½vel, foi passado:" + ids.get(i).getClass().toString() + " e estava esperando: " + tipos.get(i).toString());
				}
				if(str.length()==0){
					str = " where a." + chaves.get(i) + " = " + ids.get(i); 
				}else{
					str = " and " + chaves.get(i) + " = " + ids.get(i);
				}
			}
			

			EntityManager em = new JpaFactory().getEntityManager();	
			Object o = null;
			try{
				em.getTransaction().begin(); 
				//t.begin();
				Query query = em.createQuery("select a from " + classe.getName() + " a " + str);
				query.setMaxResults(1);
				o = query.getSingleResult();
				em.getTransaction().commit();			
			}catch(Exception e){
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				e.printStackTrace();			
			}finally{
				em.close();
			}
			
			return o;
	 }

}
