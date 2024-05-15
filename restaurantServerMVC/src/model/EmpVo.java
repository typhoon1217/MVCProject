package model;

public class EmpVo {
	private String emp_id;
	private String emp_password;
	private String emp_name;
	private String emp_manager;
	private String emp_department;
	private String emp_contact;
	private String emp_salary;

	public EmpVo() {
		super();
	}

	public EmpVo(String emp_id, String emp_password, String emp_name, String emp_manager, String emp_department,
			String emp_contact, String emp_salary) {
		super();
		this.emp_id = emp_id;
		this.emp_password = emp_password;
		this.emp_name = emp_name;
		this.emp_manager = emp_manager;
		this.emp_department = emp_department;
		this.emp_contact = emp_contact;
		this.emp_salary = emp_salary;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_manager() {
		return emp_manager;
	}

	public void setEmp_manager(String emp_manager) {
		this.emp_manager = emp_manager;
	}

	public String getEmp_department() {
		return emp_department;
	}

	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}

	public String getEmp_contact() {
		return emp_contact;
	}

	public void setEmp_contact(String emp_contact) {
		this.emp_contact = emp_contact;
	}

	public String getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(String emp_salary) {
		this.emp_salary = emp_salary;
	}

	@Override
	public String toString() {
		return "EmpVo [emp_id=" + emp_id + ", emp_password=" + emp_password + ", emp_name=" + emp_name
				+ ", emp_manager=" + emp_manager + ", emp_department=" + emp_department + ", emp_contact=" + emp_contact
				+ ", emp_salary=" + emp_salary + "]";
	}

}
