package project.cinema.poo;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import project.cinema.poo.Film;
import controllers.DBO;

@ManagedBean(name="film")
@SessionScoped

public class FilmBean {

	public String id, title, synopsis, duration, l_trailer, category, mpaa, year, l_poster;
	public List<Film> films = new ArrayList<Film>();

	//Recuperer la liste des films
    public List<Film> getFilms() throws SQLException{
    	return DBO.retrieveFromDB();
    }
	
	//inserer un film
    public String saveFilmDetails() throws SQLException {
    	System.out.println(this.getTitle());
        return DBO.saveInDB(this);
    }
	
    public String deleteFilm(String filmId) throws SQLException {
        return DBO.deleteFromDB(filmId);
    }
	
    public String updateFilm(Film film) {
    	return DBO.editFromDB(film);
    }
    
    public static String editFilmRecord(String filmId) {
		Film editRecord = new Film();
        editRecord = DBO.prepareEditObj(filmId);
        System.out.println("editFilmRecord() : Film Id: " + editRecord.getId());
        return "/editFilm.xhtml?faces-redirect=true";
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
	
	

}
