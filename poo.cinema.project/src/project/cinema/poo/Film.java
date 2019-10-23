package project.cinema.poo;

import java.sql.SQLException;
import java.util.List;

import controllers.DBO;

public class Film {

	private String title;
	private String synopsis;
	private String duration;
	private String l_trailer;
	private String category;
	private String mpaa;
	private String year;
	private String l_poster;
	private String id;
	
	public Film() {
	}

	public Film(String id, String title, String synopsis, String l_trailer, String category, String mpaa,   
			String year, String duration, String l_poster) {
		super();
		this.title = title;
		this.synopsis = synopsis;
		this.duration = duration;
		this.l_trailer = l_trailer;
		this.category = category;
		this.mpaa = mpaa;
		this.year = year;
		this.l_poster = l_poster;
		this.id = id;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMpaa() {
		return mpaa;
	}
	public void setMpaa(String mpaa) {
		this.mpaa = mpaa;
	}
	public String getL_poster() {
		return l_poster;
	}
	public void setL_poster(String l_poster) {
		this.l_poster = l_poster;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getL_trailer() {
		return l_trailer;
	}
	public void setL_trailer(String l_trailer) {
		this.l_trailer = l_trailer;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
    public List<Film> getFilms() throws SQLException{
    	return DBO.retrieveFromDB();
    }
}
