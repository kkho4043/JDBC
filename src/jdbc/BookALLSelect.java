package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookALLSelect {
	public static void main(String[] args) {
		//insert into author(author_id, author_name , author_desc)
		//values (seq_author_id.nextval, '김영하' ,'알쓸신참');
		
		// 0. import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			/*
			select  b.book_id,
				    b.title,
				    b.pubs,
				    TO_CHAR((b.pub_date),'YYYY/MM/DD'),
				    a.author_id,
				    a.author_name,
				    a.author_desc
			from book b,author a
			where b.author_id = a.author_id;
			*/
			query +=" select  b.book_id, ";
			query +=" b.title, ";
			query +=" b.pubs, ";
			query +=" TO_CHAR((b.pub_date),'YYYY/MM/DD'), ";
			query +=" a.author_id, ";
			query +=" a.author_name, ";
			query +=" a.author_desc ";
			query +=" from book b,author a ";
			query +=" where b.author_id = a.author_id ";
			
			//System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle= rs.getNString("title");
				String bookPubs = rs.getNString("pubs");
				String bookDate = rs.getNString("TO_CHAR((b.pub_date),'YYYY/MM/DD')");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc = rs.getNString("author_desc");
				
				System.out.println(bookId + "," +bookTitle+ "," + bookPubs+ ","+ bookDate+","+authorId + "," +authorName+ "," + authorDesc);
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

	}
}
