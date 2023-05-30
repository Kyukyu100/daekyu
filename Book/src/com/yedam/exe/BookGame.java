package com.yedam.exe;

import java.util.Scanner;

public class BookGame {
	
	boolean run = true;
	Scanner sc = new Scanner(System.in);
	
	
	public BookGame() {
		run();
	}
	private void run() {
		while(run) {
			System.out.println("도서 맞추기 게임");
			System.out.println("1. 게임 시작 | 2. 랭킹 확인 | 3. 뒤로 가기 ");
			int gameMenu = Integer.parseInt(sc.nextLine());
			switch(gameMenu) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					run = false;
					break;
				case 4:
					break;
			}
		}
	}
	
	
}
