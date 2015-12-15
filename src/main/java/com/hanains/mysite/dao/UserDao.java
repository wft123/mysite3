package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.exception.RepositoryException;
import com.hanains.mysite.vo.UserVo;

import oracle.jdbc.pool.OracleDataSource;

@Repository
public class UserDao {
	
	@Autowired
	private OracleDataSource oracleDataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get( UserVo vo ) throws RepositoryException {
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword",vo);
		return userVo;
	}

	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert",vo);
	}
	
	public UserVo get( Long no ){
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
	
	public boolean idCheck( String email ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//1. get Connection
			conn = oracleDataSource.getConnection();
			
			//2. prepare statement
			String sql = 
				" select no, name, email" +
				"   from member" +
				"  where email=?";
			pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, email );
			
			//4. execute SQL
			rs = pstmt.executeQuery();
			if( rs.next() ) return true;
			
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		} finally {
			//5. clear resources
			try{
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( conn != null ) conn.close();
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		return false;
	}
}