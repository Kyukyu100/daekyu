package com.yedam.exe;

import java.util.Scanner;
import com.yedam.client.ClientService;


public class Application {
	
	private boolean run = true;
	Scanner sc = new Scanner(System.in);
	ClientService cs = new ClientService();
	
	Application(){
		run();
		
	}
	
	public void run() {
		while(run) {
			if(ClientService.clientInfo == null) {
				System.out.println("====== 도서 대출 관리 시스템 ======");
				System.out.println("1. 로그인  | 2. 회원 가입  | 3. 종료");
				int menu = Integer.parseInt(sc.nextLine());
				switch(menu) {
					case 1:
						// 로그인
						cs.login();
						break;
					case 2:
						// 회원가입
						cs.insertClient();
						break;
					case 3:
						System.out.println("프로그램을 종료합니다 ✋✋");
						run = false;
						break;
					default:
						System.out.println("없는 메뉴 선택🙅‍♂️🙅‍♂️🙅‍♂️🙅‍♂️");
						break;
					}
			}else if(ClientService.clientInfo != null) {
				if(ClientService.clientInfo.getClientAuth().equals("N")) {
					// 일반사용자(N)
					new ClientApp();
				}else if(ClientService.clientInfo.getClientAuth().equals("A")) {
					// 관리자(A)
					new AdminApp();
				}
			}
		}
	}
// 구분선
}
