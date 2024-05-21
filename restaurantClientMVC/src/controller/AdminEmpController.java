package controller;

import java.io.IOException;
import java.net.Socket;

import model.CValidation;
import view.AdminChoice;
import view.CrMain;
import view.MenuChoice;
import view.MenuViewer;

public class AdminEmpController {

	// 클라 직원 목록
	public String list(Socket cs) {
		try {
			CServerDataSender cds = new CServerDataSender(cs);

			// Header 출력
			String header = String.format("%-20s%-10s%-15s%-15s%-10s%-10s%-100s", "직원ID", "직원이름", "부서", "전화번호", "월급",
					"직속상관", "이메일");
			System.out.println(header);
			System.out.println(new String(new char[header.length()]).replace('\0', '-'));

			// 데이터 출력
			while (true) {
				String empId = cds.receive();
				if ("STOP".equals(empId)) { // ----1n,end
					break;
				}
				String empName = cds.receive();// ---2n
				String empManager = cds.receive();// ---3n
				String empDepartment = cds.receive();// ---4n
				String empPhoneNumber = cds.receive();// ---5n
				String empEmail = cds.receive();// ----6n
				String empSalary = cds.receive();// ---//7n

				System.out.println(String.format("%-20s%-10s%-15s%-15s%-10s%-10s%-100s", empId, empName, empDepartment,
						empPhoneNumber, empSalary, empManager, empEmail));
			}
		} catch (IOException e) {
			System.out.print("cds에러: ");
			e.printStackTrace();
			return MenuChoice.ERROR;
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.print("Thread.sleep에러: ");
			return MenuChoice.ERROR;
		}
		return AdminChoice.LIST;
	}

