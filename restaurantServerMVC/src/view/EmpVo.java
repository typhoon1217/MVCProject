package view;

public class EmpVo {
	private String EMP_ID;
	private String EMP_PASSWORD;
	private String EMP_NAME;
	private String EMP_MANAGER;
	private String EMP_DEPARTMENT;
	private String EMP_CONTACT;
	private String EMP_SALARY;
	
	public EmpVo() {
		super();
	}
	
	public EmpVo(String eMP_ID, String eMP_PASSWORD, String eMP_NAME, String eMP_MANAGER, String eMP_DEPARTMENT,
			String eMP_CONTACT, String eMP_SALARY) {
		super();
		EMP_ID = eMP_ID;
		EMP_PASSWORD = eMP_PASSWORD;
		EMP_NAME = eMP_NAME;
		EMP_MANAGER = eMP_MANAGER;
		EMP_DEPARTMENT = eMP_DEPARTMENT;
		EMP_CONTACT = eMP_CONTACT;
		EMP_SALARY = eMP_SALARY;
	}

	public String getEMP_ID() {
		return EMP_ID;
	}

	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID;
	}

	public String getEMP_PASSWORD() {
		return EMP_PASSWORD;
	}

	public void setEMP_PASSWORD(String eMP_PASSWORD) {
		EMP_PASSWORD = eMP_PASSWORD;
	}

	public String getEMP_NAME() {
		return EMP_NAME;
	}

	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}

	public String getEMP_MANAGER() {
		return EMP_MANAGER;
	}

	public void setEMP_MANAGER(String eMP_MANAGER) {
		EMP_MANAGER = eMP_MANAGER;
	}

	public String getEMP_DEPARTMENT() {
		return EMP_DEPARTMENT;
	}

	public void setEMP_DEPARTMENT(String eMP_DEPARTMENT) {
		EMP_DEPARTMENT = eMP_DEPARTMENT;
	}

	public String getEMP_CONTACT() {
		return EMP_CONTACT;
	}

	public void setEMP_CONTACT(String eMP_CONTACT) {
		EMP_CONTACT = eMP_CONTACT;
	}

	public String getEMP_SALARY() {
		return EMP_SALARY;
	}

	public void setEMP_SALARY(String eMP_SALARY) {
		EMP_SALARY = eMP_SALARY;
	}

	@Override
	public String toString() {
		return "EmpVo [EMP_ID=" + EMP_ID + ", EMP_PASSWORD=" + EMP_PASSWORD + ", EMP_NAME=" + EMP_NAME
				+ ", EMP_MANAGER=" + EMP_MANAGER + ", EMP_DEPARTMENT=" + EMP_DEPARTMENT + ", EMP_CONTACT=" + EMP_CONTACT
				+ ", EMP_SALARY=" + EMP_SALARY + "]";
	}

	
	
}
