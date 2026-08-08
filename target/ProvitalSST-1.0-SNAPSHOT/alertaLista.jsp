<%-- 
    Document   : alertaLista
    Created on : 8 ago. 2026, 10:45:59
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Alertas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>
        <h3>Alertas</h3>

        <c:if test="${listaCertificados != null}">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr><th>Trabajador</th><th>Tipo</th><th>Vence</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${listaCertificados}">
                        <tr>
                            <td>${a.nombrePersona} ${a.apellidoPersona}</td>
                            <td>${a.tipoAlerta}</td>
                            <td>${a.fechaVenc}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${listaPersonas != null}">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr><th>Nombre</th><th>Apellido</th><th>CI</th><th>Categoría</th><th> Empresa</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${listaPersonas}">
                        <tr>
                            <td>${p.nombre}</td>
                            <td>${p.apellido}</td>
                            <td>${p.ci}</td>
                            <td>${p.categoria}</td>
                            <td>${p.nombreEmpresa != null ? p.nombreEmpresa:'Independiente'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>