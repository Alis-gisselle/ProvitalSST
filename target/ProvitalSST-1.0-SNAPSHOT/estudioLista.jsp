<%-- 
    Document   : estudioLista
    Created on : 1 ago. 2026, 19:56:19
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estudios - ${persona.nombre} ${persona.apellido}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
     <div class="container mt-4">
        <a href="persona?id=${persona.idPersona}" class="btn btn-secondary btn-sm mb-3">← Volver</a>
        <h2>Estudios de ${persona.nombre} ${persona.apellido}</h2>

        <c:if test="${!soloLectura}">
            <div class="card mb-4">
                <div class="card-body">
                    <h5>Subir Nuevo Estudio (PDF)</h5>
                    <form action="estudio" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="idPersona" value="${persona.idPersona}" />

                        <div class="mb-3">
                            <label class="form-label">Médico Laboral</label>
                            <select name="idMedicoLaboral" class="form-select" required>
                                <option value="">-- Seleccione --</option>
                                <c:forEach var="m" items="${listaMedicos}">
                                    <option value="${m.idMedicoLaboral}">${m.nombre} ${m.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Archivo PDF</label>
                            <input type="file" name="archivoPdf" class="form-control" accept="application/pdf" required />
                        </div>

                        <button type="submit" class="btn btn-primary">Subir Estudio</button>
                    </form>
                </div>
            </div>
        </c:if>

        <h5>Historial de Estudios</h5>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Fecha</th>
                    <th>Médico</th>
                    <th>Archivo</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="est" items="${listaEstudios}">
                    <tr>
                        <td>${est.fecha}</td>
                        <td>${est.nombreMedico}</td>
                        <td><a href="uploads/estudios/${est.archivoPdf}" target="_blank" class="btn btn-sm btn-info">Ver PDF</a></td>
                        <td>
                            <c:if test="${!soloLectura}">
                                <a href="estudio?accion=eliminar&id=${est.idEstudio}&idPersona=${persona.idPersona}" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro que deseas eliminar este estudio?');">Eliminar</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>