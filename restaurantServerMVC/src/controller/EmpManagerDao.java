package controller;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeVo;
import view.AdminChoice;
import view.MenuChoice;

public class EmpManagerDao {
    private List<EmployeeVo> employeeList;

    public EmpManagerDao() {
        this.employeeList = new ArrayList<>();
    }
    
    public String loadEmployeeList(Socket cs) {
        DBUtil dbu = new DBUtil();
        try (Connection con = dbu.getConnection()) {
            SClientHandler sch = new SClientHandler(cs);
            String query = "SELECT emp_id, emp_name, emp_manager, emp_department, emp_phone_number, emp_email, emp_salary FROM Employee";
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

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

                sch.send(employee.getEmpId());//--1n
                sch.send(employee.getEmpName());//-2n
                sch.send(employee.getEmpManager());//--3n
                sch.send(employee.getEmpDepartment());//--4n
                sch.send(employee.getEmpPhoneNumber());//--5n
                sch.send(employee.getEmpEmail());//---6n
                sch.send(employee.getEmpSalary());//---7n
            }
            sch.send("STOP");

        } catch (Exception e) {
            System.out.println("loadEmployeeList:에러감지");
            e.printStackTrace();
            return MenuChoice.ERROR;
        }
        System.out.println("loadEmployeeList:리스트 목록으로 돌아감");
        return AdminChoice.LIST;
    }

    public String addEmployee(Socket cs) {
        DBUtil dbu = new DBUtil();
        try (Connection con = dbu.getConnection()) {
            SClientHandler sch = new SClientHandler(cs);
// ---------------------------------
            //String empId = sch.receive();//-------1
           // String empPw = sch.receive();//-------2
           // String empName = sch.receive();//-------3
           // String empManager = sch.receive();//-------4
            //String empDepartment = sch.receive();//-------5
           // String empPhoneNumber = sch.receive();//-------6
           // String empEmail = sch.receive();//-------7
           // String empSalary = sch.receive();//-------8
//-------------------------
            String query = "INSERT INTO Employee (emp_id, emp_password, emp_name, emp_manager, emp_department, emp_phone_number, emp_email, emp_salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

           // stmt.setString(1, empId);
           // stmt.setString(2, empPw);
           // stmt.setString(3, empName);
           // stmt.setString(4, empManager);
           // stmt.setString(5, empDepartment);
           // stmt.setLong(6, Long.parseLong(empPhoneNumber));
           // stmt.setString(7, empEmail);
           // stmt.setLong(8, Long.parseLong(empSalary));
            
             stmt.setString(1, sch.receive());//-----------1
             stmt.setString(2,  sch.receive());//----------2
             stmt.setString(3,  sch.receive());//-------------3
             stmt.setString(4,  sch.receive());//---------------4
             stmt.setString(5,  sch.receive());//----------------5
             stmt.setLong(6, Long.parseLong(sch.receive()));//------6
             stmt.setString(7,  sch.receive());//----------------------7
             stmt.setLong(8, Long.parseLong(sch.receive()));//-----8
            
//--------------------------------------------
            stmt.executeUpdate();
            
            
            // 성공 메시지 전송
            sch.send("성공");
            return AdminChoice.ADD;

        } catch (Exception e) {
            System.out.println("addEmployee:에러감지");
            e.printStackTrace();
            
            // 실패 메시지 전송
            SClientHandler sch;
			try {
				sch = new SClientHandler(cs);
	            sch.send("실패");
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("IO에러 종료ERR 리턴");
				return MenuChoice.ERROR;
			}
            
            return MenuChoice.ERROR;
        }
    }

    public String deleteEmployee(Socket cs) {
        DBUtil dbu = new DBUtil();
        try (Connection con = dbu.getConnection()) {
            SClientHandler sch = new SClientHandler(cs);

            String empId = sch.receive(); //삭제할 아이디 받음1<

            String query = "DELETE FROM Employee WHERE emp_id = ?";
            System.out.println(cs+" |DB|SQL: "+query);
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, empId);

            int rowsAffected = stmt.executeUpdate(); // 성공 실패 감지를 위해 

            if (rowsAffected > 0) {
                // 성공 메시지 전송
                sch.send("성공");// 성공/실패 전송2>
                System.out.println("전송:성공");
            } else {
                // 실패 메시지 전송
                sch.send("실패");// 성공/실패 전송2>
                System.out.println("전송:실패");
            }

        } catch (Exception e) {
            System.out.println("deleteEmployee:에러감지");
            e.printStackTrace();
            return MenuChoice.ERROR;
            
        }
        System.out.println("deleteEmployee:직원 삭제 성공");
        return AdminChoice.DELETE;
    }
    public String editEmployee(Socket cs) {
        DBUtil dbu = new DBUtil();
        try (Connection con = dbu.getConnection()) {
            SClientHandler sch = new SClientHandler(cs);

            String query = "UPDATE Employee SET emp_password = ?, emp_name = ?, emp_manager = ?, emp_department = ?, emp_phone_number = ?, emp_email = ?, emp_salary = ? WHERE emp_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(8, sch.receive()); // empId
            stmt.setString(1, sch.receive()); // empPassword
            stmt.setString(2, sch.receive()); // empName
            stmt.setString(3, sch.receive()); // empManager
            stmt.setString(4, sch.receive()); // empDepartment
            stmt.setLong(5, Long.parseLong(sch.receive())); // empPhoneNumber
            stmt.setString(6, sch.receive()); // empEmail
            stmt.setLong(7, Long.parseLong(sch.receive())); // empSalary

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // 성공 메시지 전송
                sch.send("성공");
            } else {
                // 실패 메시지 전송
                sch.send("실패");
            }

            return AdminChoice.EDIT;

        } catch (Exception e) {
            System.out.println("editEmployee:에러감지");
            e.printStackTrace();
            
            // 실패 메시지 전송
            SClientHandler sch;
			try {
				sch = new SClientHandler(cs);
	            sch.send("실패");
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("IO에러 종료ERR 리턴");
				return MenuChoice.ERROR;
			}
            return MenuChoice.ERROR;
        }
    }

}

