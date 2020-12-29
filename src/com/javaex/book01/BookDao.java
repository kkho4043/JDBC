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
	
	
	//�˻��ϱ�==================================================================
	public List<BookVo> searchList(String search){
		// 0. import java.sql.*;

		List<BookVo> BooksearchList = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName(driver);

			// 2. Connection ������

			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			/*
			 *  select  b.book_id,
						b.title,
						b.pubs,
						TO_CHAR((b.pub_date),'YYYY/MM/DD'),
						a.author_name
				from book b,author a 
				where(b.title LIKE '%��%'
				or b.pubs LIKE '%��%'
				or a.author_name LIKE '%��%')
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

			// 4.���ó��
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
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
	
	//å�� �۰� ��������=================================================================
	public List<BookVo> getBookallList() {
		// 0. import java.sql.*;

				List<BookVo> BookallList = new ArrayList<BookVo>();

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					// 1. JDBC ����̹� (Oracle) �ε�
					Class.forName(driver);

					// 2. Connection ������

					conn = DriverManager.getConnection(url, id, pw);

					// 3. SQL�� �غ� / ���ε� / ����
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

					// 4.���ó��
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
					System.out.println("error: ����̹� �ε� ���� - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				} finally {

					// 5. �ڿ�����
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
	
	
	//å ����
	public int bookUpdate(BookVo bookVo) {

		// 0. import java.sql.*;
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName(driver);

			// 2. Connection ������
			
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL�� �غ� / ���ε� / ����
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

			count = pstmt.executeUpdate();//�� ����Ǹ� 1��ȯ �ȵǸ�0

		
			// 4.���ó��
			System.out.println("[DAO - book]" + count + "���� ���� �Ǿ����ϴ�.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
	
	//å����=======================================================================
	public int bookDelete(int BookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName(driver);

			// 2. Connection ������
			
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL�� �غ� / ���ε� / ����

			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1,BookId);

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO - book]" +count + "���� ���� �Ǿ����ϴ�.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
	//å ��������=================================================================
	public List<BookVo> getBookList() {
		// 0. import java.sql.*;

		List<BookVo> BookList = new ArrayList<BookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName(driver);

			// 2. Connection ������

			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";

			query += " SELECT book_id, ";
			query += " title, ";
			query += " pubs, ";
			query += " TO_CHAR((pub_date),'YYYY-MM-DD'), ";
			query += " author_id ";
			query += " FROM book";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
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
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
	
	//å ���=======================================================================
	public int bookInsert(BookVo bookVo) {
		int count = 0;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			// 2. Connection ������
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setString(3, bookVo.getBookPubDate());
			pstmt.setInt(4, bookVo.getBookauthorId());

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("[DAO - book]" + count + "���� ����Ǿ����ϴ�.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
