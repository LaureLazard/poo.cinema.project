package controllers;
import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import javax.faces.context.FacesContext;

import controllers.DbConn;
import project.cinema.poo.Film;
import project.cinema.poo.FilmBean;

public class DBO {
    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet result;
    public static PreparedStatement pstmt;
    
    private static List<Film> films = new ArrayList<Film>();
    
    
	public static List<Film> retrieveFromDB() throws SQLException
	{
		films.clear();
		DbConn lc = new DbConn();
		connObj = lc.getLocalConnection();
		stmtObj = connObj.createStatement();
		String sql1="SELECT f_id, f_title, f_synopsis, f_trailer, f_category, f_mpaa, f_year, f_duration, f_poster FROM tbl_films";
		System.err.println("****"+sql1);
		result = stmtObj.executeQuery(sql1);
		while(result.next()) {
			films.add(new Film(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9)));
		}
		lc.setConnectionClose();
		return films;		
	}
	
	
	public static String saveInDB(FilmBean newFilmObj) throws SQLException {

	    int saveResult = 0;
	    System.out.println(newFilmObj.getTitle());
        String navigationResult = "";
        try { 
    		DbConn lc = new DbConn();
    		connObj = lc.getLocalConnection();
    		stmtObj= connObj.createStatement();
            pstmt = connObj.prepareStatement("INSERT INTO tbl_films(f_id, f_title, f_synopsis, f_trailer, f_category, f_mpaa, f_year, f_duration, f_poster) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");         
            pstmt.setString(1, newFilmObj.getId());
            pstmt.setString(2, newFilmObj.getTitle());
            pstmt.setString(3, newFilmObj.getSynopsis());
            pstmt.setString(4, newFilmObj.getL_trailer());
            pstmt.setString(5, newFilmObj.getCategory());
            pstmt.setString(6, newFilmObj.getMpaa());
            pstmt.setString(7, newFilmObj.getYear());
            pstmt.setString(8, newFilmObj.getDuration());
            pstmt.setString(9, newFilmObj.getL_poster());
            saveResult = pstmt.executeUpdate();
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "index.xhtml?faces-redirect=true";
        } else {
            navigationResult = "addFilm.xhtml?faces-redirect=true";
        }
        return navigationResult;
	}
	
    public static String editFromDB(Film filmToUpdate) {
        try {
    		DbConn lc = new DbConn();
    		connObj = lc.getLocalConnection();
    		stmtObj= connObj.createStatement();
            pstmt = connObj.prepareStatement("update tbl_films set f_title=?, f_synopsis=?, f_trailer=?, f_category=?, f_mpaa=?, f_year=?, f_duration=?, f_poster=? where f_id=?");    
            pstmt.setString(1,filmToUpdate.getTitle());  
            pstmt.setString(2,filmToUpdate.getSynopsis());  
            pstmt.setString(3,filmToUpdate.getL_trailer());  
            pstmt.setString(4,filmToUpdate.getCategory());  
            pstmt.setString(5,filmToUpdate.getMpaa()); 
            pstmt.setString(6,filmToUpdate.getYear());
            pstmt.setString(7,filmToUpdate.getDuration());
            pstmt.setString(8,filmToUpdate.getL_poster());
            pstmt.setString(9,filmToUpdate.getId());  
            pstmt.executeUpdate();
            connObj.close();            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
	public static String deleteFromDB(String filmId) throws SQLException {
		DbConn lc = new DbConn();
		connObj = lc.getLocalConnection();
        System.out.println("deleteFilmInDB() : Film Id: " + filmId);
        try {
            pstmt = connObj.prepareStatement("delete from tbl_films where f_id = '"+filmId+"'");  
            pstmt.executeUpdate();          
        } 
        catch(Exception sqlException){
            sqlException.printStackTrace();
            System.out.println(sqlException);
        }      
        lc.setConnectionClose();
        return "/index.xhtml?faces-redirect=true";
	}
	
	public static Film prepareEditObj(String filmId)
	{
		Film editRecord = new Film();
        /* Setting The Particular Student Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
 
        try {
    		DbConn lc = new DbConn();
    		connObj = lc.getLocalConnection(); 
    		stmtObj= connObj.createStatement();
    		result = stmtObj.executeQuery("select * from tbl_films where f_id = '"+filmId+"'");    
            if(result != null) {
            	result.next();
                editRecord.setId(result.getString("f_id"));
                editRecord.setTitle(result.getString("f_title"));
                editRecord.setSynopsis(result.getString("f_synopsis"));
                editRecord.setL_trailer(result.getString("f_trailer"));
                editRecord.setCategory(result.getString("f_category"));
                editRecord.setMpaa(result.getString("f_mpaa")); 
                editRecord.setYear(result.getString("f_year")); 
                editRecord.setDuration(result.getString("f_duration"));
            }
            sessionMapObj.put("editRecordObj", editRecord);
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return editRecord;
	}
	
}
