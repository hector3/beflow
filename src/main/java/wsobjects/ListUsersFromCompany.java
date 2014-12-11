package wsobjects;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.Company;
import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.User;

public class ListUsersFromCompany {
	public List <UserWS> listaUsersws;
	
	public ListUsersFromCompany(){
		listaUsersws=new ArrayList<UserWS>();
	}
	
	public void add (UserWS user){
		listaUsersws.add(user);
	}
	
	
	 public ArrayList<UserWS> getList(List<User> lista) {
			
			List <UserWS> listaws=new ArrayList<UserWS>();
			
			try{
				
				
				for (User tempUser : lista) {
					
					UserWS user = new UserWS(0,tempUser.getLogin(),tempUser.getPassword(),tempUser.getRole(),tempUser.getName(),tempUser.getPhone(),tempUser.getDepartment(),tempUser.getCompany().getCompany_name());
					listaws.add(user);
					System.out.println("Usuario: "+user.getLogin());
					
					}
			}
			
			
			catch(Exception e){
				
				System.out.println("problemas.. Excepcion: "+e.getMessage());
			}
			return (ArrayList<UserWS>) listaws;
	 }
}
