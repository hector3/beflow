package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;








import wsobjects.*;





public class CRUD {

	//atributos para mantener la referencia de la sesion de la bbdd y la transaccion
	private Session sesion;
	private Transaction tx;
	
	//metodo para iniciar la sesion
	public void iniciaOperacion() throws HibernateException
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
	
	
	
/*************************** CRUD USER ************************************/
	
	//Inserta nuevo usuario devolviendo el id de la tabla
	
	public long create_user(User user)
	{ 
	    long id = 0; //id de la tabla user (único) 

	    try 
	    { 
	        iniciaOperacion(); 
	        id = (Long)sesion.save(user); //metodo para guardar cliente (del objeto hibernate.sesion) 
	        tx.commit(); 
	    }catch(HibernateException he) 
	    { 
	        manejaExcepcion(he);
	        throw he; 
	    }finally 
	    { 
	        sesion.close(); 
	    }  
	    return id; 
	}

	
	//actualizacion de User
	
	public void update_user(User user) throws HibernateException 
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
	
	public UserWS read_user(String mail) throws HibernateException
	{ 
		UserWS userws=null;

		User user = null;  
		String i=null;
		long id_user=0;
	    try 
	    { 
	       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
	       
	       i=  sesion.createQuery("SELECT u.id_user FROM User u WHERE u.mail ='"+mail+"'").uniqueResult().toString();
	       
	       //una vez encontrado el id del user puedo buscarlo
	       id_user= Integer.parseInt(i);
	       user = (User) sesion.get(User.class, id_user); 
	       userws=new UserWS(id_user, user.getLogin(), user.getPassword(), user.getRole(), user.getName(), user.getPhone(), user.getDepartment(), user.getCompany().getCompany_name());
	       
	       
	    } finally 
	    { 
	        sesion.close(); 
	    }  
	    return userws; 
	}
	
	
	
	//eliminamos cliente
	
	public void delete_user(User user) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(user); //le pasamos todo el objeto a eliminar
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
	
	public List<User> user_list() throws HibernateException 
	{ 
		List <User> Lista_usuarios = null;  
	    
	    try 
	    { 
	        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
	        Lista_usuarios=  sesion.createQuery("FROM User").list(); //creamos consulta de la tabla clientes (en plural)!
	    }finally 
	    { 
	        sesion.close(); 
	    }  
	    return Lista_usuarios; 
	}
	
	
	
/********************************CRUD COMPANY*******************/
	
	// Añade una nueva compañia  devolviendo el id de la tabla
	public long create_company(CompanyWS company)
	{ 
	    long id = 0; //id de la tabla company) 
	    Company comp= new Company(company.getCompany_name(),company.getAddress(),company.getLeader());
	    String i=null;
	    try 
	    { 
	        iniciaOperacion(); 
	        //id= (Long) 
	        sesion.persist(comp); //metodo para guardar cliente (del objeto hibernate.sesion) 
	        
	        i=  sesion.createQuery("SELECT c.id_company FROM Company c WHERE c.company_name ='"+company.getCompany_name()+"'").uniqueResult().toString();
		       //una vez encontrado el id del user puedo buscarlo
		    id= Integer.parseInt(i);
		       
	        
	        tx.commit(); 
	    }catch(HibernateException he) 
	    { 
	        manejaExcepcion(he);
	        throw he; 
	    }finally 
	    { 
	        sesion.close(); 
	    }  
	    return id; 
	}
	    
