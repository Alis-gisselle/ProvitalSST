<%-- 
    Document   : colaboradorLista
    Created on : 1 ago. 2026, 16:25:16
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Colaboradores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Colaboradores</h2>
        <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>

        <a href="colaborador?accion=nuevo" class="btn btn-primary mb-3">+ Nuevo Colaborador</a>

        <form action="colaborador" method="get" class="row g-2 mb-3">
            <div class="col-auto">
                <input type="text" name="buscar" class="form-control" placeholder="Buscar por nombre o CI" value="${buscar}" />
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Buscar</button>
            </div>
        </form>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>CI</th>
                    <th>Correo</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="col" items="${listaColaboradores}">
                    <tr>
                        <td>${col.nombre}</td>
                        <td>${col.apellido}</td>
                        <td>${col.ci}</td>
                        <td>${col.correo}</td>
                        <td>${col.categoria}</td>
                        <td>
                            <a href="persona?id=${col.idPersona}" class="btn btn-sm btn-info">Ver Detalle</a>
                            <a href="colaborador?accion=editar&id=${col.idPersona}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="colaborador?accion=eliminar&id=${col.idPersona}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Seguro que deseas eliminar este colaborador?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>