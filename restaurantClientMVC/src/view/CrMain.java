package view;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import controller.AdminEmpController;
import controller.AdminMenuController;
import controller.CLientID;
import controller.CServerDataSender;
import controller.ClientUtill;
import controller.CloginController;
import controller.TableController;

public class CrMain {
	static String logInAs;
	public static Scanner sc = new Scanner(System.in);
	public static String clientID = "empty"; // 주의 -----

	public static void main(String[] args) {
		//
		
		CMainMenu();
		
	}

	private static void CMainMenu() {
	    boolean isLogin = true;
	    isLogin = exit();
	    while (isLogin) {
	        ClientUtill cu = new ClientUtill();
	        Socket cs = cu.createClientSocket();
	        // 클라이언트 아이디 없을시 생성 있을시 확인.
	        CLientID cID = new CLientID();
	        clientID = cID.handleClientID(cs, clientID); 
	        if (clientID.equals(MenuChoice.FAIL)) {
	            System.out.println("CID FAIL");
	            return;
	        }
	        // 로그인
	        MenuViewer.prBar2("로그인");
	        CloginController cLC = new CloginController();
	        String logInAs = cLC.login(cs);
	        //
	        // 반환값 확인
	        System.out.println("로그인 결과: " + logInAs);
	        //
	        // TODO:분할 가능
	        //
	        // loginas:

	        switch (logInAs) {//비파트 먼트 코드로했으면 됬구나.....
	            case MenuChoice.ADMIN:
	                logInAs = adminMenu(cs);
	                break;
	                
	            case MenuChoice.SERVE:
	                System.out.println("진입?1");
	                logInAs = serverMenu(cs);
	                break;
	                
	            case MenuChoice.ACCOUNT:
	                logInAs = accountMenu(cs);
	                break;
	                
	            case MenuChoice.CHEF:
	                logInAs = chefMenu(cs);
	                break;
	                
	            case MenuChoice.FAIL: // 아직 반복-------------------------------중요------------
	                System.out.println("재접속을 시도합니다.");
	                break; // 재접속

	            default: // 에러등 프로그램을 종료시키고 싶을때
	                isLogin = false;
	                System.out.println("오류가 발생했습니다.");
	                System.out.println("프로그램을 종료합니다.");
	                return; // 종료
	        }
	        if (isLogin) {
	            isLogin = exit();
	        }
	    } // end of while
	    // 리턴 종료
	}

