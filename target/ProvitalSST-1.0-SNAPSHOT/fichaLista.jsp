<%-- 
    Document   : fichaLista
    Created on : 2 ago. 2026, 12:39:48
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fichas - ${persona.nombre} ${persona.apellido}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <a href="persona?id=${persona.idPersona}" class="btn btn-secondary btn-sm mb-3">← Volver</a>
        <h2>Fichas Médicas de ${persona.nombre} ${persona.apellido}</h2>
        <c:if test="${!soloLectura}">
            <a href="ficha?accion=nueva&idPersona=${persona.idPersona}" class="btn btn-primary mb-3">+ Nueva Ficha</a>
        </c:if>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>N° Ficha</th>
                    <th>Fecha</th>
                    <th>Tipo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="f" items="${listaFichas}">
                    <tr>
                        <td>${f.numeroFicha}</td>
                        <td>${f.fecha}</td>
                        <td>${f.tipoEvaluacion}</td>
                        <td>
                            <a href="ficha?accion=descargar&idFicha=${f.idFicha}" target="_blank" class="btn btn-sm btn-success">Descargar PDF</a>
                            <c:if test="${!soloLectura}">
                                <a href="ficha?accion=eliminar&idFicha=${f.idFicha}&idPersona=${persona.idPersona}" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro?');">Eliminar</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>