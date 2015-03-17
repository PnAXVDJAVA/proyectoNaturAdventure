package dao;

public class Customer {

	private String NIF;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String email;
	private int telephone;
	
	public Customer() {
		this.NIF = null;
		this.name = null;
		this.firstSurname = null;
		this.secondSurname = null;
		this.email = null;
		this.telephone = -1;
	}
	
	public Customer(String nIF, String name, String firstSurname,
			String secondSurname, String email, int telephone) {
		super();
		NIF = nIF;
		this.name = name;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
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
}
