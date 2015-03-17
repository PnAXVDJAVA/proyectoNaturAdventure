package dao;

import java.sql.Date;
import java.util.List;

public class Instructor {
	private String nif;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String address;
	private int telephone;
	private Date dateOfBirth;
	private String email;
	private String bankAccount;
	private String userID;
	private String password;
	private List<Degree> degrees;
	
	public Instructor() {
		this.nif = null;
		this.name = null;
		this.firstSurname = null;
		this.secondSurname = null;
		this.address = null;
		this.telephone = -1;
		this.dateOfBirth = null;
		this.email = null;
		this.bankAccount = null;
		this.userID = null;
		this.password = null;
		this.degrees = null;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + " || Surname: " + firstSurname + " " + secondSurname + " || Adress: " + address 
				+ "\nTelephone: " + telephone + " || Email: " + email + " || DateOfBirth: " + dateOfBirth 
				+ "\nBancAccount: " + bankAccount + " || UserID: " + userID
				+ "\nDegrees: ");
		for (Degree d: degrees)
			sb.append("\t"+d.toString()+"\n");
		return sb.toString();
	}
}
