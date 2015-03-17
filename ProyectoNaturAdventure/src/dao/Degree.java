package dao;

public class Degree {
	
	private String name;
	private String description;
	private int degreeCode;
	private String instructorNif;
	
	public Degree() {
		this.name = null;
		this.description = null;
		this.degreeCode = -1;
	}
	
	public Degree(String name, String description, int degreeCode) {
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
	
	public String getInstructorNif() {
		return this.instructorNif;
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
	
	public void setInstructorNif( String instructorNif ) {
		this.instructorNif = instructorNif;
	}
	
	@Override
	public String toString() {
		return "Cod: " + degreeCode + "\nDescription: " + description;
	}
}
