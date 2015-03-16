package dao;

import java.util.List;

public class Instructor {

	private String name;
	private String NIF;
	private Address address;
	private int telephone;
	private Date date;
	private String bankAccount;
	private List<Degrees> degrees;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		NIF = nIF;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public List<Degrees> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<Degrees> degrees) {
		this.degrees = degrees;
	}
	
	
}
