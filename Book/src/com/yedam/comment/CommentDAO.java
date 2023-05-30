package com.yedam.comment;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class CommentDAO extends DAO{
	
	private static CommentDAO commentDao = null;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		if(commentDao == null) {
			commentDao = new CommentDAO();
		}
		return commentDao;
	}
	
	// 코멘트 입력 
//	public int insertComment(String clientId, int bookId, String comment) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "INSERT INTO comment1 VALUES((SELECT (COUNT(*)+1) FROM book),?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, clientId);
//			pstmt.setInt(2, bookId);
//			pstmt.setString(3, comment);
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return result;
//	}
	// 전체보기(공용)
	public List<Comment> getAllComment(){
		List<Comment> list = new ArrayList<>();
		Comment comment = null;
		try {
			conn();
			String sql = "SELECT comment_id, c.client_id, c.book_id ,b.book_name, one_line_comment\r\n"
					+ "FROM comment1 c join book b ON(c.book_id = b.book_id)\r\n"
					+ "ORDER BY 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment = new Comment();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setClientId(rs.getString("client_id"));
				comment.setBookId(rs.getInt("book_id"));
				comment.setOneLineComment(rs.getString("one_line_comment"));
				comment.setBookName(rs.getString("book_name"));
				list.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	
	
	// 코멘트 보기(특정 책)
	public List<Comment> getCommentByBook(int bookId) {
		List<Comment> list = new ArrayList<>();
		Comment comment = null;
		try {
			conn();
			String sql = "SELECT *\r\n"
					+ "FROM comment1\r\n"
					+ "WHERE book_id = ?\r\n"
					+ "ORDER BY 2";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment = new Comment();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setClientId(rs.getString("client_id"));
				comment.setBookId(rs.getInt("book_id"));
				comment.setOneLineComment(rs.getString("one_line_comment"));
				list.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}

	// 코멘트 보기(특정 사용자)
	public List<Comment> getCommentByClient(String clientId) {
		List<Comment> list = new ArrayList<>();
		Comment comment = null;
		try {
			conn();
			String sql = "SELECT *\r\n"
					+ "FROM comment1\r\n"
					+ "WHERE client_id = ?\r\n"
					+ "ORDER BY 3";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				comment = new Comment();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setClientId(rs.getString("client_id"));
				comment.setBookId(rs.getInt("book_id"));
				comment.setOneLineComment(rs.getString("one_line_comment"));
				list.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}

	// 코멘트 삭제(사용자 본인것만)
	public int deleteCommentClient(int commentId, String clientId) {
		int result =0;
		try {
			conn();
			String sql = "DELETE comment SET comment_id = ? AND client_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentId);
			pstmt.setString(2, clientId);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	// 코멘트 삭제(관리자용)
	public int deleteCommentAdmin(int commentId) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE comment SET comment_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentId);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	
}
