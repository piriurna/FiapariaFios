package application.models;

import java.time.LocalDate;

public class Collection {

	private int id;
	private String name;
	private LocalDate releaseDate;
	
	public Collection(int id, String name, LocalDate releaseDate) {
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String toString() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
}
