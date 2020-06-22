package elu.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class WeebQueries {
	
	private static final int BUFFER = 10;
	
	public static List<WeebModel> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<WeebModel> out = new ArrayList<WeebModel>(BUFFER);
		try {
			
			con = DBConnection.getConnection();
			ps = con.prepareStatement("SELECT c.id, u.id, u.nickname, c.content, COUNT(l.id) AS likes, c.post_date FROM users u "
					+ "INNER JOIN comments c ON c.user_id = u.id "
					+ "LEFT JOIN likes l ON l.comment_id = c.id "
					+ "GROUP BY c.id "
					+ "ORDER BY c.post_date DESC");
			
			ResultSet rs = ps.executeQuery();
			if (rs == null) return null;
			
			while (rs.next()) {
				int i = 1;
				out.add(new WeebModel(
						rs.getInt(i++),    // id
						rs.getInt(i++),    // user_id
						rs.getString(i++), // nickname
						rs.getString(i++), // content
						rs.getInt(i++),    // likes
						rs.getDate(i++)    // post_date
				));				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {}
		} 
		finally {
			DBConnection.close(con);
		}
		return out;
	}
}
