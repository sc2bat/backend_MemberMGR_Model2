package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.QMDto;

public class QMDao {
	private QMDao() {}
	private static QMDao qd = new QMDao();
	public static QMDao getInstance() {return qd;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(con != null)con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		} catch (SQLException e) {e.printStackTrace();
		}
	}

	public QMDto getMember(String userid) {
		QMDto qmdto = null;
		String sql = "SELECT * FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qmdto = new QMDto();
				qmdto.setUserid(rs.getString("userid"));
				qmdto.setName(rs.getString("name"));
				qmdto.setPwd(rs.getString("pwd"));
				qmdto.setPhone(rs.getString("phone"));
				qmdto.setEmail(rs.getString("email"));
				qmdto.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
		return qmdto;
	}

	public ArrayList<QMDto> qSelectM() {
		ArrayList<QMDto> list = new ArrayList<QMDto>();
		String sql = "SELECT * FROM member ORDER BY admin, userid";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QMDto qmdto = new QMDto();
				qmdto.setUserid(rs.getString("userid"));
				qmdto.setName(rs.getString("name"));
				qmdto.setPwd(rs.getString("pwd"));
				qmdto.setEmail(rs.getString("email"));
				qmdto.setPhone(rs.getString("phone"));
				qmdto.setAdmin(rs.getInt("admin"));
				list.add(qmdto);
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	public int qInsertM(QMDto qmdto) {
		int result = 0;
//		name userid pwd email phone admin
		String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?)";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qmdto.getName());
			pstmt.setString(2, qmdto.getUserid());
			pstmt.setString(3, qmdto.getPwd());
			pstmt.setString(4, qmdto.getEmail());
			pstmt.setString(5, qmdto.getPhone());
			pstmt.setInt(6, qmdto.getAdmin());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
	}

	public int qUpdateM(QMDto qmdto) {
		int result = 0;
		String sql = "UPDATE member SET name=?, pwd=?, email=?, phone=?, admin=? WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qmdto.getName());
			pstmt.setString(2, qmdto.getPwd());
			pstmt.setString(3, qmdto.getEmail());
			pstmt.setString(4, qmdto.getPhone());
			pstmt.setInt(5, qmdto.getAdmin());
			pstmt.setString(6, qmdto.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}

	public void qDeleteM(String userid) {
		String sql = "DELETE FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
				
	}

	public void qEditAdmin(String userid, int admin) {
		String sql = "UPDATE member SET admin=? WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
