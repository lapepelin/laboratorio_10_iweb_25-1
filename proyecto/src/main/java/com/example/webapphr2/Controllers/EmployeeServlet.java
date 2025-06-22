package com.example.webapphr2.Controllers;

import com.example.webapphr2.Beans.Department;
import com.example.webapphr2.Beans.Employee;
import com.example.webapphr2.Beans.Job;
import com.example.webapphr2.Daos.DepartmentDao;
import com.example.webapphr2.Daos.EmployeeDao;
import com.example.webapphr2.Daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet", ""})
public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        EmployeeDao employeeDao = new EmployeeDao();
        JobDao jobDao = new JobDao();
        DepartmentDao departmentDao = new DepartmentDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaEmpleados", employeeDao.listarEmpleados());
                view = request.getRequestDispatcher("employees/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                request.setAttribute("listaJefes", employeeDao.listarEmpleados());

                view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                HttpSession httpSession = request.getSession();
                Employee employee = (Employee) httpSession.getAttribute("usuarioLogueado");

                if(employee != null && employee.getEmployeeId() > 0) {
                    if (request.getParameter("id") != null) {
                        String employeeIdString = request.getParameter("id");
                        int employeeId = 0;
                        try {
                            employeeId = Integer.parseInt(employeeIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("EmployeeServlet");
                        }

                        Employee emp = employeeDao.obtenerEmpleado(employeeId);

                        if (emp != null) {
                            request.setAttribute("empleado", emp);
                            request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                            request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                            request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                            view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect("EmployeeServlet");
                        }

                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }
                } else {
                    response.sendRedirect("EmployeeServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                    }

                    Employee emp = employeeDao.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        try {
                            employeeDao.borrarEmpleado(employeeId);
                            response.sendRedirect("EmployeeServlet?msg=Empleado borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                        }
                    }
                } else {
                    response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                }
                break;
            default:
                response.sendRedirect("EmployeeServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        EmployeeDao employeeDao = new EmployeeDao();

        switch (action) {
            case "guardar":
                Employee e = new Employee();
                e.setFirstName(request.getParameter("first_name"));
                e.setLastName(request.getParameter("last_name"));
                e.setEmail(request.getParameter("email"));
                e.setPhoneNumber(request.getParameter("phone"));
                e.setHireDate(request.getParameter("hire_date"));
                e.setSalary(new BigDecimal(request.getParameter("salary")));
                e.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));

                String jobId = request.getParameter("job_id");
                Job job = new Job(jobId);
                e.setJob(job);

                String managerId = request.getParameter("manager_id");
                if (!managerId.equals("sin-jefe")) {
                    Employee manager = new Employee(Integer.parseInt(managerId));
                    e.setManager(manager);
                }

                String departmentId = request.getParameter("department_id");
                Department department = new Department(Integer.parseInt(departmentId));
                e.setDepartment(department);


                if (request.getParameter("employee_id") == null) {
                    try {
                        employeeDao.guardarEmpleado(e);
                        response.sendRedirect("EmployeeServlet?msg=Empleado creado exitosamente");
                    } catch (SQLException ex) {
                        response.sendRedirect("EmployeeServlet?err=Error al crear empleado");
                    }
                } else {
                    e.setEmployeeId(Integer.parseInt(request.getParameter("employee_id")));
                    try {
                        employeeDao.actualizarEmpleado(e);
                        response.sendRedirect("EmployeeServlet?msg=Empleado actualizado exitosamente");
                    } catch (SQLException ex) {
                        response.sendRedirect("EmployeeServlet?err=Error al actualizar empleado");
                    }
                }
                break;
            case "buscar":
                String textoBuscar = request.getParameter("textoBuscar");
                if (textoBuscar == null) {
                    response.sendRedirect("EmployeeServlet");
                } else {
                    request.setAttribute("textoBusqueda",textoBuscar);
                    request.setAttribute("listaEmpleados", employeeDao.buscarEmpleadosPorNombre(textoBuscar));
                    RequestDispatcher view = request.getRequestDispatcher("employees/lista.jsp");
                    view.forward(request, response);
                }
                break;
        }
    }

}
