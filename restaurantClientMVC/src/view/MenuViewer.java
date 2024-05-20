package view;

public class MenuViewer {
	public static void prBar(){
		System.out.println("=====================================================");
	} 
	
	public static void prLogInAs(String logInAs) {
		System.out.println("직급:" + logInAs + "으로 접속합니다.");
	}
	
	public static void prAID(String logInAs) {
		System.out.println("사용자 이름을 입력하세요: ");
	}
//클라이언트!
	
	// 관리자 메뉴
	public static void adminMenuView() {
		System.out.println();
		prBar();
		System.out.println("관리자 클라이언트");
		prBar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 직원관리 조회/추가/삭제/수정");
		System.out.println("2. 메뉴판 조회/추가/삭제/수정");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 직원관리 메뉴
	public static void adminEmpMenuView() {
		System.out.println();
		prBar();
		System.out.println("관리자 클라이언트");
		System.out.println("직원관리 메뉴 입니다.");
		prBar();
		System.out.println("1. 직원 정보 목록");
		System.out.println("2. 직원 정보 입력");
		System.out.println("3. 직원 정보 삭제");
		System.out.println("4. 직원 정보 수정");
		System.out.println("5. 관리자 메뉴");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 메뉴판 관리 메뉴
	public static void adminMenuMenuView() {
		System.out.println();
		prBar();
		System.out.println("관리자 클라이언트");
		System.out.println("메뉴판관리 메뉴 입니다.");
		prBar();
		System.out.println("1. 메뉴 정보 목록");
		System.out.println("2. 메뉴 정보 입력");
		System.out.println("3. 메뉴 정보 삭제");
		System.out.println("4. 메뉴 정보 수정");
		System.out.println("5. 관리자 메뉴");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 주방 메뉴
	public static void kitchenMenuView() {
		System.out.println();
		prBar();
		System.out.println("주방 클라이언트");
		prBar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 주문 확인");
		System.out.println("2. 조리");
		System.out.println("3. 재료 조리");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 회계 메뉴
	public static void accountMenuView() {
		System.out.println();
		prBar();
		System.out.println("회계 클라이언트");
		prBar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 재고 발주");
		System.out.println("2. 매출 확인");
		System.out.println("3. 월말 정산");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 서빙 메뉴
	public static void tableMenuiew() {
		System.out.println();
		prBar();
		System.out.println("서빙 클라이언트");
		prBar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 테이블 보기");
		System.out.println("2. 테이블 변경");
		System.out.println("3. 테이블 배정");
		System.out.println("4. 테이블 삭제");
		System.out.println("5. 주문 메뉴");
		prBar();
		System.out.print("번호 선택 >> ");
	}

	// 주문 메뉴
	public static void serveMenuView() {
		System.out.println();
		prBar();
		System.out.println("서빙 클라이언트");
		System.out.println("주문 메뉴");
		prBar();
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("0. 로그아웃");
		System.out.println("1. 재고 발주");
		System.out.println("2. 매출 확인");
		System.out.println("3. 월말 정산");
		prBar();
		System.out.print("번호 선택 >> ");
	}
	
	public static boolean yes1orno2() {
		String input;
		while(true) {
			System.out.println();
	        System.out.println("1:예  2:아니요");
	        System.out.print(">>>");
	        input=CrMain.sc.nextLine();
			prBar();
	        switch(input) {
	        case "1":
	        return true;
	        case "2":
	        return false;
	        default: System.out.println("다시 입력하세요 (1) or (2)만 가능합니다.");
	        break;
	        }     
		}
	}

	public static String departmentChoice() {
		String input;
		while(true) {
			System.out.println();
	        System.out.println("1: "+MenuChoice.ADMIN+ "  2: "+MenuChoice.SERVE+
	        		"\t  3: "+ MenuChoice.CHEF+"  4: "+MenuChoice.ACCOUNT+" 5:  exit");
	        System.out.print(">>>");
	        input=CrMain.sc.nextLine();
			MenuViewer.prBar();
	        switch(input) {
	        case "1":
	        return MenuChoice.ADMIN;
	        case "2":
	        return MenuChoice.SERVE;
	        case "3":
		    return MenuChoice.CHEF;
	        case "4":
		    return MenuChoice.ACCOUNT;
	        case "5":
		    return MenuChoice.FAIL;//반복용
		    default:
		    	System.out.println("올바른 값을 입력해 주세요");
	        break;
	        }     
		}
	}	
}
