package wsobjects;

import java.util.ArrayList;
import java.util.List;

public class ListCompaniesWS {
	public List <CompanyWS> listaws;
	
	public ListCompaniesWS(){
		listaws=new ArrayList<CompanyWS>();
	}
	
	public void add(CompanyWS comp){
		listaws.add(comp);
	}
}
