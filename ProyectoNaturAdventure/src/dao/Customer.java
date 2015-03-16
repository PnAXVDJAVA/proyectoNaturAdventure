package dao;

public class Customer {

	private String NIF;
	private String name;
	private String email;
	private int telephone;
	
	public Customer() {
		this.NIF = null;
		this.name = null;
		this.email = null;
		this.telephone = -1;
	}
	
	public Customer(String NIF, String name, String email, int telephone) {
		
		this.NIF = NIF;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "NIF: " + NIF + "--Name: " + name + "\nE-mail: " + email + "--Telephone: " + telephone;
	}
}
