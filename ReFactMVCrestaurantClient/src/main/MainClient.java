package main;

import java.util.Scanner;

import controller.ClientIDmanager;
import controller.ClientUtill;
import model.LoginSession;
import view.AdminChoice;
import view.LoginChoice;
import view.MenuViewer;

public class MainClient {
	public static String clientID = null;

	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// testSelecter();
		// testStart();
		start();
	}

	private static void start() {
		ClientIDmanager cidM = new ClientIDmanager();
		boolean exit = false;
		while (!exit) {

			// view 1 변환----------------------------
			if (!cidM.checkClientID()) {
				System.err.println("ClientID 생성 혹은 실패 오류 감지로 종료합니다");
				return;// 생성 실패 혹은 이상감지 종료
			}
			System.out.println("로그인 하시겠습니까?");// 종료화면 보여주기 // 종료 방법
			System.out.println("1.로그인  0.종료");
			System.out.print(">>");
			switch (input.nextLine()) {
			case "1":
				exit = false;
			case "0":
				exit = true;
				return;
			default:
				System.err.println("잘못된 입력값입니다. 1또는 0을 입력해주세요");
				System.out.println("1.로그인  0.종료");
				System.out.print(">>");
			}
			System.out.println("ClientID 확인 혹은 생성 완료");
			System.out.println("ClientID		|" + LoginSession.getInstance().getClientID());
			System.out.println("남은 로그인 횟수	|" + LoginSession.getInstance().getAttemptRemain());
			login();
		} // end of while
	}

	private static void login() {
		ClientUtill cu = new ClientUtill();
		System.out.println("아이디를 입력해주세요");
		String username = MainClient.input.nextLine();
		System.out.println("비밀번호를 입력해주세요");
		String password = MainClient.input.nextLine();
		boolean logedin = cu.login(username, password);// boolean 사용가능
		if (logedin) {
			// 2 로그인에스 메서드 화
			System.out.println("log in as: " + LoginSession.getInstance().getUserdep());
			switch (LoginSession.getInstance().getUserdep()) {// 메뉴에서 클리어 세션실행하면 null로 실행됨
			case LoginChoice.ADMIN:
				adminMenu();
			case LoginChoice.SERVE:
				tableMenu();
			case LoginChoice.ACCOUNT:
				accountingMenu();
			case LoginChoice.CHEF:
				cookMenu();
			case null:// 하위메뉴에서 로그아웃시 null임
				// 로그아웃되어있음
				System.out.println("로그인이 만료 되어있습니다");
				break;
			default:
				System.err.println("의도되지 않은 결과");
				System.err.println("로그인 부서 부정확");
				System.err.println("로그인 부서:" + LoginSession.getInstance().getUserdep());
				System.err.println("프로그램을 종료합니다.");
				return;// 종료
			}
			System.err.println("end of switch//의도되지 않은 동작임 break로 호출가능");
		}
	}

	//
	private static void cookMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.accountMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case LoginChoice.LOGOUT:
				LoginSession.getInstance().clearSession(); // 세션클리어 로그아웃 처리
				System.out.println("로그아웃");
				return;

			case "1":
				System.out.println("주문 확인");

				break;

			case "2":
				System.out.println("조리");
				// TODO:미구현
				break;

			case "3":
				System.out.println("재료 조리");
				// TODO:미구현
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");
			}
		}
	}

	// 회계
	private static void accountingMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.accountMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case LoginChoice.LOGOUT:
				LoginSession.getInstance().clearSession(); // 세션클리어 로그아웃 처리
				System.out.println("로그아웃");
				return;

			case "1":
				System.out.println("재고 발주");
				// TODO:미구현
				break;

			case "2":
				System.out.println("매출 확인");
				// TODO:미구현
				break;

			case "3":
				System.out.println("월말 정산");
				// TODO:미구현
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");
			}
		}
	}

	// 테이블 관리
	private static void tableMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.tableMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case LoginChoice.LOGOUT:
				LoginSession.getInstance().clearSession(); // 세션클리어 로그아웃 처리
				System.out.println("로그아웃");
				return;

			case "1":
				System.out.println("테이블 보기");
				// TODO:미구현
				break;

			case "2":
				System.out.println("변경");
				// TODO:미구현
				break;

			case "3":
				System.out.println("테이블 배정");
				// TODO:미구현
				break;

			case "4":
				System.out.println("테이블 삭제");
				// TODO:미구현
				break;

			case "5":
				System.out.println("주문 메뉴");
				takeOrderMenu();
				break;
			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");
			}
		}

	}

	// 서빙|주문받기
	private static void takeOrderMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.takeOrderMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case "0":
				System.out.println("테이블 메뉴로 돌아가기");
				return;

			case "1":
				System.out.println("메뉴");
				// TODO:미구현
				break;

			case "2":
				System.out.println("주문 추가");

				// TODO:미구현
				break;

			case "3":
				System.out.println("주문 제거");

				// TODO:미구현
				break;

			case "4":
				System.out.println("주방 체크");

				// TODO:미구현
				break;

			case "5":
				System.out.println("계산 받기");

				// TODO:미구현
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");

			}
		}
	}

	// 관리자 메뉴
	private static void adminMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.adminMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case LoginChoice.LOGOUT:
				LoginSession.getInstance().clearSession(); // 세션클리어 로그아웃 처리
				System.out.println("로그아웃");
				return;

			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println("직원관리");
				adminEmployeeManageMenu();
				break;

			case AdminChoice.MANAGEMENU:
				System.out.println("메뉴판");
				adminMenuManageMenu();
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");

			}
		}
	}

	// 관리자|메뉴 관리
	private static void adminMenuManageMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.adminMenuManageView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case AdminChoice.BACK:
				System.out.println("뒤로|관리자 메뉴");
				return;

			case AdminChoice.LIST:
				System.out.println("메뉴 정보 목록");
				// TODO:미구현
				break;

			case AdminChoice.ADD:
				System.out.println("메뉴 정보 입력");
				// TODO:미구현
				break;

			case AdminChoice.DELETE:
				System.out.println("메뉴 정보 삭제");
				// TODO:미구현
				break;

			case AdminChoice.EDIT:
				System.out.println("메뉴 정보 수정");
				// TODO:미구현
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");
			}
		}
	}

	// 관리자|직원 관리
	private static void adminEmployeeManageMenu() {
		boolean exit = false;
		while (!exit) {
			MenuViewer.adminEmpMenuView();
			String choice = null;
			choice = input.nextLine();
			switch (choice) {

			case AdminChoice.BACK:
				System.out.println("뒤로|관리자 메뉴");
				return;

			case AdminChoice.LIST:
				System.out.println("직원 정보 목록");
				
				break;

			case AdminChoice.ADD:
				System.out.println("직원 정보 입력");
				// TODO:미구현
				break;

			case AdminChoice.DELETE:
				System.out.println("직원 정보 삭제");
				// TODO:미구현
				break;

			case AdminChoice.EDIT:
				System.out.println("직원 정보 수정");
				break;

			default:
				System.out.println("잘못된 값입니다 다시 입력하세요");
			}
		}
	}

	// ****테스트용**************************************

