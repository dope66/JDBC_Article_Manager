package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController extends Controller{
	private Connection conn;

	public MemberController(Connection conn, Scanner sc) {
		super(sc);
		this.conn = conn;
		
	}

	public void doJoin(String cmd) {
		// TODO Auto-generated method stub
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		System.out.println("== 회원 가입 ==");
		while (true) {
			System.out.printf("ID : ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(loginId) > 0");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?", loginId);
			
			boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
			if(isLoginIdDup) {
				System.out.printf("%s는 이미 사용중인 아이디 입니다.\n",loginId);
				continue;
			}
			
			
			break;
		}
		while (true) {
			System.out.printf("PW : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("비밀 번호를 확인해 주세요");
				continue;
			}
			boolean loginPwCheck = true;

			while (true) {
				System.out.printf("PWChk : ");
				loginPwChk = sc.nextLine().trim();
				if (loginPwChk.length() == 0) {
					System.out.println("비밀 번호확인을 확인해 주세요");
					continue;
				}
				if (loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지않습니다. 다시 입력해주세요");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}
		while (true) {
			System.out.println("name : ");
			name = sc.nextLine();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;

		}

		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		DBUtil.insert(conn, sql);

		System.out.printf("%s회원님 가입 되었씁니다.\n", name);
	}
	
}