	// 클라이언트 관리자 메뉴
	private static String adminMenu(Socket cs) {
		MenuViewer.prBar();
		System.out.println("관리자 메뉴로 이동합니다.");
		CServerDataSender cds = null;
		String choice;

		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {

			// AdminView
			MenuViewer.prBar();
			MenuViewer.adminMenuView();
			//
			// ------------------------------------------------------------------------
			// 입력후 전송
			choice = sc.nextLine(); // 정규화 필요 fail error값 직접 입력 막아야됨
			System.out.println("전송: " + choice);

			MenuViewer.prBar();

			cds.send(choice);// TODO:마무리 옵션 작업> 잘못된값일땐 안보내는게 성능에는 더좋을듯 하지만 복잡해짐
			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println("로그아웃");
				logInAs = null;
				return MenuChoice.FAIL;//나가서 재접속

			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println("직원 관리");
				choice = manageEMP(cs);
				break;
			// ----------------북마크------------
			// ----------------북마크------------
			case AdminChoice.MANAGEMENU:
				System.out.println("메뉴 관리");
				choice = manageMenu(cs);
				break;

			case MenuChoice.FAIL:
				System.out.println("<BACK>");
				break;

			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;

			default:
				System.out.println("잘못된값입니다 다시 입력해주세요");
				break;
			}
		}
	}

	// 클라 관리자 직원 관리 메뉴111
	private static String manageEMP(Socket cs) {
		System.out.println("직원 관리 메뉴로 이동합니다.");
		MenuViewer.prBar();
		CServerDataSender cds = null;
		String choice;
		AdminEmpController ac = new AdminEmpController();

		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// EMPView--------------------------------
			MenuViewer.adminEmpMenuView();
			// ------------------------------------------------------------------------
			// 입력후 전송
			choice = sc.nextLine(); // 정규화 필요 fail error값 직접 입력 막아야됨
			cds.send(choice);// TODO:마무리 옵션 작업> 잘못된값일땐 안보내는게 성능에는 더좋을듯 하지만 복잡해짐
			//
			System.out.println("전송: " + choice);// --
			MenuViewer.prBar();
			//
			switch (choice) {
			case AdminChoice.LIST:
				System.out.println("선택: 직원 목록 조회");
				choice = ac.list(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				break;

			case AdminChoice.ADD:
				System.out.println("선택: 직원 추가");

				choice = ac.add(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				break;

			case AdminChoice.EDIT:
				
				choice = ac.list(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				
				System.out.println("선택: 직원 정보 수정");
				choice = ac.edit(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				break;

			case AdminChoice.DELETE:
				System.out.println("선택: 직원 삭재");
				//
				// --리스트
				choice = ac.list(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				//
				// --삭제
				choice = ac.delete(cs);
				if (choice.equals(MenuChoice.ERROR)) {
					return MenuChoice.ERROR;// 종료함
				}
				break;

			case AdminChoice.BACK:
				System.out.println("뒤로|관리자 메뉴로");
				return MenuChoice.FAIL;// 종료X 외부 반복

			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;// 오류 종료

			default:
				System.out.println("잘못된값입니다 다시 입력해주세요");
				break;// 내부반복
			}
		}
	}

	// 클라 관리자 메뉴 관리 메뉴
	private static String manageMenu(Socket cs) {
		System.out.println("메뉴 관리 메뉴로 이동합니다.");
		System.out.println("미구현");
		return MenuChoice.FAIL;// 종료X 외부 반복
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// 클라이언트 서버 메뉴
	private static String serverMenu(Socket cs) {
		MenuViewer.prBar();
		System.out.println("서버 메뉴로 이동합니다.");
		CServerDataSender cds = null;
		String choice;

		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			e.printStackTrace();
			return MenuChoice.ERROR;
		}

		while (true) {
			TableController tbc=new TableController();
			// 서빙View
			MenuViewer.prBar();
			MenuViewer.servingMenu();
			//
			// ------------------------------------------------------------------------
			// 입력후 전송
			choice = sc.nextLine(); // 정규화 필요 fail error값 직접 입력 막아야됨
			System.out.println("전송: " + choice);

			MenuViewer.prBar();

			cds.send(choice);// TODO:마무리 옵션 작업> 잘못된값일땐 안보내는게 성능에는 더좋을듯 하지만 복잡해짐
			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println("로그아웃");
				logInAs = null;
				return MenuChoice.FAIL;

			case ServeChoice.VIEW:
				System.out.println("1. 테이블 보기"); //좌석수 변경
				choice = tbc.listTables(cs);
				break;
				
				
			case ServeChoice.EDIT:
				System.out.println("2. 테이블 변경");
				choice = tbc.moveTable(cs);
				break;
			// ----------------북마크------------
			// ----------------북마크------------
			case ServeChoice.PLACE:
				System.out.println("3. 테이블 배정");
				choice = tbc.moveTable(cs);
				break;

			case ServeChoice.DELETE:
				System.out.println("4. 테이블 삭제");
				choice = tbc.deleteTable(cs);
				break;
				
			case ServeChoice.EDITMAX:
				System.out.println("5. 최대 테이블 변경");
				choice = tbc.modifyTotalTables(cs);
				break;
				
			//주문메뉴 추가 
				
			case ServeChoice.GETORDER:
				System.out.println("<BACK>");
				break;	
				
			case MenuChoice.PASS:
				System.out.println("성공");
				System.out.println("반복");
				break;	
				
			case MenuChoice.FAIL:
				System.out.println("실패");
				System.out.println("반복");
				break;	
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR; //종료

			default:
				System.out.println("잘못된값입니다 다시 입력해주세요");
				break;
			}
		}
	}
	// 클라이언트 회계 메뉴
	private static String orderMenu(Socket cs) {
		System.out.println("주문 메뉴로 이동합니다.");
		MenuViewer.prBar();
		CServerDataSender cds = null;
		String choice;
		AdminEmpController ac = new AdminEmpController();

		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return MenuChoice.ERROR;
	}


	// 클라이언트 회계 메뉴
	private static String accountMenu(Socket cs) {
		System.out.println("회계 메뉴로 이동합니다.");
		System.out.println("미구현");
		return MenuChoice.FAIL;// 종료X 외부 반복
	}

	// 클라이언트 셰프 메뉴
	private static String chefMenu(Socket cs) {
		System.out.println("셰프 메뉴로 이동합니다.");
		System.out.println("미구현");
		return MenuChoice.FAIL;// 종료X 외부 반복
	}

	// 종료?
	private static boolean exit() {
		while (true) {
			MenuViewer.prBar();
			System.out.println("로그인 하시겠습니까?");
			System.out.println("1:로그인    2:종료");
			System.out.print(">>>");
			int i = sc.nextInt();
			MenuViewer.prBar();
			sc.nextLine();
			if (i == 1) {
				System.out.println("재접속합니다");
				return true;
			} else if (i == 2) {
				System.out.println("종료합니다.");
				return false;
			} else {
				System.out.println("잘못된 입력값입니다. 다시 입력해주세요.");
			}
		}
	}
}
