package view;

public class MenuViewer {
	public static void bar(){
		System.out.println("=====================================================");
	} 
	public static void loginas(String logInAs) {
		System.out.println("직급:" + logInAs + "으로 접속합니다.");
	}

	// 관리자 메뉴
	public static void mainMenuView() {
		System.out.println();
		bar();
		System.out.println("관리자 클라이언트");
		bar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 직원관리 조회/추가/삭제/수정");
		System.out.println("2. 메뉴판 조회/추가/삭제/수정");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 직원관리 메뉴
	public static void empMenuView() {
		System.out.println();
		bar();
		System.out.println("관리자 클라이언트");
		System.out.println("직원관리 메뉴 입니다.");
		bar();
		System.out.println("1. 직원 정보 목록");
		System.out.println("2. 직원 정보 입력");
		System.out.println("3. 직원 정보 수정");
		System.out.println("4. 직원 정보 삭제");
		System.out.println("5. 관리자 메뉴");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 메뉴판 관리 메뉴
	public static void menuMenuView() {
		System.out.println();
		bar();
		System.out.println("관리자 클라이언트");
		System.out.println("메뉴판관리 메뉴 입니다.");
		bar();
		System.out.println("1. 메뉴 정보 목록");
		System.out.println("2. 메뉴 정보 입력");
		System.out.println("3. 메뉴 정보 수정");
		System.out.println("4. 메뉴 정보 삭제");
		System.out.println("5. 관리자 메뉴");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 주방 메뉴
	public static void kitchenMenuView() {
		System.out.println();
		bar();
		System.out.println("주방 클라이언트");
		bar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 주문 확인");
		System.out.println("2. 조리");
		System.out.println("3. 재료 조리");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 회계 메뉴
	public static void accountMenuView() {
		System.out.println();
		bar();
		System.out.println("회계 클라이언트");
		bar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 재고 발주");
		System.out.println("2. 매출 확인");
		System.out.println("3. 월말 정산");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 서빙 메뉴
	public static void tableMenuiew() {
		System.out.println();
		bar();
		System.out.println("서빙 클라이언트");
		bar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 테이블 보기");
		System.out.println("2. 테이블 변경");
		System.out.println("3. 테이블 배정");
		System.out.println("4. 테이블 삭제");
		System.out.println("5. 주문 메뉴");
		bar();
		System.out.print("번호 선택 >> ");
	}

	// 주문 메뉴
	public static void serveMenuView() {
		System.out.println();
		bar();
		System.out.println("서빙 클라이언트");
		System.out.println("주문 메뉴");
		bar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 재고 발주");
		System.out.println("2. 매출 확인");
		System.out.println("3. 월말 정산");
		bar();
		System.out.print("번호 선택 >> ");
	}
}
