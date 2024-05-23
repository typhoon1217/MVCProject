package test;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String id = sc.nextLine();

		String pw= sc.nextLine();
		
		LoginInsert li = new LoginInsert();
		li.loginInsert(id,pw);

	}

}
