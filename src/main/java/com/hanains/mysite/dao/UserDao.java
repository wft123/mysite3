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
	
	public UserVo get( Long no ){
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
	
	public UserVo get( String email ){
		UserVo vo = sqlSession.selectOne("user.getByEmail",email);
		return vo;
	}

	public void insert( UserVo vo ) {
		sqlSession.insert("user.insert",vo);
	}
}