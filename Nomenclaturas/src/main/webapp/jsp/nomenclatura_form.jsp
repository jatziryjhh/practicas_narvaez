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
  <title>Formulario Nomenclatura</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body class="p-4">

<div class="container">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h1>
      <c:choose>
        <c:when test="${not empty nomenclatura}">Modificar Nomenclatura</c:when>
        <c:otherwise>Agregar Nomenclatura</c:otherwise>
      </c:choose>
    </h1>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/LogoutServlet">Cerrar Sesión</a>
  </div>

  <div class="card shadow p-4">
    <form action="${pageContext.request.contextPath}/NomenclaturaServlet" method="post">
      <input type="hidden" name="idNomenclatura" value="${nomenclatura.idNomenclatura}">
      <input type="hidden" name="idGeneracion"
             value="${nomenclatura.idGeneracion != null ? nomenclatura.idGeneracion : param.idGeneracion}">

      <div class="mb-3">
        <label class="form-label">Valor Numérico</label>
        <input type="number" class="form-control" name="valorNumerico"
               value="${nomenclatura.valorNumerico}" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Nomenclatura</label>
        <input type="text" class="form-control" name="descripcion"
               value="${nomenclatura.descripcion}" required>
      </div>

      <button type="submit" class="btn btn-primary">Guardar</button>
      <a class="btn btn-secondary"
         href="${pageContext.request.contextPath}/NomenclaturaServlet?idGeneracion=${nomenclatura.idGeneracion != null ? nomenclatura.idGeneracion : param.idGeneracion}">
        Cancelar
      </a>
    </form>
  </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
