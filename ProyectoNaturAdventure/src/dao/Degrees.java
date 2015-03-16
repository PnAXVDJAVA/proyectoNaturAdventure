package dao;

public class Degrees {
	
	private String name;
	private String description;
	private int degreeCode;
	
	public Degrees(String name, String description, int degreeCode) {
		this.name = name;
		this.description = description;
		this.degreeCode = degreeCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
