<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.webapphr2.Dto.EmpleadosPorDepartamentoDto" %>
<%@ page import="com.example.webapphr2.Dto.EmpleadosPorRegionDto" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="lista" type="java.util.ArrayList<com.example.webapphr2.Dto.EmpleadosPorDepartamentoDto>" scope="request"/>
<jsp:useBean id="listaEmpPorRegion" type="java.util.ArrayList<com.example.webapphr2.Dto.EmpleadosPorRegionDto>" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <title>Lista empleados</title>
        <jsp:include page="./includes/headCss.jsp"></jsp:include>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="./includes/navbar.jsp">
                <jsp:param name="currentPage" value="emp"/>
            </jsp:include>
            <div class="row mb-5 mt-4">
                <div class="col-md-7">
                    <h1>Estadísticas</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <h3>Cantidad de empleados por departamento</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Departamento</th>
                                <th>Cantidad empleados</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int i = 1;
                                for (EmpleadosPorDepartamentoDto e : lista) {
                            %>
                            <tr>
                                <td><%= i%>
                                </td>
                                <td><%= e.getDepartmentName()%>
                                </td>
                                <td><%= e.getCantidad()%>
                                </td>
                            </tr>
                            <% i++;  } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-4">
                    <h3>Cantidad de empleados por región</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Región</th>
                                <th>Cantidad empleados</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int j = 1;
                                for (EmpleadosPorRegionDto e : listaEmpPorRegion) {
                            %>
                            <tr>
                                <td><%= j%>
                                </td>
                                <td><%= e.getRegion()%>
                                </td>
                                <td><%= e.getCantidad()%>
                                </td>
                            </tr>
                            <% j++;  } %>
                        </tbody>
                    </table>
                </div>
            </div>


            <jsp:include page="./includes/footer.jsp"/>
        </div>
    </body>
</html>
