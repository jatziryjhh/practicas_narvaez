<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Generaciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body { padding: 20px; background-color: #f8f9fa; }
        h2 { margin-bottom: 20px; }
        table { background-color: #fff; }
        .btn-sm { font-size: 0.8rem; }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-primary">Lista de Generaciones</h2>

    <!-- Alerta si no hay generaciones -->
    <c:if test="${empty listaGeneraciones}">
        <div class="alert alert-warning">No hay generaciones disponibles.</div>
    </c:if>

    <!-- Tabla de generaciones -->
    <c:if test="${not empty listaGeneraciones}">
        <table class="table table-striped table-hover shadow-sm">
            <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Generación</th>
                <th>Periodo Mes</th>
                <th>Periodo Año</th>
                <th>Nomenclaturas</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="gen" items="${listaGeneraciones}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${gen.nombre}</td>
                    <td>${gen.periodoMes}</td>
                    <td>${gen.periodoAnio}</td>
                    <td>
                        <a href="nomenclaturas?generacionId=${gen.id}" class="btn btn-primary btn-sm">Ver Nomenclaturas</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>