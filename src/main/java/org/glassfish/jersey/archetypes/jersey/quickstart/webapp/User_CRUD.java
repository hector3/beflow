package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class User_CRUD {

	//atributos para mantener la referencia de la sesion de la bbdd y la transaccion
	private Session sesion;
	private Transaction tx;
	
	//metodo para iniciar la sesion
	private void iniciaOperacion() throws HibernateException
	{
		
		//Creamos conexión BBDD e inicamos una sesion
	    sesion = HibernateUtil.getSessionFactory().openSession();
	    //iniciamos transaccion
	    tx = sesion.beginTransaction();
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException
	{
		//si hay un error se dehace la transaccion y nos muestra el error
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
	
	//metodos CRUD
	
	
	//Inserta nuevo usuario devolviendo el id de la tabla
	public String Create_user(String name, String mail, String phone,String department, String password,int role)
	{ 
	    long id = 0; //id de la tabla user (único) 
	    //no le paso el login porquè es el mail lo copiaré de mail
	    InfoUser infouser =new InfoUser();
	    User user1 =new User();
	    
	    //parametros tabla user
	    user1.setLogin(mail);
	    user1.setPassword(password);
	    user1.setRole(role);
	    
	    //parametros tabla Info user
	    
	    infouser.setMail(mail);
	    infouser.setDepartment(department);
	    infouser.setName(name);
	    infouser.setPhone(phone);
	    infouser.setUser(user1);
	
	  
	    try 
	    { 
	        iniciaOperacion(); 
	        id = (Long)sesion.save(infouser);
	        
	        
	        //En la primer sesion a la base de datos almacenamos los dos objetos Persona 
	        //los objetos user se almacenaran en cascada*/ 
	       

	       sesion.persist(user1); 
	       sesion.persist(infouser); 
	       
	       	//metodo para guardar cliente (del objeto hibernate.sesion) 
	        tx.commit(); 
	    }catch(HibernateException he) 
	    { 
	        manejaExcepcion(he);
	        throw he; 
	       
	    }finally 
	    { 
	        sesion.close(); 
	    }  
	    return "OK"; 
	}

	
	
	//actualizacion de InfoCLiente
	
	
	public void Update_Infouser(InfoUser infouser) throws HibernateException 
	{ 
	    try 
	    { 	    	
	        iniciaOperacion(); 
	        sesion.update(infouser); //metodo update de objeto sesion
	        tx.commit(); 
	    }catch (HibernateException he) 
	    { 
	        manejaExcepcion(he); 
	        throw he; 
	    }finally 
	    { 
	        sesion.close(); 
	    } 
	}
	
	//metodo para actualizar user
	public void Update_user(User user) throws HibernateException 
	{ 
	    try 
	    { 
	        iniciaOperacion(); 
	        
	        sesion.update(user); //metodo update de objeto sesion
	        tx.commit(); 
	    }catch (HibernateException he) 
	    { 
	        manejaExcepcion(he); 
	        throw he; 
	    }finally 
	    { 
	        sesion.close(); 
	    } 
	}
	
	//buscar usuario por el correo electronico (identificacion única de user)
	
	public InfoUser Read_user(String mail) throws HibernateException
	{ 
		InfoUser infouser = null;  
		String i=null;
		long id_user=0;
	    try 
	    { 
	       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
	       i=  sesion.createQuery("SELECT u.id FROM InfoUser u WHERE u.mail ='"+mail+"'").uniqueResult().toString();
	       //una vez encontrado el id del user puedo buscarlo
	       id_user= Integer.parseInt(i);
	       infouser = (InfoUser) sesion.get(InfoUser.class, id_user); 
	     
	    } finally 
	    
	    { 
	        sesion.close(); 
	    }  
	    return infouser; 
	}
	
	
	
	//eliminamos objeto infouser el objeto user sera borrado en cascada, el objeto será eliminado por el mail
	public void Delete_user(String mail) throws HibernateException 
    { 
        try 
        { 
        	
        	InfoUser infouser = Read_user(mail);
        	
            iniciaOperacion(); 
            
            sesion.delete(infouser); //le pasamos todo el objeto a eliminar
            tx.commit(); 
 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  


	//retorna toda la lista de usuarios
	
	public List<InfoUser> User_list() throws HibernateException 
	{ 
		List <InfoUser> Lista_usuarios = null;  
	    
	    try 
	    { 
	        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
	        Lista_usuarios=  sesion.createQuery("FROM InfoUser").list(); //creamos consulta de la tabla clientes (en plural)!
	    }finally 
	    { 
	        sesion.close(); 
	    }  
	    return Lista_usuarios; 
	}
	
	
}