<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  if(session.getAttribute("usuarioLogueado") == null){
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Generaciones</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
</head>
<body class="p-4">

<div class="container">
  <div class="card shadow p-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h1>Generaciones</h1>
      <a class="btn btn-danger" href="${pageContext.request.contextPath}/LogoutServlet">Cerrar Sesión</a>
    </div>

    <table id="tablaGeneraciones" class="table table-striped table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>#</th>
        <th>Generación</th>
        <th>Periodo Mes</th>
        <th>Periodo Año</th>
        <th>Nomenclaturas</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="g" items="${listaGeneraciones}">
        <tr>
          <td>${g.idGeneracion}</td>
          <td>${g.nombre}</td>
          <td>${g.periodoMes}</td>
          <td>${g.periodoAnio}</td>
          <td>
            <a class="btn btn-primary btn-sm"
               href="${pageContext.request.contextPath}/NomenclaturaServlet?idGeneracion=${g.idGeneracion}">
              Ver Nomenclaturas
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<script>
  $(document).ready(function() {
    var table = $('#tablaGeneraciones').DataTable({
      "order": [[0, "asc"]] // ordenar inicialmente por id
    });

    $('#invertirOrdenGeneraciones').click(function() {
      var currentOrder = table.order();
      var column = currentOrder[0][0];
      var dir = currentOrder[0][1] === 'asc' ? 'desc' : 'asc';
      table.order([column, dir]).draw();
    });
  });
</script>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>