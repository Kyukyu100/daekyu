package com.yedam.exe;

import java.util.Scanner;

import com.yedam.client.ClientService;
import com.yedam.comment.CommentService;

public class CommentApp {
	
	boolean run = true;
	Scanner sc = new Scanner(System.in);
	CommentService comments = new CommentService();
	

	public CommentApp() {
		if(ClientService.clientInfo.getClientAuth().equals("N")) {
			ClientRun();
		}else if(ClientService.clientInfo.getClientAuth().equals("A")){
			AdminRun();
		}
		
	}
	
	private void ClientRun() {
		System.out.println("사용자 Comment");
		
		while(run) {
			System.out.println("1. 전체 코멘트 보기 | 2. 한줄평 삭제 | 4. 뒤로 가기");
			int menuNo = Integer.parseInt(sc.nextLine());
			switch(menuNo){
				case 1:
					comments.getCommentByAll();
					break;
				case 2:
					comments.deleteCOmmentByAdmin();
					break;
				case 3:
					run = false;
					break;
			}
		}
	}
	private void AdminRun() {
		System.out.println("관리자 Comment");
		while(run) {
			System.out.println("1. 전체 코멘트 보기 | 2. 한줄평 삭제 | 3. 뒤로가기 ");
			int menuNo = Integer.parseInt(sc.nextLine());
			switch(menuNo){
				case 1:
					comments.getCommentByAll();
					break;
				case 2:
					comments.deletCommentByClient();
					break;
				case 3:
					run = false;
					break;
			}
		}
	}
	
}
