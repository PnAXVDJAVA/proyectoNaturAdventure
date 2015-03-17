package dao;

public enum StartHour {

	morning,  //08:00 - 12:00
	afternoon, //12:00 - 20:00
	night;  //20:00 - 24:00
	
	public static StartHour getOpcion(String hour) {
		return valueOf(hour);
	}
}
