package controller;

import java.io.IOException;
import java.net.Socket;

import view.AdminChoice;

public class AdminEmpController {
	public String list(Socket cs) {
		try {
			CServerDataSender cds = new CServerDataSender(cs);

			// Header 출력
			String header = String.format("%-20s%-10s%-10s%-15s%-15s%-100s%-10s", "직원ID", "직원이름", "직속상관", "부서", "전화번호",
					"이메일", "월급");
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

				System.out.println(String.format("%-20s%-10s%-10s%-15s%-15s%-100s%-10s", empId, empName, empManager,
						empDepartment, empPhoneNumber, empEmail, empSalary));
			}
		} catch (IOException e) {
			System.out.print("cds에러: ");
			e.printStackTrace();
			return "cds에러";
		}
		return AdminChoice.LIST;
	}

	public String add(Socket cs) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(Socket cs) {
		// TODO Auto-generated method stub
		return null;
	}
}