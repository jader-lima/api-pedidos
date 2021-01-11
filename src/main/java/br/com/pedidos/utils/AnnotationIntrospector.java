package br.com.pedidos.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationIntrospector {
	public AnnotationIntrospector() {
        super();
    }

    public Annotation[] findClassAnnotation(Class<?> clazz) {
        return clazz.getAnnotations();
    }

    public Annotation[] findMethodAnnotation(Class<?> clazz, String methodName) {

        Annotation[] annotations = null;
        try {
            Class<?>[] params = null;
            Method method = clazz.getDeclaredMethod(methodName, params);
            if (method != null) {
                annotations = method.getAnnotations();                
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return annotations;
    }
    
    

    public Annotation[] findFieldAnnotation(Class<?> clazz, String fieldName) {
        Annotation[] annotations = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field != null) {
                annotations = field.getAnnotations();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return annotations;
    }
    
    public Field findField(Class<?> clazz,Annotation annotation ){
    	Field field = null;
    	Annotation[] annotations = null;
    	Field[] fields = clazz.getFields();
    	
    	for(Field f : fields){
    		annotations = f.getAnnotations();
    		for(Annotation a : annotations){
    			if(a.annotationType().equals(javax.persistence.Id.class)){
    				field = f;
    				break;
    			}
    		}
    	}   	
    	
    	
    	return field;
    }
    
    
//    private Long getIdByReflection(Object bean){  
//        try{  
//            Field idField = bean.getClass().getDeclaredField("id");             
//            idField.setAccessible(true);  
//            return (Long) idField.get(bean);  
//        }catch(Exception ex){  
//            throw new RuntimeException("NÃ£o foi possÃ­vel obter a propriedade 'id' do item",ex);  
//        }  
//    }  

}
