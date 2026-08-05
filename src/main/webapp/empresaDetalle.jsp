<%-- 
    Document   : empresaDetalle
    Created on : 1 ago. 2026, 15:22:27
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Empleados - ${empresa.nombre}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Empleados de ${empresa.nombre}</h2>
        <a href="empresa" class="btn btn-secondary btn-sm mb-3">← Volver a Empresas</a>
        <a href="empleado?accion=nuevo&idEmpresa=${empresa.idEmpresaCliente}" class="btn btn-primary mb-3">+ Nuevo Empleado</a>

        <form action="empresa" method="get" class="row g-2 mb-3">
            <input type="hidden" name="accion" value="empleados" />
            <input type="hidden" name="id" value="${empresa.idEmpresaCliente}" />
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
                    <th>Cargo</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="emp" items="${listaEmpleados}">
                    <tr>
                        <td>${emp.nombre}</td>
                        <td>${emp.apellido}</td>
                        <td>${emp.ci}</td>
                        <td>${emp.cargo}</td>
                        <td>${emp.categoria}</td>
                        <td>
                            <a href="persona?id=${emp.idPersona}" class="btn btn-sm btn-info">Ver Detalle</a>
                            <a href="empleado?accion=editar&id=${emp.idPersona}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="empleado?accion=eliminar&id=${emp.idPersona}&idEmpresa=${empresa.idEmpresaCliente}"class="btn btn-sm btn-danger"onclick="return confirm('¿Seguro que deseas eliminar este empleado?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>