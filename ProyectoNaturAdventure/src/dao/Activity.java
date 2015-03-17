package dao;

public class Activity {
	
	private int codActivity;
	private String name;
	private String description;
	private double pricePerPerson;
	private int duration;
	private int maxPartakers;
	private int minPartakers;
	private Level level;
	
	public int getCodActivity() {
		return codActivity;
	}
	public void setCodActivity(int codActivity){
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
	
	@Override
	public String toString() {
		return "\t" + name + "\n" + description + "\n\n" + "Level: " + level + "--Duration: " + duration + "--MaxPartakers: " 
				+ maxPartakers + "--MinPartakers: " + minPartakers;
	}
	
}
