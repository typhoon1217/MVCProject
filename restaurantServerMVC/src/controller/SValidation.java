package controller;

import view.MenuChoice;
import view.SrMain;

//TODO VALIDATION 정상 작동을 위해 한글입력은 BYTE 제한 제한대신 NVARCHAR2로 글자수제한 걸기 

public class SValidation {

//	public boolean checkPW(String pw) {
//		// 대문자, 소문자, 숫자, 특수문자 중 최소 3종류를 포함하고 8자리 이상인지 검사 맞을시 true 반환 
//		return pw.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$");
//	}

	public boolean checkPW(String pw) {
	    if (pw.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$")) {
	    	if(checkVar(pw,10)) {return true;}return false;
	    } else {
	        System.out.println("비밀번호는 대문자, 소문자, 숫자, 특수문자 중 최소 3종류를 포함하고 8자리 이상이어야 합니다.");
	        return false;
	    }
	}

//	public boolean checkID(String id) {
//		// 영어와 숫자가 최소 하나씩 포함되어야 하고, 총 6글자 이상이어야 합니다. 맞을시 true 반환 
//		return id.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
//	}
	public boolean checkID(String id) {
	    if (id.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
	    	if(checkVar(id,10)) {return true;}return false;
	    } else {
	        System.out.println("아이디는 영어와 숫자가 최소 하나씩 포함되어야 하고, 총 6글자 이상이어야 합니다.");
	        return false;
	    }
	}

//	public boolean EmpCheckDep(String dep) { //dep확인 맞을시 true 반환 
//	    return dep.equals(MenuChoice.ADMIN) || dep.equals(MenuChoice.SERVE) ||
//	           dep.equals(MenuChoice.CHEF) || dep.equals(MenuChoice.ACCOUNT);
//	}
	public boolean EmpCheckDep(String dep) {
	    boolean isValid = dep.equals(MenuChoice.ADMIN) || dep.equals(MenuChoice.SERVE) ||
	                      dep.equals(MenuChoice.CHEF) || dep.equals(MenuChoice.ACCOUNT);
	    if (!isValid) {
	        System.out.println("부서가 올바르지 않습니다.");
		    return isValid;
	    } 
    	if(checkVar(dep,10)) {return true;}return false;
	}

//	public boolean checkVar(String v, int bYte) {
//	    return (v.length() <= bYte);
//	}
	public boolean checkVar(String v, int n) {
	    if (v.length() <= n) {
	        return true;
	    } else {
	        System.out.println("입력된 값이 너무 깁니다. 최대 " + n + "자 이하여야 합니다.");
	        return false;
	    }
	}
}
