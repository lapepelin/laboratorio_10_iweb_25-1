<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="job" scope="request" type="com.example.webapphr2.Beans.Job"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar un trabajo</title>
        <jsp:include page="../includes/headCss.jsp"></jsp:include>
    </head>
    <body>
        <div class='container'>
            <jsp:include page="../includes/navbar.jsp">
                <jsp:param name="currentPage" value="job"/>
            </jsp:include>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Editar un trabajo</h1>
                    <form method="POST" action="<%=request.getContextPath()%>/JobServlet?action=crear">
                        <input type="hidden" class="form-control" name="id" value="<%=job.getJobId()%>">

                        <div class="mb-3">
                            <label class="form-label">Job Title</label>
                            <input type="text" class="form-control" name="jobTitle" value="<%=job.getJobTitle()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Min Salary</label>
                            <input type="text" class="form-control" name="minSalary" value="<%=job.getMinSalary()%>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Max Salary</label>
                            <input type="text" class="form-control" name="maxSalary" value="<%=job.getMaxSalary()%>">
                        </div>
                        <a href="<%= request.getContextPath()%>/JobServlet" class="btn btn-danger">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
