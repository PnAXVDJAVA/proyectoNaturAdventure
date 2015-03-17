package dao;

public enum BookingStatus {
	
	pending,
	accepted,
	denied;
	
	public static BookingStatus getOpcion( String opcion ) {
		return valueOf( opcion );
	}

}
