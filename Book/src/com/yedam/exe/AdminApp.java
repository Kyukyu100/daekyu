package com.yedam.exe;

import java.util.Scanner;

import com.yedam.Book.BookDAO;
import com.yedam.Book.BookService;
import com.yedam.client.ClientService;
import com.yedam.loan.LoanService;

public class AdminApp {

	private boolean run = true;
	Scanner sc = new Scanner(System.in);
	BookService bs = new BookService();
	ClientService  cs = new ClientService();
	LoanService ls = new LoanService();
	
	public AdminApp(){
		run();
	}
	
	private void run() {
		System.out.println("관리자 메뉴");
		while(run){
			System.out.println("1. 회원 | 2. 도서 | 3. 대출 | 8. 로그아웃" );
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu){
			case 1:
				client();
				break;
			case 2:
				book();
				break;
			case 3:
				loan();
				break;
			case 8:
				ClientService.clientInfo = null;
				run = false;
				break;
			}
		}
	}
	
	// 회원
	// 전체보기, 수정, 삭제
	private void client() {
		System.out.println("회원 메뉴");
		boolean clrun = true;
		while(clrun) {
			System.out.println("1. 전체 회원 조회 | 2. 회원 정보 조회 | 3. 회원 정보 변경 | 4. 회원 삭제 |5. 뒤로 가기");
			int clmenu = Integer.parseInt(sc.nextLine());
			switch(clmenu) {
				case 1:
					// 전체보기
					cs.getClientList();
					break;
				case 2:
					cs.getClientInfo();
					break;
				case 3:
					cs.updatePw();
					break;
				case 4:
					cs.deleteClient();
					break;
				case 5:
					clrun = false;
					break;
				default:
					System.out.println("없는 메뉴 선택 ");
			}
		}
	}
	// 책
	private void book() {
		System.out.println("도서 메뉴");
		boolean borun = true;
		while(borun) {
			System.out.println("1. 도서 전체 리스트  | 2. 한줄평 보기 | 3. 도서 등록 | 4. 도서 삭제 | 5. 도서 맞추기 게임  | "
					+ "6. 게임 초기화 | 7. 뒤로 가기");
			int bomenu = Integer.parseInt(sc.nextLine());
			switch(bomenu) {
				case 1:
					bs.getBookList();
					break;
				case 2:
					new CommentApp();
					break;
				case 3:
					bs.insertBook();
					break;
				case 4:
					bs.deleteBook();
					break;
				case 5:
					bs.getRandomBook();
					break;
				case 6:
					BookDAO.getInstance().clearCheck();
				case 7:
					borun = false;
					break;	
				default:
					System.out.println("없는 메뉴 선택 🤦‍♀️🤦‍♀️🤦‍♂️🤦‍♂️");
			}
		}
		
	}
	// 대출 
	private void loan() {
		System.out.println("대출 메뉴");
		boolean lorun = true;
		while(lorun) {
			System.out.println("1. 대출 리스트 | 2. 도서 대출 | 3. 도서 반납 | 4. 뒤로가기 ");
			int lomenu = Integer.parseInt(sc.nextLine());
			switch(lomenu) {
				case 1:
					ls.getAdminLoanList();
					break;
				case 2:
					ls.loanBook();
					break;
				case 3:
					ls.returnBook();
					break;
				case 4:
					lorun = false;
					break;
				default:
					System.out.println("없는 메뉴 선택 🤦‍♀️🤦‍♀️🤦‍♂️🤦‍♂️");
			}
		}
	}
	
}
