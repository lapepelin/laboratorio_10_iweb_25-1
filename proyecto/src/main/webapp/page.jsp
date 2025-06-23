<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webapphr2.Beans.Tablero" %>
<%@ page import="com.example.webapphr2.Beans.Configuracion" %>
<jsp:useBean id="tablero" type="com.example.webapphr2.Beans.Tablero" scope="request"/>
<jsp:useBean id="configuracion" type="com.example.webapphr2.Beans.Configuracion" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Buscaminas</title>
</head>
<body>
<div class="container">
    <h1>Buscaminas</h1>
    <form method="post" action="<%=request.getContextPath()%>/buscaminas">
        X: <input type="number" name="x" required>
        Y: <input type="number" name="y" required>
        <button type="submit">Jugar</button>
    </form>
    <table class="table table-bordered mt-3">
        <% if(tablero != null && configuracion != null){
            for(int i=0;i<configuracion.getDimMinaX();i++){ %>
        <tr>
            <% for(int j=0;j<configuracion.getDimMinaY();j++){ %>
            <td style="width:30px;height:30px;text-align:center;">
                <%
                    Tablero.EstadoCelda est = tablero.obtenerEstado(i,j);
                    if(est == Tablero.EstadoCelda.REVELADA){
                        int c = tablero.contarBombasAlrededor(i,j);
                        out.print(c);
                    } else if(est == Tablero.EstadoCelda.MARCADA){
                        out.print("F");
                    } else {
                        out.print("?");
                    }
                %>
            </td>
            <% } %>
        </tr>
        <% }
        } %>
    </table>
</div>
</body>
</html>