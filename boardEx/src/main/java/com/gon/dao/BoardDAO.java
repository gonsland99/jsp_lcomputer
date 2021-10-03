package com.gon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.gon.database.DBConnection;
import com.gon.vo.Board;

public class BoardDAO {
	private static BoardDAO dao = null;
	
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	public ArrayList<Board> getUsers(int page){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> bList = null;
		int pageNum = (page-1)*3;		// 0,3,6,9(페이지 목록갯수)
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("select		@ROWNUM := @ROWNUM -1 as ROWNUM,\n")
					.append("			ta.*\n")
					.append("from		board ta,\n")
					.append("			(select @rownum := (select COUNT(*)-?+1 from board ta)) tb\n")
					.append("order by   b_idx desc\n")
					.append("limit		?,3 \n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs = pstmt.executeQuery();
			bList = new ArrayList<Board>();
			
			while(rs.next()) {
				Board board = new Board();
				board.setB_rownum(rs.getInt("ROWNUM"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_writer(rs.getString("b_writer"));
				board.setB_date(rs.getString("b_date"));
				
				bList.add(board);
			}
		} catch(Exception e) {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException se) {
					se.printStackTrace();
			}
		}
		return bList;
	}
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;	//idx번호
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select max(b_idx) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}
			else {
				num = 1;
			}
			
			String sql = "insert into board(b_idx,b_title,b_content,b_date,b_writer,u_idx)"
					+ " values(?,?,?,sysdate(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getB_title());
			pstmt.setString(3, board.getB_content());
			pstmt.setString(4, board.getB_writer());
			pstmt.setInt(5, num);
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("SQLException: "+ex.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int getUsersCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select count(*) count from board";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(Exception e) {
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
