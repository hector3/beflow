package wsobjects;

public class CompanyWS {
	
	String company_name;
	String address;
	String leader;

	public CompanyWS(String company_name, String address, String leader) {

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