//	public static void testSelecter() {
//		boolean logedIn = false;
//
//		while (logedIn) {
//			System.out.println("--실험용");
//			System.out.println("1:관리자");
//			System.out.println("2:서빙");
//			System.out.println("3:회계");
//			System.out.println("4:요리사");
//			System.out.println("5:로그아웃");
//			String choice = null;
//			choice = input.nextLine();
//			switch (choice) {
//			case "1":
//				System.out.println("관리자");
//				adminMenu();
//				break;
//
//			case "2":
//				System.out.println("서빙");
//				tableMenu();
//				break;
//
//			case "3":
//				System.out.println("회계");
//				accountingMenu();
//				break;
//			case "4":
//				System.out.println("요리");
//				cookMenu();
//				break;
//
//			case "5":
//				System.out.println("로그아웃");
//				logedIn=false;
//				return;
//
//			case "exit":
//				System.out.println("뒤로");
//				return;
//
//			default:
//				System.out.println("잘못된 값입니다 다시 입력하세요");
//
//			}
//			System.out.println("end of switch");
//		}
//		System.out.println("end of while");
//
//	}

//	private static void testStart() {
//
//		ClientUtill cu = new ClientUtill();
////		String testinput;
////		String testinput2;
////		String testinput3;
////		
////		
////		System.out.print("테스트 입력1:");
////		testinput=input.next();
////		System.out.print("테스트 입력2:");
////		testinput2=input.next();
////		System.out.print("테스트 입력2:");
////		testinput3=input.next();
////		
////		testinput="1234s가";
////		testinput="111111";
////		testinput="가가가가";
//
//		cu.login("user1", "pass");
//
//		String token = LoginSession.getInstance().getUserToken();
//		String id = LoginSession.getInstance().getUsername();
//		String dep = LoginSession.getInstance().getUserdep();
//
//		System.out.println(token + id + dep);
//
//	}

}
