<%-- 
    Document   : medicoLista
    Created on : 1 ago. 2026, 16:43:36
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Médicos Laborales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Médicos Laborales</h2>
        <a href="configuracion" class="btn btn-secondary btn-sm mb-3">← Volver a Configuración</a>

        <a href="medico?accion=nuevo" class="btn btn-primary mb-3">+ Nuevo Médico</a>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Matrícula</th>
                    <th>Correo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="med" items="${listaMedicos}">
                    <tr>
                        <td>${med.nombre}</td>
                        <td>${med.apellido}</td>
                        <td>${med.especialidad}</td>
                        <td>${med.matricula}</td>
                        <td>${med.correo}</td>
                        <td>
                            <a href="medico?accion=editar&id=${med.idMedicoLaboral}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="medico?accion=eliminar&id=${med.idMedicoLaboral}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Seguro que deseas eliminar este médico?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>