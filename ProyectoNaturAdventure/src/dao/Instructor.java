package dao;

import java.sql.Date;
import java.util.List;
import common.MyAddress;

public class Instructor {
	private String NIF;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private MyAddress address;
	private int telephone;
	private Date date;
	private String email;
	private String bankAccount;
	private List<Degree> degrees;
	
	public Instructor() {
		this.name = null;
		this.NIF = null;
		this.address = null;
		this.telephone = -1;
		this.date = null;
		this.bankAccount = null;
		this.degrees = null;
	}
	
	public Instructor(String name, String nIF, MyAddress address,
			int telephone, Date date, String bankAccount, List<Degree> degrees) {
		this.name = name;
		this.NIF = nIF;
		this.address = address;
		this.telephone = telephone;
		this.date = date;
		this.bankAccount = bankAccount;
		this.degrees = degrees;
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
	public MyAddress getAddress() {
		return address;
	}
	public void setAddress(MyAddress address) {
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
	public List<Degree> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}
	
	
}