	  //actualizacion de company
	   public void update_company(Company company) throws HibernateException 
		{ 
		    try 
		    { 
		        iniciaOperacion(); 
		        sesion.update(company); //metodo update de objeto sesion
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
	    
	  //eliminamos compañia -> si se borra compañia los usuarios seran borrados en cascada
		
		public void delete_company(Company company) throws HibernateException 
	    { 
	        try 
	        { 
	            iniciaOperacion(); 
	            sesion.delete(company); //le pasamos todo el objeto a eliminar
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
		
		
		//retorna toda la lista de compañias
		
		public List<Company> company_list() throws HibernateException 
		{ 
			List <Company> Lista_compañias = null;  
		    
		    try 
		    { 
		        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
		        Lista_compañias=  sesion.createQuery("FROM Company").list(); //creamos consulta de la tabla clientes (en plural)!
		    }finally 
		    { 
		        sesion.close(); 
		    }  
		    return Lista_compañias; 
		}
		
		//retorna una compañia buscada por su nombre
		
		public Company read_company(String name) throws HibernateException
		{ 
			
			Company comp = null;  
			String i=null;
			long id_company=0;
		    try 
		    { 
		       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
		      
		       i=  sesion.createQuery("SELECT c.id_company FROM Company c WHERE c.company_name ='"+name+"'").uniqueResult().toString();
		       //una vez encontrado el id del user puedo buscarlo
		       id_company= Integer.parseInt(i);
		       comp= (Company) sesion.get(Company.class, id_company); 
		       
		    } finally 
		    { 
		        sesion.close(); 
		    }  
		    return comp; 
		}
		
		
	/***************************METODOS CRUD NODOS************************************/
		
		
		//Inserta nuevo usuario devolviendo el id de la tabla
		
		public long create_node(Node node)
		{ 
		    long id = 0; //id de la tabla user (único) 

		    try 
		    { 
		        iniciaOperacion(); 
		        id = (Long)sesion.save(node); //metodo para guardar cliente (del objeto hibernate.sesion) 
		        tx.commit(); 
		    }catch(HibernateException he) 
		    { 
		        manejaExcepcion(he);
		        throw he; 
		    }finally 
		    { 
		        sesion.close(); 
		    }  
		    return id; 
		}

		
		//actualizacion de User
		
		public void update_node(Node node) throws HibernateException 
		{ 
		    try 
		    { 
		        iniciaOperacion(); 
		        sesion.update(node); //metodo update de objeto sesion
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
		
		//buscar nombre del nodo por mac y puerto
		
		public String read_node(NodeWS node) throws HibernateException
		{ 
			
			String i=null;
			
		    try 
		    { 
		       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
		       
		       i=  (String) sesion.createQuery("SELECT u.node_name FROM Node u WHERE u.MAC_address ='"+node.getMAC_address()+"' and u.port_number ='"+node.getPort_number()+"'").uniqueResult();
		       //una vez encontrado el id del user puedo buscarlo
		       //id_user= Integer.parseInt(i);
		       //user = (User) sesion.get(User.class, id_user); 
		     
		    } finally 
		    { 
		        sesion.close(); 
		    }  
		    return i; 
		}
		
		
		
		
		//buscar nombre del nodo por mac y puerto y retorna el nodo
		
				public Node read_node2(String MAC, String port_number) throws HibernateException
				{ 
					Node node = null;  
					String i=null;
					long id_node=0;
				    try 
				    { 
				       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
				       
				       i=  sesion.createQuery("SELECT u.id_node FROM Node u WHERE u.MAC_address ='"+MAC+"' and u.port_number ='"+port_number+"'").uniqueResult().toString();
				       //una vez encontrado el id del user puedo buscarlo
				       id_node= Integer.parseInt(i);
				       node = (Node) sesion.get(Node.class, id_node); 
				     
				    } finally 
				    { 
				        sesion.close(); 
				    }  
				    return node; 
				}
		
		
		//eliminamos nodo
		
		public void delete_node(Node node) throws HibernateException 
	    { 
	        try 
	        { 
	            iniciaOperacion(); 
	            sesion.delete(node); //le pasamos todo el objeto a eliminar
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

		//retorna toda la lista de usuarios*** NO SE SI ES NECESARI
		
		public List<Node> node_list() throws HibernateException 
		{ 
			List <Node> Lista_nodos = null;  
		    
		    try 
		    { 
		        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
		        Lista_nodos=  sesion.createQuery("FROM Node").list(); //creamos consulta de la tabla clientes (en plural)!
		    }finally 
		    { 
		        sesion.close(); 
		    }  
		    return Lista_nodos; 
		}
		
	/*********************************CRUD WS*************************************/
		
		
		//Add a client of a company
		
		public long wsadd_user( UserWS user)
		{ 
			
			long id = 0; //id de la tabla user (único) 
			
		    
		    User userH = new User(user.getLogin(),user.getPassword(),user.getRole(),user.getName(),user.getPhone(),user.getDepartment());
		    
		    //User cliente1 = new User("luis.ortega@gmail.com", "gutie33", 1, "Luis","677899876", "Informatica"); 
		    
		    try 
		    { 
		        iniciaOperacion(); 
		       
		        id= (Long) sesion.createQuery("SELECT u.id_company FROM Company u WHERE u.company_name ='"+user.getName_company()+"'").uniqueResult();
		        Company x = (Company) sesion.load(Company.class, id);
		        x.addUsuario(userH);
		      
			    userH.setCompany(x);
		     
		        //sesion.update(compx);
		        
		        
		        //metodo para guardar cliente (del objeto hibernate.sesion) 
		        tx.commit(); 
		    }catch(HibernateException he) 
		    { 
		        manejaExcepcion(he);
		        throw he; 
		    }finally 
		    { 
		    	//Busqueda del id con qu elo ha introducido a la BBDD
		    	id= (Long) sesion.createQuery("SELECT u.id_user FROM User u WHERE u.mail ='"+user.getLogin()+"'").uniqueResult();
		        sesion.close(); 
		    }  
		    return id; 
		}
		
		//busqueda de si el usuario existe para creacion de user 
				public Boolean user_exists (UserWS user) {
					
					Query i = null;
					
					iniciaOperacion();
					i = sesion.createQuery("SELECT u.id_user FROM User u WHERE u.mail = :login"); 
				    i.setString("login", user.getLogin());
				    
				    return (i.uniqueResult() != null);
				}
				
		//busqueda de si la compañia existe para creacion
				public Boolean company_exists (CompanyWS comp) {
							
							Query i = null;
							
							iniciaOperacion();
							i = sesion.createQuery("SELECT u.id_company FROM Company u WHERE u.company_name = :name"); 
						    i.setString("name", comp.getCompany_name());
						    return (i.uniqueResult() != null);
						}
				
		//borrar usuario ws - No devuelve nada 
				
		public void wsdelete_user(String mail) throws HibernateException 
		{ 
			User user_del = null;  
			String i=null;
			long id_user=0;
					
			 try 
			  { 
			       iniciaOperacion(); 

			        i=  sesion.createQuery("SELECT u.id_user FROM User u WHERE u.mail ='"+mail+"'").uniqueResult().toString();
			 	    //una vez encontrado el id del user puedo buscarlo
			 	    id_user= Integer.parseInt(i);
			 	    user_del = (User) sesion.get(User.class, id_user);  	       
			        sesion.delete(user_del); //le pasamos todo el objeto a eliminar
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
		
		
		//borrar compañia ws- no devuelve nada
		public void wsdelete_company(String nameCompany) throws HibernateException 
		{ 
			Company comp_del = null;  
			String i=null;
			long id_comp=0;
					
			 try 
			  { 
			       iniciaOperacion(); 

			        i=  sesion.createQuery("SELECT u.id_company FROM Company u WHERE u.company_name ='"+nameCompany+"'").uniqueResult().toString();
			 	    //una vez encontrado el id del user puedo buscarlo
			 	    id_comp= Integer.parseInt(i);
			 	    comp_del = (Company) sesion.get(Company.class, id_comp);  	       
			        sesion.delete(comp_del); //le pasamos todo el objeto a eliminar
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
		
		//actualizar compañia ws- no devuelve nada
		
		public void wsupdate_company(CompanyWS company) throws HibernateException 
		{ 
			Company comp_update = null;  
									
			 try 
			  { 
			       iniciaOperacion(); 
			       comp_update = (Company) sesion.get(Company.class,company.getId_company()); 
			       
			       comp_update.setAddress(company.getAddress());
			       comp_update.setCompany_name(company.getCompany_name());
			       comp_update.setLeader(company.getLeader());
			       
			       sesion.update(comp_update); //le pasamos todo el objeto a modificar
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
	
		//actualizar Usuario ws- no devuelve nada
		
				public void wsupdate_user(UserWS user) throws HibernateException 
				{ 
					User user_update = null;  
								
					 try 
					  { 
					       iniciaOperacion(); 
					       user_update = (User) sesion.get(User.class,user.getId_user()); 
					       
					       
					       //actualizo los camps de la tabla
					       user_update.setLogin(user.getLogin());
					       user_update.setMail(user.getLogin());
					       user_update.setName(user.getName());
					       user_update.setPassword(user.getPassword());;
					       user_update.setPhone(user.getPhone());
					       user_update.setRole(user.getRole());
					       user_update.setDepartment(user.getDepartment());
					       
					       sesion.update(user_update); //le pasamos todo el objeto a modificar
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
				
				public CompanyWS wsread_company(String name) throws HibernateException
				{ 
					CompanyWS compws=null;
					Company comp = null;  
					String i=null;
					long id_company=0;
				    try 
				    { 
				       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
				      
				       i=  sesion.createQuery("SELECT c.id_company FROM Company c WHERE c.company_name ='"+name+"'").uniqueResult().toString();
				       //una vez encontrado el id del user puedo buscarlo
				       id_company= Integer.parseInt(i);
				       comp= (Company) sesion.get(Company.class, id_company); 
				       compws =new CompanyWS(id_company,comp.getCompany_name(),comp.getAddress(),comp.getLeader());
				    } finally 
				    { 
				        sesion.close(); 
				    }  
				    return compws; 
				}
				
				public List<User> wsuser_list(String name) throws HibernateException 
				{ 
					String i =null;
					long id_company=0;
					List <User> Lista_usuarios = null;  
					List<User> users=new ArrayList<User>();
				    
				    try 
				    { 
				        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
				       
				        i=  sesion.createQuery("SELECT c.id_company FROM Company c WHERE c.company_name ='"+name+"'").uniqueResult().toString();
					       //una vez encontrado el id del user puedo buscarlo
					    id_company= Integer.parseInt(i);
					    Company compx= (Company) sesion.get(Company.class, id_company); 
					    Lista_usuarios=compx.getUsuarios();
					    
					    users.addAll(Lista_usuarios);
					    
						for (User tempUser : Lista_usuarios) {
							//para cargar todos los objetos de user
							}
				     
				    }finally 
				    { 
				        sesion.close(); 
				    }  
				    return users;//Lista_usuarios; 
				}
				
				
				//Add a Node of a company
				
				public long wsadd_node(NodeWS node)
				{ 
					
					
					long id = 0; //id de la tabla user (único) 
				    Node nodeC = new Node(node.getNode_name(),node.getMAC_address(),node.getPort_number());
				    
				    //User cliente1 = new User("luis.ortega@gmail.com", "gutie33", 1, "Luis","677899876", "Informatica"); 
				    
				    try 
				    { 
				        iniciaOperacion(); 
				       
				        id= (Long) sesion.createQuery("SELECT u.id_company FROM Company u WHERE u.company_name ='"+node.getName_company()+"'").uniqueResult();
				        Company x = (Company) sesion.load(Company.class, id);
				        x.addNodo(nodeC);
				      
					    nodeC.setCompany(x);
				     
				        //sesion.update(compx);
				        
				        
				        //metodo para guardar cliente (del objeto hibernate.sesion) 
				        tx.commit(); 
				    }catch(HibernateException he) 
				    { 
				        manejaExcepcion(he);
				        throw he; 
				    }finally 
				    { 
				    	//Busqueda del id con qu elo ha introducido a la BBDD
				    	id= (Long) sesion.createQuery("SELECT u.id_node FROM Node u WHERE u.node_name ='"+node.getNode_name()+"'").uniqueResult();
				        sesion.close(); 
				    }  
				    return id; 
				}
				
				//busqueda de si el nodo existe para creacion de nodo
				public Boolean node_exists (NodeWS node) {
					
					Query i = null;
					
					iniciaOperacion();
					i = sesion.createQuery("SELECT u.id_node FROM Node u WHERE u.node_name = :seu"); 
				    i.setString("seu", node.getNode_name());
				    
				    return (i.uniqueResult() != null);
				}
				
				
				//borrar nodo ws - No devuelve nada 
				
				public void wsdelete_node(String name) throws HibernateException 
				{ 
					Node node_del = null;  
					String i=null;
					long id_node=0;
							
					 try 
					  { 
					       iniciaOperacion(); 

					        i=  sesion.createQuery("SELECT u.id_node FROM Node u WHERE u.node_name ='"+name+"'").uniqueResult().toString();
					 	    //una vez encontrado el id del user puedo buscarlo
					 	    id_node= Integer.parseInt(i);
					 	    node_del = (Node) sesion.get(Node.class, id_node);  	       
					        sesion.delete(node_del); //le pasamos todo el objeto a eliminar
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
				
				//actualizar Usuario ws- no devuelve nada
				
				public void wsupdate_node(NodeWS node) throws HibernateException 
				{ 
					Node node_update = null;  
								
					 try 
					  { 
					       iniciaOperacion(); 
					       node_update = (Node) sesion.get(Node.class,node.getId_node()); 
					       
					       
					       //actualizo los camps de la tabla
					       node_update.setMAC_address(node.getMAC_address());
					       node_update.setNode_name(node.getNode_name());
					       node_update.setPort_number(node.getPort_number());
					       
					       
					       sesion.update(node_update); //le pasamos todo el objeto a modificar
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
				
				//buscar nodeo por su nombre (identificacion única de user)
				
				public NodeWS wsread_node(String name) throws HibernateException
				{ 
					NodeWS nodews=null;

					Node node = null;  
					String i=null;
					long id_node=0;
				    try 
				    { 
				       iniciaOperacion(); //unique result me devuelve el objeto encontrado con dicho correo electronico
				       
				       i=  sesion.createQuery("SELECT u.id_node FROM Node u WHERE u.node_name ='"+name+"'").uniqueResult().toString();
				       
				       //una vez encontrado el id del user puedo buscarlo
				       id_node= Integer.parseInt(i);
				       node = (Node) sesion.get(Node.class, id_node); 
				       nodews=new NodeWS(id_node, node.getNode_name(),node.getMAC_address(),node.getPort_number(), node.getCompany().getCompany_name());
				       
				       
				    } finally 
				    { 
				        sesion.close(); 
				    }  
				    return nodews; 
				}
				
				//retorna lista de nodos de una compañia
				
				public List<Node> wsnode_list(String name) throws HibernateException 
				{ 
					String i =null;
					long id_company=0;
					List <Node> Lista_nodos = null;  
					List <Node> nodes=new ArrayList<Node>();
				    
				    try 
				    { 
				        iniciaOperacion(); //IMPORTANTE la query: se pide la clase realmnete Cliente! no la tabla que se ha creado
				       
				        i=  sesion.createQuery("SELECT c.id_company FROM Company c WHERE c.company_name ='"+name+"'").uniqueResult().toString();
					       //una vez encontrado el id del user puedo buscarlo
					    id_company= Integer.parseInt(i);
					    Company compx= (Company) sesion.get(Company.class, id_company); 
					    Lista_nodos=compx.getNodes();
					    
					    nodes.addAll(Lista_nodos);
					    
						for (Node tempUser : Lista_nodos) {
							//para cargar todos los objetos de user
							}
				     
				    }finally 
				    { 
				        sesion.close(); 
				    }  
				    return nodes;//Lista_nodos en arraylist; 
				}
				
}