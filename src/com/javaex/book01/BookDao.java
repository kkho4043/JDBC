package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	//검색하기==================================================================
	public List<BookVo> searchList(String search){
		// 0. import java.sql.*;

		List<BookVo> BooksearchList = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기

			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			/*
			 *  select  b.book_id,
						b.title,
						b.pubs,
						TO_CHAR((b.pub_date),'YYYY/MM/DD'),
						a.author_name
				from book b,author a 
				where(b.title LIKE '%문%'
				or b.pubs LIKE '%문%'
				or a.author_name LIKE '%문%')
				and b.author_id = a.author_id;
			 */
			query +=" select  b.book_id, ";
			query +=" b.title, ";
			query +=" b.pubs, ";
			query +=" TO_CHAR((b.pub_date),'YYYY/MM/DD'), ";
			query +=" a.author_name ";
			query +=" from book b,author a ";
			query +=" where(b.title LIKE '%"+search+"%' " ;
			query +=" or b.pubs LIKE '%"+search+"%' ";
			query +=" or a.author_name LIKE '%" +search+"%' )";
			query +=" and b.author_id = a.author_id";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle= rs.getNString("title");
				String bookpubs = rs.getNString("pubs");
				String bookpubdate = rs.getNString("TO_CHAR((b.pub_date),'YYYY/MM/DD')");
				String authorName = rs.getNString("author_name");
			
				BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate, authorName);
				BooksearchList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return BooksearchList;
	}
	
	//책과 작가 가져오기=================================================================
	public List<BookVo> getBookallList() {
		// 0. import java.sql.*;

				List<BookVo> BookallList = new ArrayList<BookVo>();

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

					// 2. Connection 얻어오기

					conn = DriverManager.getConnection(url, id, pw);

					// 3. SQL문 준비 / 바인딩 / 실행
					String query = "";

					query +=" select  b.book_id, ";
					query +=" b.title, ";
					query +=" b.pubs, ";
					query +=" TO_CHAR((b.pub_date),'YYYY/MM/DD'), ";
					query +=" a.author_name ";
					query +=" from book b,author a ";
					query +=" where b.author_id = a.author_id ";

					pstmt = conn.prepareStatement(query);
					rs = pstmt.executeQuery();

					// 4.결과처리
					while (rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookTitle= rs.getNString("title");
						String bookpubs = rs.getNString("pubs");
						String bookpubdate = rs.getNString("TO_CHAR((b.pub_date),'YYYY/MM/DD')");
						String authorName = rs.getNString("author_name");
						

						BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate, authorName);
						BookallList.add(vo);
					}
				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {

					// 5. 자원정리
					try {

						if (rs != null) {
							rs.close();
						}
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						System.out.println("error:" + e);
					}
				}
				return BookallList;

	}
	
	
	//책 수정
	public int bookUpdate(BookVo bookVo) {

		// 0. import java.sql.*;
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			
			System.out.println(query);
			
			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setString(3, bookVo.getBookPubDate());
			pstmt.setInt(4, bookVo.getBookauthorId());
			pstmt.setInt(5, bookVo.getBookId());

			count = pstmt.executeUpdate();//잘 실행되면 1반환 안되면0

		
			// 4.결과처리
			System.out.println("[DAO - book]" + count + "건이 변경 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
	
	//책삭제=======================================================================
	public int bookDelete(int BookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1,BookId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[DAO - book]" +count + "건이 삭제 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return count;
	}
	//책 가져오기=================================================================
	public List<BookVo> getBookList() {
		// 0. import java.sql.*;

		List<BookVo> BookList = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기

			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += " SELECT book_id, ";
			query += " title, ";
			query += " pubs, ";
			query += " TO_CHAR((pub_date),'YYYY-MM-DD'), ";
			query += " author_id ";
			query += " FROM book";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookpubs = rs.getString("pubs");
				String bookpubdate = rs.getString("TO_CHAR((pub_date),'YYYY-MM-DD')");
				int author_id = rs.getInt("author_id");

				BookVo vo = new BookVo(bookId, bookTitle, bookpubs, bookpubdate, author_id);
				BookList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return BookList;

	}
	
	//책 등록=======================================================================
	public int bookInsert(BookVo bookVo) {
		int count = 0;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setString(3, bookVo.getBookPubDate());
			pstmt.setInt(4, bookVo.getBookauthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[DAO - book]" + count + "건이 저장되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				// if (rs != null) {
				// rs.close();
				// }
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;
	}
}
