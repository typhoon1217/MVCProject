package view;

public interface MenuChoice {
	//부서 
	public static final String ADMIN = "admin";
	public static final String SERVE = "server";
	public static final String CHEF = "chef";
	public static final String ACCOUNT = "account";
	
	//에러 실패 처리용

	public static final String PASS = "/PASS?PASSED/";
	public static final String FAIL = "/FAIL?REPEAT/";
	public static final String ERROR = "/ERROR?END/";
	
	//로그아웃용
	public static final String LOGOUT = "0";
}

