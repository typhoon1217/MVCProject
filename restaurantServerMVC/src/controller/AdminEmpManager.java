package controller;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import view.AdminChoice;
import view.MenuChoice;
import model.EmployeeVo;

public class AdminEmpManager {
    private List<EmployeeVo> employeeList;

    // 데이터베이스 연결 및 직원 목록 불러오기
    public String loadEmployeeList() {
        String dbUrl = "jdbc:your_database_url";
        String dbUser = "your_database_user";
        String dbPassword = "your_database_password";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT emp_id, emp_name, emp_manager, emp_department, emp_phone_number, emp_email, emp_salary FROM Employee";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            employeeList = new ArrayList<>();
            while (rs.next()) {
                EmployeeVo employee = new EmployeeVo();
                employee.setEmpId(rs.getString("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setEmpManager(rs.getString("emp_manager"));
                employee.setEmpDepartment(rs.getString("emp_department"));
                employee.setEmpPhoneNumberFromInt(rs.getInt("emp_phone_number"));
                employee.setEmpEmail(rs.getString("emp_email"));
                employee.setEmpSalaryFromInt(rs.getInt("emp_salary"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
        	return MenuChoice.ERROR;
        }
		return AdminChoice.LIST;
    }

    // 클라이언트로 직원 목록 전송
    public String list(Socket clientSocket) {
        try {
            SClientHandler sch = new SClientHandler(clientSocket);
            String result = loadEmployeeList();
            // 직원 목록 전송
            for (EmployeeVo employee : employeeList) {
                // 비밀번호를 제외한 직원 정보 전송
                sch.send(employee.getEmpId());
                sch.send(employee.getEmpName());
                sch.send(employee.getEmpManager());
                sch.send(employee.getEmpDepartment());
                sch.send(String.valueOf(employee.getEmpPhoneNumber()));
                sch.send(employee.getEmpEmail());
                sch.send(String.valueOf(employee.getEmpSalary()));
            }
            // 데이터 전송 종료를 나타내는 문자열 전송
            sch.send("STOP");
        } catch (Exception e) {
            System.out.println("Error sending employee list to client: " + e.getMessage());
            return MenuChoice.ERROR;
        }
		return AdminChoice.LIST;
    }

	public String add(String cID, Socket s) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(String cID, Socket s) {
		// TODO Auto-generated method stub
		return null;
	}
}
