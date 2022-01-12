package com.ezen.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyDto;
import com.ezen.board.util.Dbman;
import com.ezen.board.util.Paging;

public class BoardDao {
	private BoardDao() {}
	private static BoardDao itc = new BoardDao();
	public static BoardDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/* paging 추가
	public ArrayList<BoardDto> selectBoard() {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM board ORDER BY num DESC";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				list.add(bdto);
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);
		}
		return list;
	}*/
	public ArrayList<BoardDto> selectBoard(Paging paging) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
//		String sql = "SELECT * FROM board ORDER BY num DESC";
		String sql = "SELECT * FROM ( "
				+ "SELECT * FROM ( "
				+ "SELECT rownum AS rn, b.* FROM ( "
				+ "SELECT * FROM board ORDER BY num DESC) b "
				+ ") WHERE rn >= ? "
				+ ") WHERE rn <= ? ";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getStartNum());
			pstmt.setInt(2, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
				list.add(bdto);
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public void plusReadCount(int num) {
		String sql = "UPDATE board SET readcount = readcount + 1 WHERE num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
	}

	public BoardDto getBoard(int num) {
		BoardDto bdto = null;
		String sql = "SELECT * FROM board WHERE num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto = new BoardDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setPass(rs.getString("pass"));
				bdto.setEmail(rs.getString("email"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setWritedate(rs.getTimestamp("writedate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		} 
		return bdto;
	}

	public void insertBoard(BoardDto bdto) {
		String sql = "INSERT INTO board(num, userid, pass, email, title, content) VALUES(board_seq.nextVal, ?, ?, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getEmail());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
	}

	public void updateBoard(BoardDto bdto) {
		String sql = "UPDATE board SET userid=?, pass=?, email=?, title=?, content=? WHERE num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getUserid());
			pstmt.setString(2, bdto.getPass());
			pstmt.setString(3, bdto.getEmail());
			pstmt.setString(4, bdto.getTitle());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, bdto.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);
		}
	}

	public void deleteBoard(int num) {
		String sql = "DELETE FROM board WHERE num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		
	}

	public void insertReply(ReplyDto rdto) {
		// replynum, boardnum, userid, writedate, content 
		String sql = "INSERT INTO reply(replynum, boardnum, userid, content) VALUES(reply_seq.nextVal, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBoardnum());
			pstmt.setString(2, rdto.getUserid());
			pstmt.setString(3, rdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
	}

	public ArrayList<ReplyDto> selectReply(int num) {
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		String sql = "SELECT * FROM reply WHERE boardnum=? ORDER BY replynum DESC";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyDto rdto = new ReplyDto();
				rdto.setReplynum(rs.getInt("replynum"));
				rdto.setBoardnum(rs.getInt("boardnum"));
				rdto.setUserid(rs.getString("userid"));
				rdto.setWritedate(rs.getTimestamp("writedate"));
				rdto.setContent(rs.getString("content"));
				list.add(rdto);
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public void deleteReply(String replynum) {
		String sql = "DELETE FROM reply WHERE replynum=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(replynum));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {Dbman.close(con, pstmt, rs);
		}
	}

	public int getAllCount() {
		int count = 0;
		String sql = "SELECT COUNT(*) AS cnt FROM board";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return count;
	}

	public int getReplycnt(int num) {
		int cnt = 0;
		String sql = "SELECT COUNT(*) AS cnt FROM reply WHERE boardnum=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return cnt;
	}
}
