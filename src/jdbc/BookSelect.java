package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {
	public static void main(String[] args) {
		//insert into author(author_id, author_name , author_desc)
		//values (seq_author_id.nextval, '�迵��' ,'�˾�����');
		
		// 0. import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL�� �غ� / ���ε� / ����
			String query = "";
			/*
			select 
			    book_id,
			    title,
			    pubs,
			    pub_date,
			    author_id
			 from book
			*/
			query +=" select ";
			query +=" book_id, ";
			query +=" title, ";
			query +=" pubs, ";
			query +=" TO_CHAR((pub_date),'YYYY/MM/DD'), ";
			query +=" author_id ";
			query +=" from book ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			System.out.println(query);
		    // 4.���ó��
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle= rs.getNString("title");
				String bookPubs = rs.getNString("pubs");
				String bookDate = rs.getNString("TO_CHAR((pub_date),'YYYY/MM/DD')");
				String bookAuthodId = rs.getNString("author_id");
	
				
				System.out.println(bookId + "," +bookTitle+ "," + bookPubs+ ","+ bookDate+","+bookAuthodId);
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

	}
}
