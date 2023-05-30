package com.yedam.Book;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class BookDAO extends DAO{
	
	// 싱글톤
	private static BookDAO bookDao = null;

	private BookDAO(){
		
	}
	public static BookDAO getInstance() {
		if(bookDao == null) {
			bookDao = new BookDAO();
		}
		return bookDao;
	}
	
	// 도서 조회(책번호, 책이름, 카테고리, 대출가능여부) 공용
	public List<Book> getBookList(){
		List<Book> list = new ArrayList<>();
		Book book = null;
		try {
			conn();
			String sql = "SELECT * FROM book ORDER BY book_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setBookKate(rs.getString("book_kate"));
				book.setBookGameCheck(rs.getString("book_game_check"));
				book.setBookGameHint(rs.getString("book_game_hint"));
				list.add(book);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	// 단일 도서 조회 (공용)
	public Book getBook(int id) {
		Book book = null;
		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setBookKate(rs.getString("book_kate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return book;
	}
	
	// 도서 등록(관리자용)
	public int insertBook(Book book) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO book VALUES((SELECT (COUNT(*)+1) FROM book),?,?,'Y','')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookKate());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	// 도서 삭제(관리자용)
	public int deleteBook(String id) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE book WHERE book_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	// 카테고리 검색
	public List<Book> sarchKateBook(String kate){
		List<Book> list = new ArrayList<>();
		Book book = null;
		try {
			conn();
			String sql = "SELECT * FROM BOOK WHERE book_kate LIKE('%' || ? || '%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setBookKate(rs.getString("book_kate"));
				book.setBookId(rs.getInt("book_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
//////////////////////////////////////////////////////////////////
	// 게임용
	
	// 랜덤 단어 가져오기
	public Book bookGame(int bookId) {
		Book newWord = null;
		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				newWord = new Book();
				newWord.setBookId(rs.getInt("book_id"));
				newWord.setBookName(rs.getString("book_name"));
				newWord.setBookKate(rs.getString("book_kate"));
				newWord.setBookGameCheck(rs.getString("book_game_check"));
				newWord.setBookGameHint(rs.getString("book_game_hint"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return newWord;
	}
	// 랜덤 단어 맞추면 스코어 업데이트
	public int scoreUp(int Up, String clientId) {
		 int result =  0;
			try {
				conn();
				String sql = "UPDATE client SET game_score = (game_score+?) WHERE client_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Up);
				pstmt.setString(2, clientId);
				result = pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				disconn();
			}
		return result;
	}
		
	// 랜덤단어 종료시 모든 book_game_check 초기화
	public void clearCheck() {
			try {
				conn();
				String sql = "UPDATE book SET book_game_check = 'Y'";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				disconn();
			}

	}
	
	// 단어 맞추면 해당 단어 리스트에서 제외
	public int exceptWord(int bookId) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE book SET book_game_check = 'N' WHERE book_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			result =  pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return result;
	}
	
	
	// 힌트 가져오기
//	public Book hintBook(int bookId) {
//		Book newWord = null;
//		try {
//			conn();
//			String sql = "SELECT * FROM book WHERE = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bookId);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				newWord = new Book();
//				newWord.setBookId(rs.getInt("book_id"));
//				newWord.setBookName(rs.getString("book_name"));
//				newWord.setBookKate(rs.getString("book_kate"));
//				newWord.setBookGameCheck(rs.getString("bookGameCheck"));
//				newWord.setBookGameHint(rs.getString("book_game_hint"));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return newWord;
//	}
//	
	
	
}



