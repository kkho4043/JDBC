package com.javaex.book01;

import java.util.List;
import java.util.Scanner;

public class BookAuthorApp {
	public static void main(String[] args) {
		// 작가 등록 6명.(author)
		// AuthorDao, AuthorVo 이용해서 등록.
		AuthorDao authorDao = new AuthorDao();

		AuthorVo authorVo1 = new AuthorVo("김문열", "경북영양");
		authorDao.authorInsert(authorVo1);

		AuthorVo authorVo2 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo2);

		AuthorVo authorVo3 = new AuthorVo("유시민", "17대국회의원");
		authorDao.authorInsert(authorVo3);

		AuthorVo authorVo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(authorVo4);

		AuthorVo authorVo5 = new AuthorVo("강풀", "온라인만화가 1세대");
		authorDao.authorInsert(authorVo5);

		AuthorVo authorVo6 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(authorVo6);

		AuthorVo authorVo7 = new AuthorVo("삭제와", "수정을위해");
		authorDao.authorInsert(authorVo7);

		// 리스트 받아오기
		List<AuthorVo> authorList;
		// 리스트
		authorList = authorDao.getAuthorList();
		System.out.println("---author리스트 출력---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// 수정(author)
		AuthorVo authorVo0 = new AuthorVo(7, "수정과", "삭제를위해");
		authorDao.authorUpdate(authorVo0);

		// 리스트(author)
		authorList = authorDao.getAuthorList();
		System.out.println("---수정후 authour 리스트 출력---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// 삭제(author)
		authorDao.authorDelete(7);

		// 리스트(author)
		authorList = authorDao.getAuthorList();
		System.out.println("---삭제후 authour 리스트 출력---");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "." + vo.getAuthorName() + "," + vo.getAuthordesc());
		}

		// =========================================================================
		// 책8권등록
		// bookdao bookvo 이용
		BookDao bookDao = new BookDao();

		BookVo bookVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(bookVo1);

		BookVo bookVo2 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(bookVo2);

		BookVo bookVo3 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(bookVo3);

		BookVo bookVo4 = new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert(bookVo4);

		BookVo bookVo5 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(bookVo5);

		BookVo bookVo6 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(bookVo6);

		BookVo bookVo7 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(bookVo7);

		BookVo bookVo8 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(bookVo8);

		BookVo bookVo9 = new BookVo("삭제와", "수정을위해", "2020-12-29", 1);
		bookDao.bookInsert(bookVo9);

		// 리스트 받아오기(book)
		List<BookVo> bookList;

		bookList = bookDao.getBookList();
		// 리스트(book)
		System.out.println("--- book 리스트 출력---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}

		// 수정(book)
		BookVo bookVo0 = new BookVo(9, "수정과", "삭제를위해", "2020-12-29", 2);
		bookDao.bookUpdate(bookVo0);
		// 리스트(book)
		bookList = bookDao.getBookList();
		System.out.println("---수정후 book 리스트 출력---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
		// 삭제(book)
		bookDao.bookDelete(9);

		// 리스트(book)
		bookList = bookDao.getBookList();
		System.out.println("---삭제후 book 리스트 출력---");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
		
		
		List<BookVo> bookallList;
		bookallList = bookDao.getBookallList();
		System.out.println("---book ALL 리스트 출력---");
		
		for (int i = 0; i < bookallList.size(); i++) {
			BookVo voa = (BookVo) bookallList.get(i);
			System.out.println(voa.getBookId()+","+voa.getBookTitle()+","+voa.getBookPubs()+","+voa.getBookPubDate()
			+","+voa.getauthorname());
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 문자를 입력하시오 .");
		String search = sc.nextLine();
		List<BookVo> booksearchList;
		booksearchList = bookDao.searchList(search);
		
		System.out.println("---검색후 book 리스트 출력---");
		for (int i = 0; i < booksearchList.size(); i++) {
			BookVo vo = booksearchList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getBookTitle() + "," + vo.getBookPubs() + ","
					+ vo.getBookPubDate() + "," + vo.getBookauthorId());
		}
	}
}
