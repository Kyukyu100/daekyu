
package com.yedam.loan;



import java.util.List;
import java.util.Scanner;
import com.yedam.Book.Book;
import com.yedam.Book.BookService;
import com.yedam.client.ClientService;

public class LoanService {

	Scanner sc = new Scanner(System.in);
	ClientService cs = new ClientService();
	BookService bs = new BookService();

	// 도서 대출
	public void loanBook() {
		System.out.println("도서 대출 ");
		searchBook();
		System.out.println("대출할 도서 번호 입력");
		Loan loan = null;
		int bookId = Integer.parseInt(sc.nextLine());
		int result = 0;
		loan = LoanDAO.getInstance().checkLoan(ClientService.clientInfo.getClientId(),bookId);
		if(loan != null) {
			if(loan.getLoanCheck().equals("N")) {
				result = LoanDAO.getInstance().loanBook(ClientService.clientInfo.getClientId(), bookId);
				if(result > 0) {
					System.out.println("대출 완료");
				}else {
					System.out.println("대출 실패");
				}
			}else {
				System.out.println("현재 대출중인 도서입니다.");
			}
		}else {
			result = LoanDAO.getInstance().loanBook(ClientService.clientInfo.getClientId(), bookId);
			if(result > 0) {
				System.out.println("대출 완료");
			}else {
				System.out.println("대출 실패");
			}
		}
	}
	// 미납 도서 조회
	public void getNotReturnBook() {
		List<Loan> list = LoanDAO.getInstance().getClientLoanList(ClientService.clientInfo.getClientId());
		for(Loan loan : list) {
			System.out.printf("대출 번호 : %04d ", loan.getLoanId());
			System.out.print(" | 사용자 이름 : " + loan.getClientId());
			System.out.printf(" | 도서 번호 : %04d", loan.getBookId());
			System.out.println(" | 도서 이름 : " + loan.getBookName() + " | 대출 일자 : " + loan.getLoanDate() 
							+ " | 대출 기한 : " + loan.getReturnDate());
		}
	}
	
	
	// 전체 대출 보기(관리자)
	public void getAdminLoanList() {
		List<Loan> list = LoanDAO.getInstance().getAdimeLoanList();
		for(Loan loan : list) {
		System.out.printf("대출 번호 : %04d ", loan.getLoanId());
		System.out.print(" | 사용자 이름 : " + loan.getClientId());
		System.out.printf(" | 도서 번호 : %04d", loan.getBookId());
		System.out.println(" | 도서 이름 : " + loan.getBookName() + " | 대출 일자 : " + loan.getLoanDate() 
						+ " | 대출 기한 : " + loan.getReturnDate()
						+ " | 대출 완료 여부 : " + (loan.getLoanCheck().equals("Y") ? "미납" : "반납"));	
		}
		
	}
	// 내 대출보기(사용자용)
	public void getClientLoanList() {
		List<Loan> list = LoanDAO.getInstance().getClientLoanList(ClientService.clientInfo.getClientId());
		for(Loan loan : list) {
			System.out.printf("대출 번호 : %04d ", loan.getLoanId());
			System.out.print(" | 사용자 이름 : " + loan.getClientId());
			System.out.printf(" | 도서 번호 : %04d", loan.getBookId());
			System.out.println(	" | 도서이름 : " + loan.getBookName() + " | 대출 일자 : " + loan.getLoanDate() 
							+ " | 대출 기한 : " + loan.getReturnDate()
							+ " | 대출 완료 여부 : " + (loan.getLoanCheck().equals("Y") ? "미납" : "반납"));	
		}
	}
	// 미납 도서 보기
	public void getClientNotLoanList() {
		List<Loan> list = LoanDAO.getInstance().getAllLoan(ClientService.clientInfo.getClientId());
		for(Loan loan : list) {
			if(loan.getLoanCheck().equals("Y")) {
				System.out.printf("대출 번호 : %04d ", loan.getLoanId());
				System.out.print(" | 사용자 이름 : " + loan.getClientId());
				System.out.printf(" | 도서 번호 : %04d", loan.getBookId());
				System.out.println(" | 도서 이름 : "+ loan.getBookName() +" | 대출 일자 : " + loan.getLoanDate() 
								+ " | 대출 기한 : " + loan.getReturnDate());
			}
		}
	}
	
	
	// 도서 반납
	public void returnBook() {
		System.out.println("반납할 도서 번호를 입력하세요.");
		int bookId = Integer.parseInt(sc.nextLine());
		int result = LoanDAO.getInstance().returnBook(ClientService.clientInfo.getClientId(), bookId);
		if(result > 0) {
			System.out.println("도서 반납 성공!");
			System.out.println("한줄평을 남기시겠습니까?");
			System.out.println("1. 예 | 2. 아니오 ");
			int yesOrNo = Integer.parseInt(sc.nextLine());
			if(yesOrNo == 1) {
				System.out.println("한줄평 입력>");
				String comment = sc.nextLine();
				int commentRe = LoanDAO.getInstance().addComment(ClientService.clientInfo.getClientId(),comment, bookId);
				if(commentRe > 0) {
					System.out.println("한줄평 등록 완료");
				}else {
					System.out.println("한줄평 등록 실패");
				}
			}
		}else {
			System.out.println("도서 반납 살패");
		}
	}
	public void searchBook() {
		while(true) {
			System.out.println("도서 이름 검색");
			String name = sc.nextLine();
			List<Book> list = LoanDAO.getInstance().searchBookName(name);
			if(list.size()>= 1) {
				for(Book book : list) {
					System.out.printf("도서 번호 : %04d", book.getBookId());
					System.out.println(" | 도서 이름 : " + book.getBookName()+
										" | 도서 카테고리 : " + book.getBookKate());
				}
				break;
			}else {
				System.out.println("검색된 도서가 없습니다.");
				continue;
			}
		}
	}
	
	// 도서 반납후 코멘트

	
}
