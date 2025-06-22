package com.example.webapphr2.Daos;


import com.example.webapphr2.Beans.Department;
import com.example.webapphr2.Beans.Employee;
import com.example.webapphr2.Beans.Job;
import com.example.webapphr2.Dto.EmpleadosPorDepartamentoDto;
import com.example.webapphr2.Dto.EmpleadosPorRegionDto;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao extends DaoBase {

    public ArrayList<Employee> listarEmpleados() {
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "SELECT * FROM employees e \n"
                + "left join jobs j on (j.job_id = e.job_id) \n"
                + "left join departments d on (d.department_id = e.department_id)\n"
                + "left  join employees m on (e.manager_id = m.employee_id)";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                fetchEmployeeData(employee, rs);

                listaEmpleados.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEmpleados;
    }

    public Employee obtenerEmpleado(int employeeId) {

        Employee employee = null;

        String sql = "SELECT * FROM employees e \n"
                + "left join jobs j ON (j.job_id = e.job_id) \n"
                + "left join departments d ON (d.department_id = e.department_id)\n"
                + "left  join employees m ON (e.manager_id = m.employee_id)\n"
                + "WHERE e.employee_id = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    employee = new Employee();
                    fetchEmployeeData(employee, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public void guardarEmpleado(Employee employee) throws SQLException {

        String sql = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            setEmployeeParams(pstmt, employee);
            pstmt.executeUpdate();
        }
    }

    public void actualizarEmpleado(Employee employee) throws SQLException {

        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, "
                + "hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, "
                + "manager_id = ?, department_id = ? WHERE employee_id = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            setEmployeeParams(pstmt, employee);
            pstmt.setInt(11, employee.getEmployeeId());
            pstmt.executeUpdate();
        }
    }

    public void borrarEmpleado(int employeeId) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        }
    }

    public ArrayList<Employee> buscarEmpleadosPorNombre(String name){

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "SELECT * FROM employees e \n"
                + "left join jobs j ON (j.job_id = e.job_id) \n"
                + "left join departments d ON (d.department_id = e.department_id)\n"
                + "left  join employees m ON (e.manager_id = m.employee_id)\n"
                + "WHERE e.first_name = ? OR e.last_name = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, name);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Employee employee = new Employee();
                    fetchEmployeeData(employee, rs);

                    listaEmpleados.add(employee);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaEmpleados;
    }

    private void setEmployeeParams(PreparedStatement pstmt, Employee employee) throws SQLException {
        pstmt.setString(1, employee.getFirstName());
        pstmt.setString(2, employee.getLastName());
        pstmt.setString(3, employee.getEmail());
        pstmt.setString(4, employee.getPhoneNumber());
        pstmt.setString(5, employee.getHireDate());
        pstmt.setString(6, employee.getJob().getJobId());
        pstmt.setBigDecimal(7, employee.getSalary());
        if (employee.getCommissionPct() == null) {
            pstmt.setNull(8, Types.DECIMAL);
        } else {
            pstmt.setBigDecimal(8, employee.getCommissionPct());
        }
        if (employee.getManager() == null) {
            pstmt.setNull(9, Types.INTEGER);
        } else {
            pstmt.setInt(9, employee.getManager().getEmployeeId());
        }
        pstmt.setInt(10, employee.getDepartment().getDepartmentId());
    }


    private void fetchEmployeeData(Employee employee, ResultSet rs) throws SQLException {
        employee.setEmployeeId(rs.getInt(1));
        employee.setFirstName(rs.getString(2));
        employee.setLastName(rs.getString(3));
        employee.setEmail(rs.getString(4));
        employee.setPhoneNumber(rs.getString(5));
        employee.setHireDate(rs.getString(6));

        Job job = new Job();
        job.setJobId(rs.getString(7));
        job.setJobTitle(rs.getString("job_title"));
        employee.setJob(job);

        employee.setSalary(rs.getBigDecimal(8));
        employee.setCommissionPct(rs.getBigDecimal(9));

        Employee manager = new Employee();
        manager.setEmployeeId(rs.getInt("e.manager_id"));
        manager.setFirstName(rs.getString("m.first_name"));
        manager.setLastName(rs.getString("m.last_name"));
        employee.setManager(manager);

        Department department = new Department();
        department.setDepartmentId(rs.getInt(11));
        department.setDepartmentName(rs.getString("d.department_name"));
        employee.setDepartment(department);
    }

    public boolean validarUsuarioPassword(String username, String password){

        String sql = "SELECT * FROM employees_credentials where email = ? and password = ?";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public boolean validarUsuarioPasswordHashed(String username, String password){

        String sql = "SELECT * FROM employees_credentials where email = ? and password_hashed = sha2(?,256)";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public Employee obtenerEmpleado(String email) {

        Employee employee = null;

        String sql = "SELECT * FROM employees e \n"
                + "left join jobs j ON (j.job_id = e.job_id) \n"
                + "left join departments d ON (d.department_id = e.department_id)\n"
                + "left  join employees m ON (e.manager_id = m.employee_id)\n"
                + "WHERE e.email = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    employee = new Employee();
                    fetchEmployeeData(employee, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public ArrayList<EmpleadosPorRegionDto> listarEmpPorRegion(){

        ArrayList<EmpleadosPorRegionDto> lista = new ArrayList<>();

        String sql = "select r.region_name, count(c.country_id) as '# empleados'\n" +
                "from employees e\n" +
                "inner join departments d on (e.department_id = d.department_id)\n" +
                "inner join locations l on (l.location_id = d.location_id)\n" +
                "inner join countries c on (c.country_id = l.country_id)\n" +
                "inner join regions r on (c.region_id = r.region_id)\n" +
                "group by r.region_id;\n";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmpleadosPorRegionDto e = new EmpleadosPorRegionDto();
                e.setRegion(rs.getString(1));
                e.setCantidad(rs.getInt(2));
                lista.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;

    }
}
