package dao;

public class Degrees {
	
	private String description;
	private int degreeCode;
	
	public Degrees(String description, int degreeCode) {
		this.description = description;
		this.degreeCode = degreeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(int degreeCode) {
		this.degreeCode = degreeCode;
	}
	
	@Override
	public String toString() {
		return "Cod: " + degreeCode + "\nDescription: " + description;
	}
}
