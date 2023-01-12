package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController() {
		
		this.memberService = Container.memberService;
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

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup) {
				System.out.printf("%s는 이미 사용중인 아이디 입니다.\n", loginId);
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

		memberService.doJoin(loginId, loginPw, name);

		System.out.printf("%s회원님 가입 되었씁니다.\n", name);
	}

	public void Login(String cmd) {
		String loginId = null;
		String loginPw = null;
		System.out.println("== 로그인 ==");
		while (true) {
			System.out.printf("아이디 :");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("pls input your ID");
				continue;
			}
			boolean isLoginDup = memberService.isLoginIdDup(loginId);

			if (isLoginDup == false) {
				System.out.printf("%s is not Exists \n", loginId);
				continue;
			}
			break;

		}
		Member member = memberService.getMemberByLoginId(loginId);
		int tryCount = 0;
		int tryMaxCount = 3;

		while (true) {
			if (tryCount >= tryMaxCount) {
				System.out.println("pls Check your Pw try again");
				break;
			}
			System.out.printf("Password : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				tryCount++;
				System.out.println("pls input your Password");
				continue;
			}
			if (member.loginPw.equals(loginPw) == false) {
				tryCount++;
				System.out.println("Password do not match");
				continue;
			}
			System.out.printf("Welcom to %s Enjoy \n ", member.name);
			
			Container.session.login(member);
			break;
		}
	}

	public void showProfile(String cmd) {
		if(Container.session.loginedMemberId ==-1) {
			System.out.println("로그인 상태가 아닙니다.");
		}else {
			System.out.println(Container.session.loginedMember.name);
		}
		
	}

	public void Logout(String cmd) {
		Container.session.logout();
		System.out.println("logut");
	}

}
