<%-- 
    Document   : colaboradorForm
    Created on : 1 ago. 2026, 16:26:41
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Colaborador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h2>${colaborador != null ? "Editar" : "Nuevo"} Colaborador</h2>

        <form action="colaborador" method="post">
            <c:if test="${colaborador != null}">
                <input type="hidden" name="idPersona" value="${colaborador.idPersona}" />
            </c:if>

            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" value="${colaborador.nombre}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Apellido</label>
                <input type="text" name="apellido" class="form-control" value="${colaborador.apellido}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">CI</label>
                <input type="number" name="ci" class="form-control" value="${colaborador.ci}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Fecha de Nacimiento</label>
                <input type="date" name="fechaNacimiento" class="form-control" value="${colaborador.fechaNacimiento}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Categoría</label>
                <select name="categoria" class="form-select" required>
                    <option value="">-- Seleccione --</option>
                    <option value="admisional" ${colaborador.categoria == 'admisional' ? 'selected' : ''}>Admisional</option>
                    <option value="manipulador" ${colaborador.categoria == 'manipulador' ? 'selected' : ''}>Manipulador de Alimentos</option>
                </select>
            </div>

            <c:if test="${colaborador == null}">
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
            <a href="colaborador" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
