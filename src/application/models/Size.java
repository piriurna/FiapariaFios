package application.models;

public class Size {

	private int id;
	private String initials;
	
	public Size(int id, String initials) {
		this.id = id;
		this.initials = initials;
	}

	public int getId() {
		return id;
	}
	
	public String getInitials() {
		return initials;
	}
	
	public String toString() {
		return getInitials();
	}
}
