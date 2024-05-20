package model;

public class EmployeeVo {
    private String empId;
    private String empPassword;
    private String empName;
    private String empManager;
    private String empDepartment;
    private String empPhoneNumber; // changed to String
    private String empEmail;
    private String empSalary; // changed to String

    public EmployeeVo() {
        super();
    }

    public EmployeeVo(String empId, String empPassword, String empName, String empManager, String empDepartment, String empPhoneNumber, String empEmail, String empSalary) {
        this.empId = empId;
        this.empPassword = empPassword;
        this.empName = empName;
        this.empManager = empManager;
        this.empDepartment = empDepartment;
        this.empPhoneNumber = empPhoneNumber;
        this.empEmail = empEmail;
        this.empSalary = empSalary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpManager() {
        return empManager;
    }

    public void setEmpManager(String empManager) {
        this.empManager = empManager;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public String getEmpPhoneNumber() {
        return empPhoneNumber;
    }

    public void setEmpPhoneNumber(String empPhoneNumber) {
        this.empPhoneNumber = empPhoneNumber;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(String empSalary) {
        this.empSalary = empSalary;
    }

    // 타입 변환용 SETTERS;
    public long getEmpPhoneNumberAsInt() {
        return Integer.parseInt(empPhoneNumber);
    }

    public void setEmpPhoneNumberFromInt(int empPhoneNumber) {
        this.empPhoneNumber = Integer.toString(empPhoneNumber);
    }

    public double getEmpSalaryAsInt() {
        return Integer.parseInt(empSalary);
    }

    public void setEmpSalaryFromInt(int empSalary) {
        this.empSalary = Integer.toString(empSalary);
    }

	@Override
	public String toString() {
		return "EmployeeVo [empId=" + empId + ", empPassword=" + empPassword + ", empName=" + empName + ", empManager="
				+ empManager + ", empDepartment=" + empDepartment + ", empPhoneNumber=" + empPhoneNumber + ", empEmail="
				+ empEmail + ", empSalary=" + empSalary + "]";
	}
}

