package common;

public class MyAddress {
	private String dir;
	private int postcode;
	private String town;
	private String province;

	public MyAddress(String dir, int postcode, String town, String province) {
		this.dir = dir;
		this.postcode = postcode;
		this.town = town;
		this.province = province;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
