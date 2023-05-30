package com.yedam.loan;

import java.util.ArrayList;
import java.util.List;

import com.yedam.Book.Book;
import com.yedam.common.DAO;

public class LoanDAO extends DAO{
	
	public static LoanDAO loanDao = null;
	
	private LoanDAO() {
		
	}
	public static LoanDAO getInstance() {
		if(loanDao == null) {
			loanDao = new LoanDAO();
		}
		return loanDao;
	}
	
	// 대출 확인
	public Loan checkLoan(String id, int bookId){
		Loan loan = null;
		try {
			conn();
			String sql = "SELECT * FROM loan WHERE loan_check = 'Y' AND book_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setClientId(rs.getString("client_id"));
				loan.setBookId(rs.getInt("book_id"));
				loan.setLoanDate(rs.getDate("loan_date"));
				loan.setReturnDate(rs.getDate("return_date"));
				loan.setLoanCheck(rs.getString("loan_check"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}	
		return loan;
	}
	
	
	
	// 대출
	public int loanBook(String clientId, int bookId) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO loan VALUES((SELECT (COUNT(*)+1) FROM loan),?,?,sysdate,(sysdate+14),'Y')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			pstmt.setInt(2, bookId);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	// 대출시 book_loan Y으로 변경
//	public void loanLoan(int bookId) {
//		try {
//			conn();
//			String sql = "UPDATE book SET book_loan='Y' WHERE book_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bookId);
//			pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//	}
	// 반납시 book_loan N으로 변경
//	public void returnLoan(int bookId) {
//		try {
//			conn();
//			String sql = "UPDATE book SET book_loan='N' WHERE book_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bookId);
//			pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//	}
	// 반납
		public int returnBook(String id, int bookId) {
			int result = 0;
			try {
				conn();
				String sql = "UPDATE loan SET loan_check ='N' WHERE client_id = ? AND book_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, bookId);
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				disconn();
			}
			return result;
		}
	// 대출 리스트(관리자)
	public List<Loan> getAdimeLoanList(){
		List<Loan> list = new ArrayList<>();
		Loan loan = null;
		try {
			conn();
			String sql = "SELECT l.loan_id, l.client_id, l.book_id, b.book_name, l.loan_date, return_date, loan_check\r\n"
					+ "FROM loan l join book b ON(l.book_id = b.book_id)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setClientId(rs.getString("client_id"));
				loan.setBookId(rs.getInt("book_id"));
				loan.setBookName(rs.getString("book_name"));
				loan.setLoanDate(rs.getDate("loan_date"));
				loan.setReturnDate(rs.getDate("return_date"));
				loan.setLoanCheck(rs.getString("loan_check"));
				list.add(loan);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}	
		return list;
	}
	
	// 대출 리스트(사용자)
	public List<Loan> getClientLoanList(String id){
		List<Loan> list = new ArrayList<>();
		Loan loan = null;
		try {
			conn();
			String sql = "SELECT l.loan_id, l.client_id, l.book_id, b.book_name, l.loan_date, return_date, loan_check\r\n"
					+ "FROM loan l join book b ON(l.book_id = b.book_id) WHERE client_id = ? AND loan_check='N' ORDER BY loan_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setClientId(rs.getString("client_id"));
				loan.setBookId(rs.getInt("book_id"));
				loan.setBookName(rs.getString("book_name"));
				loan.setLoanDate(rs.getDate("loan_date"));
				loan.setReturnDate(rs.getDate("return_date"));
				loan.setLoanCheck(rs.getString("loan_check"));
				list.add(loan);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}	
		return list;
	}

	// 대출 histroy(사용자)
	public List<Loan> getAllLoan(String id){
		List<Loan> list = new ArrayList<>();
		Loan loan = null;
		try {
			conn();
			String sql = "SELECT l.loan_id, l.client_id, l.book_id, b.book_name, l.loan_date, return_date, loan_check\r\n"
					+ "FROM loan l join book b ON(l.book_id = b.book_id) WHERE client_id = ? ORDER BY loan_id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				loan = new Loan();
				loan.setLoanId(rs.getInt("loan_id"));
				loan.setClientId(rs.getString("client_id"));
				loan.setBookId(rs.getInt("book_id"));
				loan.setBookName(rs.getString("book_name"));
				loan.setLoanDate(rs.getDate("loan_date"));
				loan.setReturnDate(rs.getDate("return_date"));
				loan.setLoanCheck(rs.getString("loan_check"));
				list.add(loan);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}	
		return list;
	}
	// 한줄평 남기기 
	public int addComment(String clientId, String comment, int bookId) {
		int result = 0; 
		try {
			conn();
			String sql = "INSERT INTO comment1 VALUES((SELECT (COUNT(*)+1) FROM book),"
					+ "?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			pstmt.setInt(2, bookId);
			pstmt.setString(3, comment);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	// 대출전 도서 검색 
	public List<Book> searchBookName(String name) {
		List<Book> list = new ArrayList<>();
		Book book = null;
		try {
			conn();
			String sql = "SELECT * FROM book WHERE book_name LIKE('%' || ? || '%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("book_id"));
				book.setBookName(rs.getString("book_name"));
				book.setBookKate(rs.getString("book_kate"));
				list.add(book);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	
	
}
