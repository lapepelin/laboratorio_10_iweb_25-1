package com.example.webapphr2.Daos;


import com.example.webapphr2.Dto.EmpleadosPorDepartamentoDto;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> listaDepartamentos() {
        ArrayList<Department> listaDepartamentos = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM departments");) {

            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));

                listaDepartamentos.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDepartamentos;
    }

    public ArrayList<EmpleadosPorDepartamentoDto> listarEmpXDepa(){

        String sql = "SELECT department_name, count(*) `Cantidad` \n" +
                "FROM employees e inner join departments d on e.department_id = d.department_id\n" +
                "where e.department_id is not null \n" +
                "group by e.department_id";

        ArrayList<EmpleadosPorDepartamentoDto> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmpleadosPorDepartamentoDto e = new EmpleadosPorDepartamentoDto();
                e.setDepartmentName(rs.getString(1));
                e.setCantidad(rs.getInt(2));

                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;

    }
}
