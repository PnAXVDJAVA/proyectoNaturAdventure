package dao;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	private int codActivity;
	private String name;
	private String description;
	private double pricePerPerson;
	private int duration;
	private int maxPartakers;
	private int minPartakers;
	private Level level;
	private List<Instructor> specializedInstructors; //////////////////////////////
	
	public Activity() {
		this.codActivity = -1;
		this.name = null;
		this.description = null;
		this.pricePerPerson = -1;
		this.duration = -1;
		this.maxPartakers = -1;
		this.minPartakers = -1;
		this.level = null;
		this.specializedInstructors = null;
	}
	
	public int getCodActivity() {
		return codActivity;
	}

	public void setCodActivity(int codActivity) {
		this.codActivity = codActivity;
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

	public double getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getMaxPartakers() {
		return maxPartakers;
	}

	public void setMaxPartakers(int maxPartakers) {
		this.maxPartakers = maxPartakers;
	}

	public int getMinPartakers() {
		return minPartakers;
	}

	public void setMinPartakers(int minPartakers) {
		this.minPartakers = minPartakers;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setSpecializedInstructors(  List<Instructor> list ) {
		this.specializedInstructors = list;
	}
	
	public List<Instructor> getSpecializedInstructors() {
		return this.specializedInstructors;
	}
	
	public void addSpecializedInstructor(  Instructor instructor ) {
		if( specializedInstructors == null ) {
			this.specializedInstructors = new LinkedList<>();
		}
		specializedInstructors.add( instructor );
	}

	@Override
	public String toString() {
		return name + "\n" + description + "\n" + "Level: " + level + "--Duration: " + duration + "--MaxPartakers: " 
				+ maxPartakers + "--MinPartakers: " + minPartakers;
	}
}
