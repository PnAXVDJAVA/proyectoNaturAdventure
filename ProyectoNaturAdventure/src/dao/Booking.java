package dao;

import java.sql.Date;

public class Booking {
	private int codBooking;
	private Date proposalPerformingDate;
	private int numPartakers;
	private Date bookingDate;
	private String customerNif;
	private StartHour startHour;
	private String status;
	private int codActivity;
	
	public Booking() {
		this.codBooking = -1;
		this.proposalPerformingDate = null;
		this.numPartakers = -1;
		this.bookingDate = null;
		this.customerNif = null;
		this.startHour = null;
		this.codActivity = -1;
	}
	
	public Booking(Date proposalPerformingDate, int numPartakers,
			Date bookingDate, String customerNif, StartHour startHour, int codActivity) {
		this.proposalPerformingDate = proposalPerformingDate;
		this.numPartakers = numPartakers;
		this.bookingDate = bookingDate;
		this.customerNif = customerNif;
		this.startHour = startHour;
		this.codActivity = codActivity;
	}

	public int getCodBooking() {
		return codBooking;
	}

	public void setCodBooking(int codBooking) {
		this.codBooking = codBooking;
	}

	public Date getProposalPerformingDate() {
		return proposalPerformingDate;
	}

	public void setProposalPerformingDate(Date proposalPerformingDate) {
		this.proposalPerformingDate = proposalPerformingDate;
	}

	public int getNumPartakers() {
		return numPartakers;
	}

	public void setNumPartakers(int numPartakers) {
		this.numPartakers = numPartakers;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getCustomerNif() {
		return customerNif;
	}

	public void setCustomerNif(String customerNif) {
		this.customerNif = customerNif;
	}

	public StartHour getStartHour() {
		return startHour;
	}

	public void setStartHour(StartHour startHour) {
		this.startHour = startHour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCodActivity() {
		return codActivity;
	}

	public void setCodActivity(int codActivity) {
		this.codActivity = codActivity;
	}
	
}
