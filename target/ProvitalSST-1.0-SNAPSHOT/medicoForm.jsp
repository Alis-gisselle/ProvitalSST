<%-- 
    Document   : medicoForm
    Created on : 1 ago. 2026, 16:44:24
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Médico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h2>${medico != null ? "Editar" : "Nuevo"} Médico Laboral</h2>

        <form action="medico" method="post">
            <c:if test="${medico != null}">
                <input type="hidden" name="idMedicoLaboral" value="${medico.idMedicoLaboral}" />
            </c:if>

            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" value="${medico.nombre}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Apellido</label>
                <input type="text" name="apellido" class="form-control" value="${medico.apellido}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Especialidad</label>
                <input type="text" name="especialidad" class="form-control" value="${medico.especialidad}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Matrícula</label>
                <input type="text" name="matricula" class="form-control" value="${medico.matricula}" required />
            </div>

            <c:if test="${medico == null}">
                <div class="mb-3">
                    <label class="form-label">Correo (para su login)</label>
                    <input type="email" name="correo" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="contrasenia" class="form-control" required />
                </div>
            </c:if>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="medico" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>