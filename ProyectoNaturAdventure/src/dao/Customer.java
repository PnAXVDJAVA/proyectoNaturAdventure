package dao;

public class Customer {

	private String nif;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String email;
	private int telephone;
	
	public Customer() {
		this.nif = null;
		this.name = null;
		this.firstSurname = null;
		this.secondSurname = null;
		this.email = null;
		this.telephone = -1;
	}
	
	public Customer(String nIF, String name, String firstSurname,
			String secondSurname, String email, int telephone) {
		super();
		nif = nIF;
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
		this.email = email;
		this.telephone = telephone;
	}
	
	
	public String getNIF() {
		return nif;
	}

	public void setNIF(String nIF) {
		nif = nIF;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
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
	public String toString() {
		return "Nif: " + nif + "\tName Surname Lastname: " + name + " " + firstSurname + " " + secondSurname 
				+ "\nEmail: " + email + "\tTelephone: " + telephone;
	}
}
