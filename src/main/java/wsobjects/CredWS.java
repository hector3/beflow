package wsobjects;

public class CredWS {

	String login;
	String pass;
	
	public CredWS(String login,String pass){
		this.login=login;
		this.pass=pass;
		

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
