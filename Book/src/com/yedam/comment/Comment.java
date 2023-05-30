package com.yedam.comment;

public class Comment {
//	COMMENT_ID       NOT NULL NUMBER        
//	CLIENT_ID                 VARCHAR2(20)  
//	BOOK_ID                   NUMBER        
//	ONE_LINE_COMMENT          VARCHAR2(200) 
	private int commentId;
	private String clientId;
	private int bookId;
	private String oneLineComment;
	private String ClientName;
	private String bookName;
	
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getOneLineComment() {
		return oneLineComment;
	}
	public void setOneLineComment(String oneLineComment) {
		this.oneLineComment = oneLineComment;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	
	
}
