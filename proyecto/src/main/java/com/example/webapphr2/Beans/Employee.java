package com.example.webapphr2.Beans;

import java.math.BigDecimal;

public class Employee {
    
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private Job job;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private Employee manager;
    private Department department;

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee() {
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the hireDate
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }


    /**
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * @return the commissionPct
     */
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    /**
     * @param commissionPct the commissionPct to set
     */
    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @return the manager
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    
}
