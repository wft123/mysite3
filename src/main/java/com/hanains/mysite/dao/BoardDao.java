package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.http.util.DBClose;
import com.hanains.http.util.DBConnect;
import com.hanains.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	static public final int PAGE_ROW = 5;
	DBConnect dbconnect = null;
	String sql="";
	
	public BoardDao(){
		dbconnect = new DBConnect();
	}
	
	public void insert(BoardVo vo){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		
		String sql ="insert into board values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE, ?, ?, ?, ? )";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMember_no());
			pstmt.setLong(4, vo.getGroup_no());
			pstmt.setLong(5, vo.getOrder_no());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setString(7, vo.getFileName());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public BoardVo getView(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		String sql = "select no, title, content, member_no, group_no, order_no, depth, fileName from board where no="+no;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setMember_no(rs.getLong(4));
				vo.setGroup_no(rs.getLong(5));
				vo.setOrder_no(rs.getLong(6));
				vo.setDepth(rs.getLong(7));
				vo.setFileName(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return vo;
	}
	
	public void upCount(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update board set view_cnt = view_cnt + 1 where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public void modify(BoardVo vo){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update board set title=?, content=? where no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public void delete(long no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from board where no = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt);
		}
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.no, ");
		sql.append("a.title, ");
		sql.append("a.member_no, ");
		sql.append("b.name as member_name, ");
		sql.append("a.view_cnt, ");
		sql.append("to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') ");
		sql.append("from board a, ");
		sql.append("member b ");
		sql.append("where a.member_no = b.no ");
		sql.append("order by a.reg_date desc");

		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setMember_no(rs.getLong(3));
				vo.setMember_name(rs.getString(4));
				vo.setView_cnt(rs.getLong(5));
				vo.setReg_date(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		
		return list;
	}
	
	public int getBoardSize(String kwd){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board where title like ?";
		try {
			pstmt = con.prepareStatement(sql);
			if(kwd!=null) pstmt.setString(1, "%"+kwd+"%");
			else pstmt.setString(1, "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return 0;
	}
	
	public long getMaxGroup(){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(group_no) from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return 0L;
	}
	
	public long getMaxOrder(long group_no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(order_no) from board where group_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, group_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return 0L;
	}
	
	public long getMaxDepth(long group_no, long order_no){
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(depth) from board where group_no=? and order_no=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, group_no);
			pstmt.setLong(2, order_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		return 0L;
	}
	
	public List<BoardVo> getListPage(int pageNo, String kwd){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection con = dbconnect.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, title, member_no, member_name,view_cnt, reg_date, group_no, order_no, depth ");
		sql.append("from (SELECT *");
		sql.append("		FROM (");
		sql.append(" 			SELECT rownum rnum, no, title, member_no, member_name,view_cnt, reg_date, group_no, order_no, depth");
		sql.append("    		FROM (");
		sql.append("				 select a.no no,");
		sql.append("						  a.title title,");
		sql.append("						  a.member_no member_no,");
		sql.append("						  b.name as member_name,");
		sql.append("						  a.view_cnt view_cnt,");
		sql.append("						  to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date,");
		sql.append("						  a.group_no group_no,");
		sql.append("						  a.order_no order_no,");
		sql.append("						  a.depth depth");
		sql.append("				 from board a, member b");
		sql.append("				where a.member_no = b.no ");
		sql.append("				AND title like ? ");
		sql.append("				order by group_no desc, order_no asc, depth asc)");
		sql.append("				) pagetable");
		sql.append("			where rnum <= ?");
		sql.append("		)");
		sql.append("	where rnum >= ?");

		try {
			pstmt = con.prepareStatement(sql.toString());
			if(kwd!=null) pstmt.setString(1, "%"+kwd+"%");
			else pstmt.setString(1, "%");
			pstmt.setLong(2, pageNo*PAGE_ROW);
			pstmt.setLong(3, 1+(pageNo-1)*PAGE_ROW);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setMember_no(rs.getLong(3));
				vo.setMember_name(rs.getString(4));
				vo.setView_cnt(rs.getLong(5));
				vo.setReg_date(rs.getString(6));
				vo.setGroup_no(rs.getLong(7));
				vo.setOrder_no(rs.getLong(8));
				vo.setDepth(rs.getLong(9));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(con,pstmt,rs);
		}
		
		return list;
	}
	
}