	// 클라 직원 추가
	public String add(Socket cs) {
		CValidation validation = new CValidation();
		String msg;
		String empId = null;
		String empPassword = null;
		String empName = null;
		String empManager = null;
		String empDepartment = null;
		String empPhoneNumber = null;
		String empEmail = null;
		String empSalary = null;
		boolean flag = false;

		while (!flag) {

			while (true) {
				MenuViewer.prBar();
				System.out.print("직원 ID입력: ");
				empId = CrMain.sc.nextLine();// --------입력1
				if (validation.checkID(empId)) {
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empId;);

			while (true) {
				MenuViewer.prBar();
				System.out.print(" 직원 PW입력: ");
				empPassword = CrMain.sc.nextLine();// --------입력2
				if (validation.checkPW(empPassword)) {
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empPw;);

			while (true) {
				MenuViewer.prBar();
				System.out.print("직원 이름입력: ");
				empName = CrMain.sc.nextLine();// --------입력3
				if (validation.checkVar(empName, 5)) {// 이름 nvachar5
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				}
			} // empName);

			while (true) {
				MenuViewer.prBar();
				System.out.print("상사 입력: ");
				empManager = CrMain.sc.nextLine();// --------입력4
				if (validation.checkVar(empManager, 20)) {// 이름 nvachar20
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empManager);

			while (true) {
				MenuViewer.prBar();
				System.out.println("부서 입력: ");
				empDepartment = MenuViewer.departmentChoice(); // 부서입력 입력4
				System.out.println("다시입력할까요?");
				if (!MenuViewer.yes1orno2())
					break;
			} // empDepartment);

			while (true) {
				MenuViewer.prBar();
				System.out.print("전화번호 입력: ");
				empPhoneNumber = CrMain.sc.nextLine();// --------입력5
				if (validation.checkPhoneNum(empPhoneNumber)) {// 이름 nvachar5
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empPhoneNumber);

			while (true) {
				MenuViewer.prBar();
				System.out.print("이메일 입력: ");
				empEmail = CrMain.sc.nextLine();// --------입력6
				if (validation.checkEmail(empEmail)) {// 이름 nvachar5
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empEmail);

			while (true) {
				MenuViewer.prBar();
				System.out.print("월급 입력: ");
				empSalary = CrMain.sc.nextLine();// --------입력7
				if (validation.checkNUM(empSalary)) {// 이름 nvachar5
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2())
						break;
				} // val 실패시 다시입력
			} // empManager);
			
            MenuViewer.prBar2("입력받은 값");
            System.out.printf("%-10s : %s%n", "직원아이디", empId);
            System.out.printf("%-10s : %s%n", "직원비밀번호", empPassword);
            System.out.printf("%-10s : %s%n", "이름", empName);
            System.out.printf("%-10s : %s%n", "직속상사", empManager);
            System.out.printf("%-10s : %s%n", "소속부서", empDepartment);
            System.out.printf("%-10s : %s%n", "전화번호", empPhoneNumber);
            System.out.printf("%-10s : %s%n", "이메일", empEmail);
            System.out.printf("%-10s : %s%n", "직원월급", empSalary);
            MenuViewer.prBar();
            
			System.out.println("입력받은 정보를 전송할까요?? 아니요:처음부터 다시입력");
			flag = MenuViewer.yes1orno2();
		} // end of flag while
		try {
			CServerDataSender cds = new CServerDataSender(cs);

			// 데이터 전송
			cds.send(empId);
			cds.send(empPassword);
			cds.send(empName);
			cds.send(empManager);
			cds.send(empDepartment);
			cds.send(empPhoneNumber);
			cds.send(empEmail);
			cds.send(empSalary);

			// 결과 수신
			String result = cds.receive();
			System.out.println("처리 결과: " + result);

			if ("성공".equals(result)) {
				System.out.println("직원 추가 성공");
				return AdminChoice.ADD;
			} else {
				System.out.println("직원 추가 실패");
				return MenuChoice.ERROR;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("add에러");
			return MenuChoice.ERROR;
		}
	}

	// 클라 직원 삭제
	public String delete(Socket cs) {

		try {
			CServerDataSender cds = new CServerDataSender(cs);

			System.out.println("삭제할 직원 ID: ");
			String empId = CrMain.sc.nextLine();

			cds.send(empId); // 삭제할 아이디 전송1>

			String result = cds.receive(); // 결과 수신 2<
			System.out.println("처리 결과:"); // 결과에 따른 처리를 위해 요약 가능하지만 유지 일단 실패건 성공이건 같은 위치로 돌아감
			System.out.println(result);// 결과 출력

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("delete에러");

			return MenuChoice.ERROR;
		}
		return AdminChoice.DELETE;
	}

	// 클라 직원 수정
	public String edit(Socket cs) {
	    CValidation validation = new CValidation();
	    String msg;
	    String empId = null;
	    String empPassword = null;
	    String empName = null;
	    String empManager = null;
	    String empDepartment = null;
	    String empPhoneNumber = null;
	    String empEmail = null;
	    String empSalary = null;
	    boolean flag = false;
	    try {
	        CServerDataSender cds = new CServerDataSender(cs);

	        while (!flag) {
	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("수정할 직원 ID 입력: ");
	                empId = CrMain.sc.nextLine();
	                if (validation.checkID(empId)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("직원 PW입력: ");
	                empPassword = CrMain.sc.nextLine();
	                if (validation.checkPW(empPassword)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("직원 이름입력: ");
	                empName = CrMain.sc.nextLine();
	                if (validation.checkVar(empName, 5)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("상사 입력: ");
	                empManager = CrMain.sc.nextLine();
	                if (validation.checkVar(empManager, 20)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.println("부서 입력: ");
	                empDepartment = MenuViewer.departmentChoice();
	                System.out.println("다시입력할까요?");
	                if (!MenuViewer.yes1orno2()) break;
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("전화번호 입력: ");
	                empPhoneNumber = CrMain.sc.nextLine();
	                if (validation.checkPhoneNum(empPhoneNumber)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("이메일 입력: ");
	                empEmail = CrMain.sc.nextLine();
	                if (validation.checkEmail(empEmail)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }

	            while (true) {
	                MenuViewer.prBar();
	                System.out.print("월급 입력: ");
	                empSalary = CrMain.sc.nextLine();
	                if (validation.checkNUM(empSalary)) {
	                    System.out.println("다시입력할까요?");
	                    if (!MenuViewer.yes1orno2()) break;
	                }
	            }
                MenuViewer.prBar2("입력받은 값");
                System.out.printf("%-10s : %s%n", "직원아이디", empId);
                System.out.printf("%-10s : %s%n", "직원비밀번호", empPassword);
                System.out.printf("%-10s : %s%n", "이름", empName);
                System.out.printf("%-10s : %s%n", "직속상사", empManager);
                System.out.printf("%-10s : %s%n", "소속부서", empDepartment);
                System.out.printf("%-10s : %s%n", "전화번호", empPhoneNumber);
                System.out.printf("%-10s : %s%n", "이메일", empEmail);
                System.out.printf("%-10s : %s%n", "직원월급", empSalary);
                
                MenuViewer.prBar();
	            System.out.println("입력받은 정보를 전송할까요?? 아니요:처음부터 다시입력");
	            flag = MenuViewer.yes1orno2();
	        }

	        cds.send(empId);
	        cds.send(empPassword);
	        cds.send(empName);
	        cds.send(empManager);
	        cds.send(empDepartment);
	        cds.send(empPhoneNumber);
	        cds.send(empEmail);
	        cds.send(empSalary);

	        // 결과 수신
	        String result = cds.receive();
	        System.out.println("처리 결과: " + result);

	        if ("성공".equals(result)) {
	            System.out.println("직원 수정 성공");
	            return AdminChoice.EDIT;
	        } else {
	            System.out.println("직원 수정 실패");
	            return MenuChoice.ERROR;
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("edit에러");
	        return MenuChoice.ERROR;
	    }
	}
}
