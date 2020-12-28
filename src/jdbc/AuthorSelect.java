package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {
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
			SELECT author_id,
			author_name,
			author_desc
			FROM author;
			*/
			
			query +=" SELECT author_id, ";
			query +=" author_name, ";
			query +=" author_desc ";
			query +=" FROM author";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			System.out.println(query);
		    // 4.���ó��
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc = rs.getNString("author_desc");
				
				System.out.println(authorId + "," +authorName+ "," + authorDesc );
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
