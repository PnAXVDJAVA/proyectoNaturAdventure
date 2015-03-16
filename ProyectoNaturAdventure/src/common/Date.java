package common;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {

	private Calendar dateCalendar = new GregorianCalendar();
	private static final int[] monthsDays = {31,28,31,30,31,30,31,31,30,31,30,31};
	public Date() {
		dateCalendar.set(dateCalendar.get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH)+1,
						dateCalendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public Date(int day, int month, int year){
		dateCalendar.set(year, month, day);
	}
	
	public static boolean isCorrect(int day, int month, int year) {
		if(year < 1 || month < 1 || month > 12 || day < 1)
			return false;
			
		boolean leap = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
		if(leap) {
			int[] monthsDaysLeap = monthsDays;
			monthsDaysLeap[1] = 29;
			if (day > monthsDaysLeap[month-1])
				return false;				
		}
		else if (day > monthsDays[month-1])
			return false;				
		return true;	
	}
	
	public boolean isCorrect() {
		return isCorrect(getDay(), getMonth(), getYear());
	}

	public int getDay() {
		return dateCalendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public void setDay(int day) {
		dateCalendar.set(getYear(), getMonth(), day);
	}
	
	public int getMonth() {
		return dateCalendar.get(Calendar.MONTH);
	}
	
	public void setMonth(int month) {
		dateCalendar.set(getYear(), month, getDay());
	}
	
	public int getYear() {
		return dateCalendar.get(Calendar.YEAR);
	}
	
	public void setYear(int year) {
		dateCalendar.set(year, getMonth(), getDay());
	}

	public Calendar getDateCalendar() {
		return dateCalendar;
	}
	
	public int compareTo(Date date) {
		if (getYear() > date.getYear())
			return 1;
		else if (getYear() < date.getYear())
			return -1;
		else {
			if (getMonth() > date.getMonth())
				return 1;
			else if (getMonth() < date.getMonth())
				return -1;
			else {
				if (getDay() > date.getDay())
					return 1;
				else if (getDay() < date.getDay())
					return -1;
				else
					return 0;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getDay() + "/" + (getMonth()) + "/" + getYear());
		return sb.toString();
	}
}
