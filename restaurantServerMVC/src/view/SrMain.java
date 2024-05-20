package view;

import java.io.IOException;
import java.net.Socket;

import controller.AdminEmpManager;
import controller.AdminMenuManager;
import controller.ClientIDManager;
import controller.EmpManagerDao;
import controller.SClientHandler;
import controller.SLoginManager;
import controller.ServerUtil;

//TODO db초기값 id 부분 랜덤 대신 문자+시퀸스로 변경
//Q: SrMain 외부에서 쓰레드를 나누어서 다시 돌아오기 때문에 전역변수로 CID를 안줌 줘도 상관없나?

public class SrMain {

	public static boolean end = false;

	public static void main(String[] args) {
		// FixedFee.setup();
		ServerUtil.startServer();
		System.out.println("server end");
	}

	public static void loginServer(Socket s) {
		String loginas = null;
		ClientIDManager cidm = new ClientIDManager();
		SLoginManager slm = new SLoginManager();

		// 클라이언트 아이디 확인
		System.out.println("클라이언트 아이디 확인" + s);
		String cID = cidm.IDCheck(s);
		if (MenuChoice.FAIL.equals(cID)) {
			System.out.println("CID FAIL");
			return;
		}

		// 로그인
		System.out.println(cID + ": 직원 로그인 시도" + s);
		loginas = slm.loginValidation(s, cID);

		// 반환값 확인
		System.out.println(cID + ": log in as:" + loginas);

		// TODO:분할 가능

		// login as:
		switch (loginas) {
		case MenuChoice.ADMIN:
			System.out.println(cID + ": log in as:" + loginas);
			loginas = adminMenu(cID, s);
			break;
		case MenuChoice.SERVE:
			System.out.println(cID + ": log in as:" + loginas);
			loginas = serverMenu(cID, s);
			break;
		case MenuChoice.ACCOUNT:
			loginas = accountMenu(cID, s);
			System.out.println(cID + ": log in as:" + loginas);
			break;
		case MenuChoice.CHEF:
			System.out.println(cID + ": log in as:" + loginas);
			loginas = chefMenu(cID, s);
			break;
		default:
			System.out.println(cID + ": 오류가 발생했습니다.");
			System.out.println(loginas);
			break;
		}
	}

