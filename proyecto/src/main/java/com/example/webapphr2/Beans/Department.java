package com.example.webapphr2.Beans;

public class Department {
    
    private int departmentId;
    private String departmentName;
    private int managerId;
    private int locationId;

    public Department() {
    }

    public Department(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the managerId
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
