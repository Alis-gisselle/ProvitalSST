<%-- 
    Document   : certifManipuladorLista
    Created on : 2 ago. 2026, 11:32:21
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Certificado Manipulador - ${persona.nombre} ${persona.apellido}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <a href="persona?id=${persona.idPersona}" class="btn btn-secondary btn-sm mb-3">← Volver</a>
        <h2>Certificado Manipulador de Alimentos - ${persona.nombre} ${persona.apellido}</h2>

        <div class="card mb-4">
            <div class="card-body">
                <h5>Nuevo Certificado</h5>
                <form action="certifManipulador" method="post">
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
                        <label class="form-label">Aptitud</label>
                        <select name="aptitud" class="form-select" required>
                            <option value="apto">APTO</option>
                            <option value="no_apto">NO APTO</option>
                            <option value="apto_con_recomendacion">Apto con Recomendación</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Recomendaciones</label>
                        <textarea name="recomendaciones" class="form-control"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Guardar Certificado</button>
                </form>
            </div>
        </div>

        <h5>Historial</h5>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Emisión</th>
                    <th>Vencimiento</th>
                    <th>Aptitud</th>
                    <th>Médico</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cert" items="${listaCertificados}">
                    <tr>
                        <td>${cert.fechaEmision}</td>
                        <td>${cert.fechaVenc}</td>
                        <td>${cert.aptitud}</td>
                        <td>${cert.nombreMedico}</td>
                        <td>
                            <a href="certifManipulador?accion=descargar&id=${cert.idCertifManipulador}" target="_blank" class="btn btn-sm btn-success">Descargar PDF</a>
                            <a href="certifManipulador?accion=eliminar&id=${cert.idCertifManipulador}&idPersona=${persona.idPersona}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Seguro que deseas eliminar este certificado?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>