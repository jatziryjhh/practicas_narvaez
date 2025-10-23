<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Nomenclatura</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body { padding: 20px; background-color: #f8f9fa; }
        h2 { margin-bottom: 20px; }
        .form-container { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.15); max-width: 500px; margin: auto; }
        .btn { min-width: 100px; }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <c:set var="isEdit" value="${not empty nomenclatura.id}"/>
        <h2 class="text-primary">${isEdit ? "Modificar Nomenclatura" : "Agregar Nomenclatura"}</h2>

        <form action="form_nomenclatura" method="post">
            <input type="hidden" name="id" value="${nomenclatura.id}"/>
            <input type="hidden" name="generacionId" value="${nomenclatura.idGeneracion}"/>

            <div class="mb-3">
                <label class="form-label">Valor Numérico</label>
                <input type="number" name="valorNumerico" class="form-control" value="${nomenclatura.valorNumerico}" required/>
            </div>

            <div class="mb-3">
                <label class="form-label">Nomenclatura</label>
                <input type="text" name="descripcion" class="form-control" value="${nomenclatura.descripcion}" required/>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">${isEdit ? "Modificar" : "Agregar"}</button>
                <a href="nomenclaturas?generacionId=${nomenclatura.idGeneracion}" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>