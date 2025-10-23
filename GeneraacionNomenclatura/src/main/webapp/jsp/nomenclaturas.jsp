<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nomenclaturas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body { padding: 20px; background-color: #f8f9fa; }
        h2 { margin-bottom: 10px; }
        table { background-color: #fff; }
        .btn-sm { font-size: 0.8rem; }
        .btn-success { margin-bottom: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-success">Nomenclaturas - Generación ${generacion.nombre}</h2>
    <p>Periodo Mes: <strong>${generacion.periodoMes}</strong> | Periodo Año: <strong>${generacion.periodoAnio}</strong></p>

    <a href="form_nomenclatura.jsp?generacionId=${generacion.id}" class="btn btn-success">+ Agregar Nomenclatura</a>

    <table class="table table-bordered table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>#</th>
            <th>Valor Numérico</th>
            <th>Nomenclatura</th>
            <th>Modificar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="n" items="${listaNomenclaturas}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${n.valorNumerico}</td>
                <td>${n.descripcion}</td>
                <td>
                    <a href="form_nomenclatura.jsp?id=${n.id}" class="btn btn-warning btn-sm">Modificar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="generaciones" class="btn btn-secondary">Volver a Generaciones</a>
</div>
</body>
</html>