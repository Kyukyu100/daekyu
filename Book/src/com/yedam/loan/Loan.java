package com.yedam.loan;

import java.sql.Date;

public class Loan {
//	LOAN_ID    NOT NULL NUMBER   
//	CLIENT_ID           VARCHAR2(20)
//	BOOK_ID             VARCHAR2(20) 
//	LOAN_DATE           DATE         
//	LOAN_CHECK          CHAR(1)   
	private int loanId;
	private String clientId;
	private int bookId;
	private String bookName;
	private Date loanDate;
	private Date returnDate;
	private String loanCheck;
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getLoanCheck() {
		return loanCheck;
	}
	public void setLoanCheck(String loanCheck) {
		this.loanCheck = loanCheck;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
