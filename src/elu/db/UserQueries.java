package elu.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

public class UserQueries {
	/**
	 * TODO: encrypt passwords
	 */
	public static String validate(String email, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement("SELECT nickname, email, password FROM users WHERE email = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception ex) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {}
		} 
		finally {
			DBConnection.close(con);
		}
		return null;
	}
	
	public static boolean register(String nickname, String email, String pwd) {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = DBConnection.getConnection();
				ps = con.prepareStatement("SELECT nickname, email, password FROM users WHERE email = ? AND password = ?");
				ps.setString(1, email);
				ps.setString(2, pwd);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) { // user found in the DB
					return true;
					
				} else { // register user
					ps = con.prepareStatement("INSERT INTO users (nickname, email, password) VALUES (?,?,?)");
					ps.setString(1, nickname);
					ps.setString(2, email);
					ps.setString(3, pwd);
					return ps.executeUpdate() > 0;
				}
			} catch (Exception ex) {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
					return false;
				} catch (IOException e) {}
			} 
			finally {
				DBConnection.close(con);
			}
			return false;
	}
	
	private static Connection getConnection() throws SQLException {
		return DBConnection.getConnection();
	}
}
