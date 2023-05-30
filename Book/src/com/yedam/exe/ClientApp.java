package com.yedam.exe;

import java.util.Scanner;

import com.yedam.Book.BookDAO;
import com.yedam.Book.BookService;
import com.yedam.client.ClientService;
import com.yedam.loan.LoanService;

public class ClientApp {
	
	boolean run = true;
	Scanner sc = new Scanner(System.in);
	BookService bs = new BookService();
	ClientService cs = new ClientService();
	LoanService ls = new LoanService();
	
	public ClientApp(){
		run();
	}
	private void run() {
		System.out.println("사용자 메뉴");
		while(run) {
			System.out.println("1. 도서 메뉴 | 2. 대출 메뉴 | 3. 회원 정보 | 5. 로그아웃 ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				book();
				break;
			case 2:
				loan();
				break;
			case 3:
				client();
				break;
			case 4:
				break;
			case 5:
				ClientService.clientInfo = null;
				run = false;
				break;
			default:
				System.out.println("없는 메뉴 선택 🤦‍♀️🤦‍♀️🤦‍♂️🤦‍♂️");
			}
		}
	}
	
	private void book() {
		System.out.println("도서 메뉴");
		boolean borun = true;
		while(borun) {
			System.out.println("1. 전체 도서 조회 | 2. 단일 도서 조회 | 3. 도서 맞추기 게임 | 4. 한줄평 보기  | 5. 카테고리 검색 "
					+ "| 6. 게임 초기화 | 7. 뒤로 가기");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				bs.getBookList();
				break;
			case 2:
				bs.getBook();
				break;
			case 3:
				bs.getRandomBook();
				break;
			case 4:
				new CommentApp();
				break;
			case 5:
				bs.sarchKate();
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
	
	private void loan() {
		System.out.println("대출 메뉴");
		boolean lorun = true;
		while(lorun) {
			System.out.println("1. 미납 도서 조회 | 2. 도서 대출 | 3. 도서 반납 |  4. 대출 히스토리 | 5. 뒤로 가기");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				ls.getClientNotLoanList();
				break;
			case 2:
				ls.loanBook();
				break;
			case 3:
				ls.returnBook();
				break;
			case 4:
				ls.getClientLoanList();
				break;
				
			case 5:
				lorun = false;
				break;
			default:
				System.out.println("없는 메뉴 선택 🤦‍♀️🤦‍♀️🤦‍♂️🤦‍♂️");
			}
		}
	}
	
	private void client() {
		System.out.println("회원 정보");
		boolean clrun = true;
		while(clrun) {
			System.out.println("1. 내 정보 | 2. 비밀번호 수정 | 3. 회원 탈퇴 | 6. 종료");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				cs.getClientInfo();
				break;
			case 2:
				cs.updatePw();
				break;
			case 3:
				cs.deleteClient();
				clrun= false;
				break;
			case 6:
				clrun = false;
				break;
			default:
				System.out.println("없는 메뉴 선택 🤦‍♀️🤦‍♀️🤦‍♂️🤦‍♂️");
			}
		}
	}
}
