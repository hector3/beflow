package wsobjects;

public class CompanyWS {
	
	long id_company;
	String company_name;
	String address;
	String leader;

	public CompanyWS(long id_company,String company_name, String address, String leader) {
		this.id_company = id_company;
		this.company_name = company_name;
		this.address = address;
		this.leader = leader;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public long getId_company() {
		return id_company;
	}

	public void setId_company(long id_company) {
		this.id_company = id_company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

}
