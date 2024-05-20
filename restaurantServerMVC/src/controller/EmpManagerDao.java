package controller;

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

                sch.send(employee.getEmpId());
                sch.send(employee.getEmpName());
                sch.send(employee.getEmpManager());
                sch.send(employee.getEmpDepartment());
                sch.send(employee.getEmpPhoneNumber());
                sch.send(employee.getEmpEmail());
                sch.send(employee.getEmpSalary());
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
//TODO 합쳐도 되나? ---------------------------------
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
            
             stmt.setString(1, sch.receive());
             stmt.setString(2,  sch.receive());
             stmt.setString(3,  sch.receive());
             stmt.setString(4,  sch.receive());
             stmt.setString(5,  sch.receive());
             stmt.setLong(6, Long.parseLong(sch.receive()));
             stmt.setString(7,  sch.receive());
             stmt.setLong(8, Long.parseLong(sch.receive()));
            
//--------------------------------------------
            stmt.executeUpdate();
            
            

			System.out.println("추가 작업 종료");
        } catch (Exception e) {
            System.out.println("addEmployee:에러감지");
            e.printStackTrace();
            
            return MenuChoice.ERROR;
        }
        
        System.out.println("addEmployee:직원 추가 성공");
        return AdminChoice.ADD;
    }

    public String deleteEmployee(Socket cs) {
        DBUtil dbu = new DBUtil();
        try (Connection con = dbu.getConnection()) {
            SClientHandler sch = new SClientHandler(cs);

            String empId = sch.receive();

            String query = "DELETE FROM Employee WHERE emp_id = ?";
            System.out.println(cs+" |DB|SQL: "+query);
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, empId);

            stmt.executeUpdate();
            
            //sch.send("pass");//TODO: add delete edit 에 성공여부 소통하기 

        } catch (Exception e) {
            System.out.println("deleteEmployee:에러감지");
            e.printStackTrace();
            return MenuChoice.ERROR;
            
        }
        System.out.println("deleteEmployee:직원 삭제 성공");
        return AdminChoice.DELETE;
    }

	public String editEmployee(Socket s) {
		
		return null;
	}
}

