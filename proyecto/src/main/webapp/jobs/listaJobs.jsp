<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.webapphr2.Beans.Job" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean type="java.util.ArrayList<com.example.webapphr2.Beans.Job>" scope="request" id="lista"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista trabajos</title>
        <jsp:include page="../includes/headCss.jsp"></jsp:include>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="job"/>
            </jsp:include>
            <div class="row mb-5 mt-4">
                <div class="col-md-7">
                    <h1>Lista de trabajos en hr</h1>
                </div>
                <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
                    <a href="<%= request.getContextPath()%>/JobServlet?action=formCrear" class="btn btn-primary">Agregar
                        nuevo Trabajo</a>
                </div>
            </div>
            <% if (session.getAttribute("msg") != null) {%>
            <div class="alert alert-success" role="alert"><%=session.getAttribute("msg")%>
            </div>
            <%
                session.removeAttribute("msg");
            } %>
            <% if (request.getParameter("err") != null) {%>
            <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
            </div>
            <% } %>
            <table class="table">
                <tr>
                    <th>#</th>
                    <th>Job ID</th>
                    <th>Job Name</th>
                    <th>Min Salary</th>
                    <th>Max Salary</th>
                    <th></th>
                    <th></th>
                </tr>
                <%
                    int i = 1;
                    for (Job job : lista) {
                %>
                <tr>
                    <td><%=i%>
                    </td>
                    <td><%=job.getJobId()%>
                    </td>
                    <td><%=job.getJobTitle()%>
                    </td>
                    <td><%=job.getMinSalary()%>
                    </td>
                    <td><%=job.getMaxSalary()%>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/JobServlet?action=editar&id=<%=job.getJobId()%>">
                            Editar
                        </a>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/JobServlet?action=borrar&id=<%=job.getJobId()%>">
                            Borrar
                        </a>
                    </td>
                </tr>
                <%
                        i++;
                    }
                %>
            </table>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>