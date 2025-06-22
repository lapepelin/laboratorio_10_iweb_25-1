<%@ page import="com.example.webapphr2.Beans.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLogueado" scope="session" type="Employee" class="com.example.webapphr2.Beans.Employee" />

<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>">Gestión HR</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link <%=currentPage.equals("emp") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/EmployeeServlet">
                        Employees
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=currentPage.equals("job") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/JobServlet">
                        Jobs
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%=currentPage.equals("stats") ? "active" : ""%>"
                       href="<%=request.getContextPath()%>/StatServlet">
                        Stats
                    </a>
                </li>
                <li class="nav-item">
                    <% if(usuarioLogueado.getEmployeeId() == 0){ %>
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;"
                       href="<%=request.getContextPath()%>/LoginServlet">
                        (Iniciar sesión)
                    </a>
                    <% }else{ %>
                    <a class="nav-link disabled"><%=usuarioLogueado.getFirstName() + " " + usuarioLogueado.getLastName()%></a>
                    <% } %>
                </li>
                <% if(usuarioLogueado.getEmployeeId() != 0){ %>
                <li class="nav-item">
                    <a class="nav-link" style="text-decoration: underline;color: #0d6efd;" href="<%=request.getContextPath()%>/LoginServlet?a=lo">(Cerrar sesión)</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
