package com.yedam.Book;

public class Book {
//	book_id varchar2(20) primary key,
//	book_name varchar2(20),
//	book_kate varchar(20),
//	book_loan char); 
	private int bookId;
	private String bookName;
	private String bookKate;
	private String bookGameCheck;
	private String bookGameHint;

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookKate() {
		return bookKate;
	}
	public void setBookKate(String bookKate) {
		this.bookKate = bookKate;
	}
	public String getBookGameCheck() {
		return bookGameCheck;
	}
	public void setBookGameCheck(String bookGameCheck) {
		this.bookGameCheck = bookGameCheck;
	}
	public String getBookGameHint() {
		return bookGameHint;
	}
	public void setBookGameHint(String bookGameHint) {
		this.bookGameHint = bookGameHint;
	}

	
	
}