	// 서버 관리자 메뉴
	private static String adminMenu(String cID, Socket s) {
		System.out.println(cID + ": 관리자 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();
			//
			System.out.println("socket: "+ s );
			System.out.println(cID +"|받은값: "+choice);

			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println(cID+": 로그아웃");
				return MenuChoice.FAIL;
				
			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println(cID+": 직원 관리");
				choice = manageEMP(cID,s);
				break;
				
			case AdminChoice.MANAGEMENU:
				System.out.println(cID+": 메뉴 관리");
				choice = manageMenu(cID,s);
				break;
				
			case MenuChoice.FAIL:
				System.out.println(cID+": <BACK>");
				break;
				
			case MenuChoice.ERROR:
				System.out.println(cID+": 오류");
				return MenuChoice.ERROR;
				
			default:
				System.out.print(cID+": 재입력 요청:");
				
				break;
			}
		}
	}

	
	
	

	// 서버 관리자 직원 관리 메뉴
	private static String manageEMP(String cID, Socket s) {
		System.out.println(cID + ": 직원 관리 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;
		AdminEmpManager aem = new AdminEmpManager();
		EmpManagerDao eMD = new EmpManagerDao();

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();//수신---------------------------------------------
			//
			System.out.println("socket: "+ s );
			System.out.print(cID +"|");
			System.out.println("받은값: "+choice);//--

			switch (choice) {
			case AdminChoice.LIST:
				System.out.println(cID +"|선택: 직원 목록 조회");
				choice = aem.list(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
				
			case AdminChoice.ADD:
				System.out.println(cID +"|선택: 직원 추가");
				choice = aem.add(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
				
			case AdminChoice.EDIT:
				System.out.println(cID +"|선택: 직원 삭재");
				choice = aem.edit(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
				
			case AdminChoice.DELETE:
				System.out.println(cID +"|선택: 직원 삭재");
				//
				//--리스트
				choice = aem.list(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				//
				//--삭제
				choice = aem.delete(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;

			
			case AdminChoice.BACK:
				System.out.println(cID +"|관리자 메뉴로");
				return MenuChoice.FAIL;//종료X  외부 반복
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;//오류 종료
				
			default:
				System.out.println(cID +"|잘못된값입니다 다시 입력해주세요");
				break;//내부반복
			}
		}
	}

	
	
	
	// 서버 관리자 메뉴 관리 메뉴
	private static String manageMenu(String cID,Socket s) {
		System.out.println(cID + ": 메뉴 관리 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;
		AdminMenuManager amm = new AdminMenuManager();

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();//수신---------------------------------------------
			//
			System.out.println("socket: "+ s );
			System.out.print(cID +"|");
			System.out.println("받은값: "+choice);//--

			switch (choice) {
			case AdminChoice.LIST:
				System.out.println(cID +"|선택: 직원 목록 조회");
				//choice = amm.loadEmployeeList(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
				
			case AdminChoice.ADD:
				System.out.println(cID +"|선택: 직원 추가");
				//choice = amm.add(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
				
			case AdminChoice.DELETE:
				System.out.println(cID +"|선택: 직원 삭재");
				//
				//
				//choice = amm.loadEmployeeList(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				//
				//
				//choice = amm.delete(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;

			case AdminChoice.EDIT:
				System.out.println(cID +"|선택: 직원 삭재");
				//choice = amm.edit(s);
				if (choice.equals(MenuChoice.ERROR)){
					return MenuChoice.ERROR;//종료함
				}
				break;
			
			case AdminChoice.BACK:
				System.out.println(cID +"|관리자 메뉴로");
				return MenuChoice.FAIL;//종료X  외부 반복
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;//오류 종료
				
			default:
				System.out.println(cID +"|잘못된값입니다 다시 입력해주세요");
				break;//내부반복
			}
		}
	}

	
	
	

	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	

	
	
	
	
	
	

	// 서버 서버 메뉴
	private static String serverMenu(String cID, Socket s) {
		System.out.println(cID + ": 서버 메뉴로 이동합니다.");
		System.out.println(cID + ": 관리자 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();
			//
			System.out.println("socket: "+ s );
			System.out.println(cID +": "+ choice);
			//
			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println("로그아웃");
				return MenuChoice.FAIL;
				
			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println("직원 관리");
				choice = manageEMP(cID,s);
				break;
				
			case AdminChoice.MANAGEMENU:
				System.out.println("메뉴 관리");
				choice = manageMenu(cID,s);
				break;
				
			//case MenuChoice.FAIL:System.out.println("실패");return MenuChoice.FAIL;
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;
				
			default:
				System.out.print("재입력 요청:");
				break;
			}
		}
	}


	// 서버 회계 메뉴
	private static String accountMenu(String cID, Socket s) {
		System.out.println(cID + ": 계정 메뉴로 이동합니다.");
		System.out.println(cID + ": 관리자 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();
			//
			System.out.println("socket: "+ s );
			System.out.println(cID +": "+ choice);
			//
			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println("로그아웃");
				return MenuChoice.FAIL;
				
			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println("직원 관리");
				choice = manageEMP(cID,s);
				break;
				
			case AdminChoice.MANAGEMENU:
				System.out.println("메뉴 관리");
				choice = manageMenu(cID,s);
				break;
				
			//case MenuChoice.FAIL:System.out.println("실패");return MenuChoice.FAIL;
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;
				
			default:
				System.out.print("재입력 요청:");
				break;
			}
		}
	}


	// 서버 쉐프 메뉴
	private static String chefMenu(String cID, Socket s) {
		System.out.println(cID + ": 셰프 메뉴로 이동합니다.");
		System.out.println(cID + ": 관리자 메뉴로 이동합니다.");
		SClientHandler sch = null;
		String choice;

		try {
			sch = new SClientHandler(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// ------------------------------------------------------------------------
			// 수신
			choice = sch.receive();
			//
			System.out.println("socket: "+ s );
			System.out.println(cID +": "+ choice);
			//
			switch (choice) {
			case MenuChoice.LOGOUT:
				System.out.println("로그아웃");
				return MenuChoice.FAIL;
				
			case AdminChoice.MANAGEEMPLOYEES:
				System.out.println("직원 관리");
				choice = manageEMP(cID,s);
				break;
				
			case AdminChoice.MANAGEMENU:
				System.out.println("메뉴 관리");
				choice = manageMenu(cID,s);
				break;
				
			//case MenuChoice.FAIL:System.out.println("실패");return MenuChoice.FAIL;
				
			case MenuChoice.ERROR:
				System.out.println("오류");
				return MenuChoice.ERROR;
				
			default:
				System.out.print("재입력 요청:");
				break;
			}
		}
	}
}
