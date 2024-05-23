package view;

public interface LoginChoice {
	//부서 
	public static final String ADMIN = "admin";
	public static final String SERVE = "server";
	public static final String CHEF = "chef";
	public static final String ACCOUNT = "account";
	
	//에러 실패 처리용

	public static final String PASS = "PASS";
	public static final String FAIL = "FAILL";
	public static final String ERROR = "ERROR";
	
	//로그아웃용
	public static final String LOGOUT = "0";
}
