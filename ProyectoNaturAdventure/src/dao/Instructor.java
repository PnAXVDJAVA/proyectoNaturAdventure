package dao;

import java.sql.Date;
import java.util.List;
import common.MyAddress;

public class Instructor {
	private String name;
	private String NIF;
	private MyAddress address;
	private int telephone;
	private Date date;
	private String bankAccount;
	private List<Degrees> degrees;
	
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
			int telephone, Date date, String bankAccount, List<Degrees> degrees) {
		this.name = name;
		this.NIF = nIF;
		this.address = address;
		this.telephone = telephone;
		this.date = date;
		this.bankAccount = bankAccount;
		this.degrees = degrees;
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
	public List<Degrees> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<Degrees> degrees) {
		this.degrees = degrees;
	}
	
	
}
