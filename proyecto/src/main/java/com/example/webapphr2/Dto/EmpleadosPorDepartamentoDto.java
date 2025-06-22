package com.example.webapphr2.Dto;

public class EmpleadosPorDepartamentoDto {
    private String departmentName;
    private int cantidad;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
