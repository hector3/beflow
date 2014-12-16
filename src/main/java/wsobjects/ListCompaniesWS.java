package wsobjects;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.Company;

public class ListCompaniesWS {
	public List <CompanyWS> listaws;
	
	public ListCompaniesWS(){
		listaws=new ArrayList<CompanyWS>();
	}
	
	public void add(CompanyWS comp){
		listaws.add(comp);
	}
	
	 public ArrayList<CompanyWS> getList(List<Company> lista) {
			
			List <CompanyWS> listaws=new ArrayList<CompanyWS>();
			CompanyWS compws=null;
			try{
				
				for (Company tempComp : lista) {
					
					compws= new CompanyWS(0,tempComp.getCompany_name(),tempComp.getAddress(),tempComp.getLeader());
					listaws.add(compws);
					System.out.println(tempComp.getCompany_name());
					System.out.println(tempComp.getLeader());
					System.out.println(tempComp.getAddress());
					}
			}
			
			
			catch(Exception e){
				e.printStackTrace();
				System.out.println("problemas.. Excepcion: "+e.getMessage());
			}
			return (ArrayList<CompanyWS>) listaws;
	 }
}
