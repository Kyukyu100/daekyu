package com.yedam.Book;

import java.util.List;
import java.util.Scanner;

import com.yedam.client.ClientService;

public class BookService {
	
	Scanner sc = new Scanner(System.in);
	boolean run = true;

	// 전체 조회(공용)
	public void getBookList() {
		List<Book> list = BookDAO.getInstance().getBookList();
		for(Book book : list) {
			System.out.println("==============================");
			System.out.printf("도서 ID : %04d\n",book.getBookId());
			System.out.println("도서 이름  : " + book.getBookName());
			System.out.println("도서 카테고리 :  " + book.getBookKate());

		}
	}
	// 이름 검색
	
	// 단일 조회(공용)
	public void getBook() {
		System.out.println("조회할 책 ID를 입력하시오>");
		int id = Integer.parseInt(sc.nextLine());
		Book book = BookDAO.getInstance().getBook(id);
		System.out.printf("책 ID : %04d\n", book.getBookId());
		System.out.println("책 이름 : " + book.getBookName());
		System.out.println("책 카테고리 : " + book.getBookKate());
	}
	
	// 도서 등록(관리자용)
	public void insertBook() {
		System.out.println("도서 등록");
		Book book = new Book();
		
		System.out.println("NAME 입력>");
		book.setBookName(sc.nextLine());
		
		System.out.println("KATEGORI 입력>");
		book.setBookKate(sc.nextLine());

		int result = BookDAO.getInstance().insertBook(book);
		if(result > 0) {
			System.out.println("도서 등록 성공");
		}else {
			System.out.println("도서 등록 실패");
		}
	}
	// 도서 삭제(관리자용)
	public void deleteBook() {
		System.out.println("도서 삭제");
		System.out.println("삭제할 도서 ID 입력>");
		String id = sc.nextLine();
		int result = BookDAO.getInstance().deleteBook(id);
		if(result > 0) {
			System.out.println("도서 삭제 성공");
		}else {
			System.out.println("도서 삭제 실패");
		}
	}
	// 카테고리 검색
	public void sarchKate() {
		System.out.println("카테고리 입력>");
		String kate = sc.nextLine();
		List<Book> list = BookDAO.getInstance().sarchKateBook(kate);
		for(Book book : list) {
			System.out.println("도서 ID  : " + book.getBookId());
			System.out.println("도서 이름  : " + book.getBookName());
			System.out.println("도서 카테고리  : " + book.getBookKate());
		}
}

	public void getRandomBook() {
		Book randomBook = new Book();
		List<Book> list = BookDAO.getInstance().getBookList();
		String randomWord = "";
//		int allBook = (int)(Math.random()*list.size()+1);
		Book checkBook = new Book();
		System.out.println("================================");
		for(int i=0; i<list.size(); i++) {
			checkBook = list.get(i);
			if(checkBook.getBookGameCheck().equals("Y")) {
				randomBook = list.get(i);
				randomWord = randomBook.getBookName().replaceAll(" ", "");
			}	
		}
		System.out.println("도서 이름 맞추기 게임");
		run = true;
		int count = 3;
		while(run) {
			String threeWord = "";
			if(randomWord.length()<3) {
				threeWord = randomWord.substring(0,1);
			}
			else{
				threeWord = randomWord.substring(0,3);
			}
			if(count==3) {
				for(int i=0; i<threeWord.length(); i++) {
					System.out.print(threeWord.charAt(i)+"  ");
				}
				System.out.println();
				System.out.println("남은 기회 : 3");
				System.out.println("정답 입력>");
				String gogo = sc.nextLine();
				if(gogo.replaceAll(" ", "").equals(randomWord)) {
					System.out.println("축하합니다. 정답입니다.");
					BookDAO.getInstance().scoreUp(90,ClientService.clientInfo.getClientId());
					BookDAO.getInstance().exceptWord(randomBook.getBookId());
					run = false;
				}else {
					System.out.println("오답입니다 다시 입력해 주십시오.");
					count--;
					}
			}
			else if(count == 2) {
				System.out.println("================================");
				for(int i=0; i<threeWord.length(); i++) {
					System.out.print(threeWord.charAt(i)+"  ");
				}
				System.out.println();
				System.out.println(randomBook.getBookKate());
				System.out.println("남은 기회 : 2 ");
				System.out.println("정답 입력>");
				String gogo = sc.nextLine();
				if(gogo.replaceAll(" ", "").equals(randomWord)) {
					System.out.println("축하합니다. 정답입니다.");
					BookDAO.getInstance().scoreUp(60,ClientService.clientInfo.getClientId());
					BookDAO.getInstance().exceptWord(randomBook.getBookId());
					run = false;
				}else {
					System.out.println("오답입니다 다시 입력해 주십시오.");
					count--;
					}
			}
			else if(count == 1) {
				System.out.println("================================");
				for(int i=0; i<threeWord.length(); i++) {
					System.out.print(threeWord.charAt(i)+"  ");
				}
				System.out.println();
				System.out.println(randomBook.getBookKate());
				System.out.println(randomBook.getBookGameHint());
				System.out.println("남은 기회 : 1 ");
				System.out.println("정답 입력>");
				String gogo = sc.nextLine();
				if(gogo.replaceAll(" ", "").equals(randomWord)) {
					System.out.println("축하합니다. 정답입니다.");
					BookDAO.getInstance().scoreUp(30,ClientService.clientInfo.getClientId());
					BookDAO.getInstance().exceptWord(randomBook.getBookId());
					run = false;
				}else {
					System.out.println("오답입니다 다시 입력해 주십시오.");
					count--;
					}
			}
			else if(count == 0) {
				System.out.println("GAME OVER");
				BookDAO.getInstance().exceptWord(randomBook.getBookId());
				run = false;
			}
					
		}	
	}
	
	
	
	
	
}
