package com.yedam.comment;

import java.util.List;
import java.util.Scanner;

import com.yedam.Book.BookService;
import com.yedam.client.ClientService;

public class CommentService {
	
	
	Scanner sc = new Scanner(System.in);
	ClientService cs = new ClientService();
	BookService bs = new BookService();
	
	// 입력
	
	// 전체보기(공용)
	public void getCommentByAll() {
		System.out.println("전체 한줄평 보기");
		List<Comment> list = CommentDAO.getInstance().getAllComment();
		for(int i=0; i<list.size(); i++) {
			System.out.printf("%04d : " ,(i+1));
			System.out.println(" 작성자명 : " + list.get(i).getClientId() + " 도서 이름 : " + list.get(i).getBookName());
			System.out.println("Comment : " +list.get(i).getOneLineComment());
		}
	}
	
	
	// 보기(사용자용)
	public void getCommentByClinet(){
		System.out.println("내 한줄평 보기");
		List<Comment> list = CommentDAO.getInstance().getCommentByClient(ClientService.clientInfo.getClientId());
		for(int i=0; i<list.size(); i++) {
			System.out.printf("%04d - " ,(i+1));
			System.out.println(" 도서 이름 : " + list.get(i).getBookName());
			System.out.println("Comment : " +list.get(i).getOneLineComment());
		}
	}
	
	// 보기(관리자용)
	public void getCommentByAdime() {
		System.out.println("사용자 ");
	}
	
	// 특정 책 보기
	public void getCommentByBook() {
		System.out.println("한줄평을 볼 도서 ID를 입력해주세요");
		int no = Integer.parseInt(sc.nextLine());
		CommentDAO.getInstance().getCommentByBook(no);
	}
	
	// 삭제(사용자용)
	public void deletCommentByClient() {
		System.out.println("내 한줄평 삭제");
		System.out.println("내 코멘트 목록 :");
		List<Comment> list = CommentDAO.getInstance().getAllComment();
		for(int i=0; i<list.size(); i++) {
			System.out.printf("Commnet id : %04d " ,list.get(i).getCommentId());
			System.out.println(" 도서 이름 : " + list.get(i).getBookName());
			System.out.println("Comment : " +list.get(i).getOneLineComment());
		}
		System.out.println("삭제할 Comment ID 입력>");
		
		int del = Integer.parseInt(sc.nextLine());
		int result = CommentDAO.getInstance().deleteCommentClient(del, ClientService.clientInfo.getClientId());
		if(result > 0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
	}
	
	
	// 삭제(관리자요)
	public void deleteCOmmentByAdmin() {
		
	}
	
	
	
	
	
}
