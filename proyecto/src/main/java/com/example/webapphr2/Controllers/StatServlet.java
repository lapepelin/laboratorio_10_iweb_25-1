package com.example.webapphr2.Controllers;

import com.example.webapphr2.Daos.DepartmentDao;
import com.example.webapphr2.Daos.EmployeeDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "StatServlet", value = "/StatServlet")
public class StatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DepartmentDao departmentDao = new DepartmentDao();
        EmployeeDao employeeDao = new EmployeeDao();

        request.setAttribute("lista",departmentDao.listarEmpXDepa());
        request.setAttribute("listaEmpPorRegion",employeeDao.listarEmpPorRegion());
        request.getRequestDispatcher("stats.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}