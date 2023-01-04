package com.KoreaIT.example.JAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("hello world");

		String cmd;
		System.out.println("--프로그램 실행--");
		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.printf("명령어 : ");
			cmd = sc.nextLine().trim();
			System.out.println(cmd);

			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

		}
		sc.close();

	}
}
