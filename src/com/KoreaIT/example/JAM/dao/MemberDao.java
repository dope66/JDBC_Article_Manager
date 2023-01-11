package com.KoreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberDao {
	private Connection conn;

	public MemberDao(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn = conn;

	}


	public int doJoin(String loginId, String loginPw, String name) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		return DBUtil.insert(conn, sql);
	}

	public boolean isLoginIdDup(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(loginId) > 0");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		return  DBUtil.selectRowBooleanValue(conn, sql);
		
	}


	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId=? ",loginId);
		
		Map<String, Object> memberMap = DBUtil.selectRow(conn, sql);
		if(memberMap.isEmpty()) {
			return null;
		}
		return new Member(memberMap);
	}
}
