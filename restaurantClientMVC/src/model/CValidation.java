package model;

import view.AdminChoice;
import view.MenuChoice;

public class CValidation {


	public boolean checkPW(String pw) {
	    if (pw.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
	    	if(checkVar(pw,20)) {return true;}return false;
	    } else {
	        System.out.println("비밀번호는 글자 1개, 숫자 1개, 특수문자 1개 포함하고 8자리 이상이어야 합니다.");
	        return false;
	    }
	}

	public boolean checkID(String id) {
	    if (id.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
	    	if(checkVar(id,20)) {return true;}return false;
	    } else {
	        System.out.println("아이디는 영어와 숫자가 최소 하나씩 포함되어야 하고, 총 6글자 이상이어야 합니다.");
	        return false;
	    }
	}
	public boolean EmpCheckDep(String dep) {
	    boolean isValid = dep.equals(MenuChoice.ADMIN) || dep.equals(MenuChoice.SERVE) ||
	                      dep.equals(MenuChoice.CHEF) || dep.equals(MenuChoice.ACCOUNT);
	    if (!isValid) {
	        System.out.println("부서가 올바르지 않습니다.");
		    return isValid;
	    } 
    	if(checkVar(dep,10)) {return true;}return false;
	}
	
	public boolean checkVar(String v, int n) {
	    if (v.length() <= n) {
	        return true;
	    } else {
	        System.out.println("입력된 값이 너무 깁니다. 최대 " + n + "자 이하여야 합니다.");
	        return false;
	    }
	}

	public boolean isValidCID(String clientID) {
		// TODO Auto-generated method stub
		return false;
	}

}

