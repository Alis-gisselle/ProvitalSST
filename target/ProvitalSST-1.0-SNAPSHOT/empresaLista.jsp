<%-- 
    Document   : empresaLista
    Created on : 1 ago. 2026, 15:29:44
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Empresas Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Listado de Empresas Cliente</h2>
        <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>

        <a href="empresa?accion=nuevo" class="btn btn-primary mb-3">+ Nueva Empresa</a>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>RUC</th>
                    <th>Nombre</th>
                    <th>Ciudad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="emp" items="${listaEmpresas}">
                    <tr>
                        <td>${emp.idEmpresaCliente}</td>
                        <td>${emp.ruc}</td>
                        <td>${emp.nombre}</td>
                        <td>${emp.direccion}</td>
                        <td>
                            <a href="empresa?accion=empleados&id=${emp.idEmpresaCliente}" class="btn btn-sm btn-info">Ver Empleados</a>
                            <a href="empresa?accion=editar&id=${emp.idEmpresaCliente}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="empresa?accion=eliminar&id=${emp.idEmpresaCliente}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Seguro que deseas eliminar esta empresa?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>