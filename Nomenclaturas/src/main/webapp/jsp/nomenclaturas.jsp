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
  <title>Nomenclaturas</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
</head>
<body class="p-4">

<div class="container">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h1>Nomenclaturas</h1>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/LogoutServlet">Cerrar Sesión</a>
  </div>

  <div class="card shadow p-4">
    <!-- Info completa de la generación -->
    <div class="mb-3">
      <h3>Generación: ${generacion.nombre}</h3>
      <p>Periodo Mes: ${generacion.periodoMes} | Periodo Año: ${generacion.periodoAnio}</p>
    </div>

    <div class="d-flex justify-content-between mb-3">
      <a class="btn btn-success"
         href="${pageContext.request.contextPath}/NomenclaturaServlet?idGeneracion=${idGeneracion}&nuevo=true">
        + Agregar Nomenclatura
      </a>
    </div>

    <table id="tablaNomenclaturas" class="table table-striped table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>Valor Numérico</th>
        <th>Nomenclatura</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="n" items="${listaNomenclaturas}">
        <tr>
          <td>${n.valorNumerico}</td>
          <td>${n.descripcion}</td>
          <td>
            <a class="btn btn-warning btn-sm"
               href="${pageContext.request.contextPath}/NomenclaturaServlet?idNomenclatura=${n.idNomenclatura}">
              Modificar
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <div class="mt-3">
      <a class="btn btn-secondary"
         href="${pageContext.request.contextPath}/GeneracionServlet">
        Volver a Generaciones
      </a>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<script>
  $(document).ready(function() {
    var table = $('#tablaNomenclaturas').DataTable({
      "order": [[0, "asc"]]
    });

    $('#invertirOrden').click(function() {
      var currentOrder = table.order();
      var column = currentOrder[0][0];
      var dir = currentOrder[0][1] === 'asc' ? 'desc' : 'asc';
      table.order([column, dir]).draw();
    });
  });
</script>

</body>
</html>