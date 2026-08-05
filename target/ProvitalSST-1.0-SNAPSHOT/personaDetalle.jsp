<%-- 
    Document   : personaDetalle
    Created on : 1 ago. 2026, 19:42:59
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${persona.nombre} ${persona.apellido}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>

        <div class="card mb-4">
            <div class="card-body">
                <h3>${persona.nombre} ${persona.apellido}</h3>
                <p><strong>CI:</strong> ${persona.ci}</p>
                <p><strong>Tipo:</strong> ${tipo == 'empleado' ? 'Empleado' : 'Colaborador'}</p>
                <c:if test="${nombreEmpresa != null}">
                    <p><strong>Empresa:</strong> ${nombreEmpresa}</p>
                </c:if>
                <p><strong>Categoría:</strong> ${persona.categoria == 'admisional' ? 'Admisional' : 'Manipulador de Alimentos'}</p>
            </div>
        </div>

        <div class="row g-4">
            <c:if test="${persona.categoria == 'admisional'}">
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <h5>Ficha</h5>
                            <a href="ficha?idPersona=${persona.idPersona}" class="btn btn-primary">Ver / Crear Ficha</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5>Estudios</h5>
                        <a href="estudio?idPersona=${persona.idPersona}" class="btn btn-primary">Ver / Subir Estudios</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5>Certificado</h5>
                        <c:choose>
                            <c:when test="${persona.categoria == 'admisional'}">
                                <a href="certifMed?idPersona=${persona.idPersona}" class="btn btn-primary">Ver / Crear Certificado</a>
                            </c:when>
                            <c:otherwise>
                                <a href="certifManipulador?idPersona=${persona.idPersona}" class="btn btn-primary">Ver / Crear Certificado</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>