package wsobjects;

public class UserWS {
	
	long id_user;
	String login;
	String password;
	int role;
	String name;
	String phone;
	String department;
	String name_company;
	
	
	public UserWS(long id_user,String login, String password, int role, String name, String phone, String department, String name_company) {
		this.id_user=id_user;
		this.login = login;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.department = department;
		this.name_company = name_company;
	}


	public long getId_user() {
		return id_user;
	}


	public void setId_user(long id_user) {
		this.id_user = id_user;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getName_company() {
		return name_company;
	}


	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
	


	
}
 