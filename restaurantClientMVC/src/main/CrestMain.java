package main;

import java.util.Scanner;

import controller.ClientUtill;

public class CrestMain {
	public static void main(String[] args) {
		ClientUtill.createClientSocket();
		System.out.println("The end");
	}
	private static void login() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("사용자 이름을 입력하세요: ");
	String username = scanner.nextLine();
	System.out.println("비밀번호를 입력하세요: ");
	String password = scanner.nextLine();
	}
}