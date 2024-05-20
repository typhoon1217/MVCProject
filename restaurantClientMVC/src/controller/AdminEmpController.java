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
				if ("STOP".equals(empId)) {
					break;
				}
				String empName = cds.receive();
				String empManager = cds.receive();
				String empDepartment = cds.receive();
				String empPhoneNumber = cds.receive();
				String empEmail = cds.receive();
				String empSalary = cds.receive();

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

	//TODO:비밀번호!!!!!!!
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
		try {
			CServerDataSender cds = new CServerDataSender(cs);

			while (!flag) {
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("직원 ID입력: ");
					empId = CrMain.sc.nextLine();// --------입력1
					if (validation.checkID(empId)) {
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empId;);
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("직원 PW입력: ");
					empPassword = CrMain.sc.nextLine();// --------입력2
					if (validation.checkPW(empPassword)) {
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empPw;);
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("직원 이름입력: ");
					empName = CrMain.sc.nextLine();// --------입력3
					if (validation.checkVar(empName, 5)) {// 이름 nvachar5
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}
				} // empName);
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("상사 입력: ");
					empManager = CrMain.sc.nextLine();// --------입력4
					if (validation.checkVar(empManager, 20)) {// 이름 nvachar20
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empManager);
				
				while (true) {
					MenuViewer.prBar();
					System.out.println("부서 입력: ");
					empDepartment=MenuViewer.departmentChoice(); //부서입력 입력4
					System.out.println("다시입력할까요?");
					if (!MenuViewer.yes1orno2()) break;
				} // empDepartment);

				while (true) {
					MenuViewer.prBar();
					System.out.print("전화번호 입력: ");
					empPhoneNumber = CrMain.sc.nextLine();// --------입력5
					if (validation.checkPhoneNum(empPhoneNumber)) {// 이름 nvachar5
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empPhoneNumber);
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("이메일 입력: ");
					empEmail = CrMain.sc.nextLine();// --------입력6
					if (validation.checkEmail(empEmail)) {// 이름 nvachar5
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empEmail);
				
				
				while (true) {
					MenuViewer.prBar();
					System.out.print("월급 입력: ");
					empSalary = CrMain.sc.nextLine();// --------입력7
					if (validation.checkNUM(empSalary)) {// 이름 nvachar5
						System.out.println("다시입력할까요?");
						if (!MenuViewer.yes1orno2()) break;
					}//val 실패시 다시입력
				} // empManager);
				
				System.out.print("입력받은 정보를 전송할까요?? 아니요:처음부터 다시입력");
				flag=MenuViewer.yes1orno2();
			}//end of flag while
			
			
			cds.send(empId);// empId)-----------------전송1
			cds.send(empPassword);// empPassword)-----------------전송2
			cds.send(empName);// empName)-----------------전송3
			cds.send(empManager);// empManager)-----------------전송4
			cds.send(empDepartment);// empDepartment)-----------------전송5
			cds.send(empPhoneNumber);// empPhoneNumber)-----------------전송6
			cds.send(empEmail);// empEmail)-----------------전송7
			cds.send(empSalary);// empSalary)-----------------전송8
			

			//TODO:값확인
			//TODO:값출력
			System.out.println("추가 작업 종료");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("add에러");
			return MenuChoice.ERROR;
		}

        System.out.println("직원 추가 성공");
		return AdminChoice.ADD;
	}
	
	

	// 클라 직원 삭제
	public String delete(Socket cs) {
		try {
			CServerDataSender cds = new CServerDataSender(cs);

			System.out.println("삭제할 직원 ID: ");
			String empId = CrMain.sc.nextLine();

			//cds.send(empId);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("delete에러");

			return MenuChoice.ERROR;  
		}
		return AdminChoice.DELETE;
	}

	// 클라 직원 수정
	public String edit(Socket cs) {

		return null;
	}
}
