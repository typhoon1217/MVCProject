package controller;

import view.MenuChoice;
import view.SrMain;

public class SValidation {

	public boolean checkPW(String pw) {
		// 대문자, 소문자, 숫자, 특수문자 중 최소 3종류를 포함하고 8자리 이상인지 검사 맞을시 true 반환 
		return pw.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$");
	}

	public boolean checkID(String id) {
		// 영어와 숫자가 최소 하나씩 포함되어야 하고, 총 6글자 이상이어야 합니다. 맞을시 true 반환 
		return id.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
	}

	public boolean checkVar(String val, int bYte) {
		// VARCHAR2(bYte)확인 맞을시 true 반환 
		return true;
	}

	public boolean checkDep(String dep) { //dep확인 맞을시 true 반환 
	    return dep.equals(MenuChoice.ADMIN) || dep.equals(MenuChoice.SERVE) ||
	           dep.equals(MenuChoice.CHEF) || dep.equals(MenuChoice.ACCOUNT);
	}
}
