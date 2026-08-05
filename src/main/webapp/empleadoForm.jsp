<%-- 
    Document   : empleadoForm
    Created on : 1 ago. 2026, 15:44:26
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h2>${empleado != null ? "Editar" : "Nuevo"} Empleado</h2>

        <form action="empleado" method="post">
            <c:if test="${empleado != null}">
                <input type="hidden" name="idPersona" value="${empleado.idPersona}" />
            </c:if>

            <input type="hidden" name="idEmpresaCliente"
                   value="${empleado != null ? empleado.idEmpresaCliente : idEmpresaNuevo}" />

            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" value="${empleado.nombre}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Apellido</label>
                <input type="text" name="apellido" class="form-control" value="${empleado.apellido}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">CI</label>
                <input type="number" name="ci" class="form-control" value="${empleado.ci}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Fecha de Nacimiento</label>
                <input type="date" name="fechaNacimiento" class="form-control" value="${empleado.fechaNacimiento}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Cargo</label>
                <input type="text" name="cargo" class="form-control" value="${empleado.cargo}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Categoría</label>
                <select name="categoria" class="form-select" required>
                    <option value="">-- Seleccione --</option>
                    <option value="admisional" ${empleado.categoria == 'admisional' ? 'selected' : ''}>Admisional</option>
                    <option value="manipulador" ${empleado.categoria == 'manipulador' ? 'selected' : ''}>Manipulador de Alimentos</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="empresa?accion=empleados&id=${empleado != null ? empleado.idEmpresaCliente : idEmpresaNuevo}" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>