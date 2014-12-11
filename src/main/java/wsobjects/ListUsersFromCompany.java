package wsobjects;

import java.util.ArrayList;
import java.util.List;

public class ListUsersFromCompany {
	public List <UserWS> listaUsersws;
	
	public ListUsersFromCompany(){
		listaUsersws=new ArrayList<UserWS>();
	}
	
	public void add (UserWS user){
		listaUsersws.add(user);
	}
}
