package com.spring.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.BookDTO;

@Repository
public class BookDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Autowired
	private DataSource ds;
	
	public void close(Connection con,PreparedStatement pstmt,ResultSet rs) {
		try {
			pstmt.close();
			con.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con,PreparedStatement pstmt) {
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BookDTO> getRows(){
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		try {
			con=ds.getConnection();
			String sql = "select * from booktbl";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean insert(BookDTO insertDto) {
		boolean flag = false;
		
		try {
			con=ds.getConnection();
			String sql = "insert into booktbl(code,title,writer,price,description) values(?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, insertDto.getCode());
			pstmt.setString(2, insertDto.getTitle());
			pstmt.setString(3, insertDto.getWriter());
			pstmt.setInt(4, insertDto.getPrice());
			pstmt.setString(5, insertDto.getDescription());
			
			int result = pstmt.executeUpdate();
			if(result>0) {
				flag=true;
				commit(con);
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return flag;
	}
	
	public boolean delete(int code) {
		boolean flag = false;
		
		try {
			con = ds.getConnection();
			String sql = "delete from booktbl where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			int result = pstmt.executeUpdate();
			if(result>0) {
				flag = true;
				commit(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(con);
		} finally {
			close(con, pstmt);
		}
		return flag;
	}
	
	public boolean update(BookDTO updateDto) {
		boolean flag = false;
		
		try {
			con = ds.getConnection();
			String sql = "update booktbl set price=? where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, updateDto.getPrice());
			pstmt.setInt(2, updateDto.getCode());
			int result = pstmt.executeUpdate();
			if(result>0) {
				flag = true;
				commit(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(con);
		} finally {
			close(con, pstmt);
		}
		return flag;
	}
	
	public BookDTO getRow(int code) {
		BookDTO bookDto=null;
		
		try {
			con = ds.getConnection();
			String sql = "select * from booktbl where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookDto = new BookDTO();
				bookDto.setCode(rs.getInt("code"));
				bookDto.setTitle(rs.getString("title"));
				bookDto.setWriter(rs.getString("writer"));
				bookDto.setPrice(rs.getInt("price"));
				bookDto.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return bookDto;
	}
}
