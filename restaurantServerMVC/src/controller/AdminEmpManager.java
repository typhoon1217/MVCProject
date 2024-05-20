package controller;

import java.net.Socket;

import view.MenuChoice;

public class AdminEmpManager {
	
	  public String list(Socket s) {
	        try {
	            EmpManagerDao dao = new EmpManagerDao();
	            return dao.loadEmployeeList(s);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("add에러");
	            return MenuChoice.ERROR;
	        }
	    }
	
	//서버 직원 추가 
    public String add(Socket s) {
        try {
            EmpManagerDao dao = new EmpManagerDao();
            return dao.addEmployee(s);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("add에러");
            return MenuChoice.ERROR;
        }
    }
    //서버 직원 삭제 
    public String delete(Socket s) {
        try {
            EmpManagerDao dao = new EmpManagerDao();
            return dao.deleteEmployee( s);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete에러");
            return MenuChoice.ERROR;
        }
    }
    //서버 직원 수정 
	public String edit(Socket s) {
        try {
            EmpManagerDao dao = new EmpManagerDao();
            return dao.editEmployee( s);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Edit에러");
            return MenuChoice.ERROR;
        }
    }
}
