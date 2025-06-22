package com.example.webapphr2.Controllers;


import com.example.webapphr2.Daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", urlPatterns = {"/JobServlet"})
public class JobServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        JobDao jobDao = new JobDao();
        RequestDispatcher view;
        Job job;
        String jobId;

        switch (action) {
            case "formCrear":
                view = request.getRequestDispatcher("jobs/newJob.jsp");
                view.forward(request, response);
                break;
            case "crear":
                jobId = request.getParameter("id");
                String jobTitle = request.getParameter("jobTitle");
                int minSalary = Integer.parseInt(request.getParameter("minSalary"));
                int maxSalary = Integer.parseInt(request.getParameter("maxSalary"));

                job = jobDao.obtenerTrabajo(jobId);

                if (job == null) {
                    try {
                        jobDao.crearTrabajo(jobId, jobTitle, minSalary, maxSalary);
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("msg","Trabajo creado exitosamente");
                        response.sendRedirect(request.getContextPath() + "/JobServlet");
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/JobServlet?err=Error al actualizar el trabajo");
                    }
                } else {
                    try {
                        jobDao.actualizarTrabajo(jobId, jobTitle, minSalary, maxSalary);
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("msg","Trabajo editado exitosamente");
                        response.sendRedirect(request.getContextPath() + "/JobServlet");
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/JobServlet?err=Error al actualizar el trabajo");
                    }
                }

                break;
            case "lista":
                ArrayList<Job> listaTrabajos = jobDao.listarTrabajos();

                request.setAttribute("lista", listaTrabajos);

                view = request.getRequestDispatcher("jobs/listaJobs.jsp");
                view.forward(request, response);
                break;

            case "editar":
                jobId = request.getParameter("id");
                job = jobDao.obtenerTrabajo(jobId);
                if (job == null) {
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } else {
                    request.setAttribute("job", job);
                    view = request.getRequestDispatcher("jobs/updateJob.jsp");
                    view.forward(request, response);
                }
                break;
            case "borrar":
                jobId = request.getParameter("id");
                if (jobDao.obtenerTrabajo(jobId) != null) {
                    try {
                        jobDao.borrarTrabajo(jobId);
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/JobServlet?err=Error al borrar el trabajo");
                    }
                }
                response.sendRedirect(request.getContextPath() + "/JobServlet?msg=Trabajo borrado exitosamente");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
