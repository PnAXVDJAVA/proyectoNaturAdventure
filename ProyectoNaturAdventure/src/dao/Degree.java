package dao;

public class Degree {
	private String instructorNif;
	private int codDegree;
	private String description;
	private String name;
	
	public Degree() {
		this.instructorNif = null;
		this.codDegree = -1;
		this.description = null;
		this.name = null;
	}
	
	public String getInstructorNif() {
		return instructorNif;
	}

	public void setInstructorNif(String instructorNif) {
		this.instructorNif = instructorNif;
	}

	public int getCodDegree() {
		return codDegree;
	}

	public void setCodDegree(int codDegree) {
		this.codDegree = codDegree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {/////////////////////////////////////////////////////////////////////////////////////
		return "Cod: " + codDegree + "; Name: " + name + "; Description: " + description;
	}
}
