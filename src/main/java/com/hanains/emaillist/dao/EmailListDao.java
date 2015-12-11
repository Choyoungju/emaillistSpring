package com.hanains.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.emaillist.vo.EmailListVo;



@Repository
public class EmailListDao {

	public List<EmailListVo> getList(){

		List<EmailListVo> list = new ArrayList<EmailListVo>();
		ResultSet rs = null;
		//Connection connection = null;
		Connection connection = null;
		Statement stmt = null;

		//1.,드라이버 로딩(클래스 로딩)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2. db 욘굘
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection =  DriverManager.getConnection(dbUrl, "webdb", "webdb"); 
			//DriverManager.getConnection(dbUrl,"webdb","webdb");


			//3. statement
			stmt=  connection.createStatement();

			//4. sql
			//String sql = "select no, first_name, last_name,email from email_list";
			String sql = "select * from email_list ORDER BY no desc";
			rs = stmt.executeQuery(sql);
			
			System.out.println("리스트 겟");

			//5 결과 가져오기

			while(rs.next()){
			
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);

				EmailListVo vo  = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);

				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패");
		} catch(SQLException ex){
			System.out.println("에러 - " + ex);
		} 
		

		return list;
	}

	public void insert(EmailListVo vo){

		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try{
			//1 driver load
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2. db con
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection =  DriverManager.getConnection(dbUrl, "webdb", "webdb"); 
			//DriverManager.getConnection(dbUrl,"webdb","webdb");

			//3. statement ready
			String sql = "insert into email_list values(email_list_no_seq.nextval,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			
			//5. sql 
			pstmt.executeUpdate();
			System.out.println("쿼리 insert 됨요");
			
			pstmt.close();
			connection.close();
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패");
		} catch(SQLException ex){
			System.out.println("에러 - " + ex);
		}
		
	}

}
