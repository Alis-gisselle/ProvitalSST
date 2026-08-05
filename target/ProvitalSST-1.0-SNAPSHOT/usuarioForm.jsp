<%-- 
    Document   : usuarioForm
    Created on : 1 ago. 2026, 16:52:28
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${usuario != null ? "Editar" : "Nuevo"} Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h2>${usuario != null ? "Editar" : "Nuevo"} Usuario</h2>

        <form action="usuario" method="post">
            <c:if test="${usuario != null}">
                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
            </c:if>

            <div class="mb-3">
                <label class="form-label">Correo</label>
                <input type="email" name="correo" class="form-control" value="${usuario.correo}" required />
            </div>

            <c:if test="${usuario == null}">
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="contrasenia" class="form-control" required />
                </div>
            </c:if>

            <div class="mb-3">
                <label class="form-label">Rol</label>
                <select name="rol" id="rol" class="form-select" required onchange="mostrarEmpresa()">
                    <option value="">-- Seleccione --</option>
                    <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="rrhh" ${usuario.rol == 'rrhh' ? 'selected' : ''}>RRHH</option>
                </select>
            </div>

            <div class="mb-3" id="divEmpresa" style="${usuario != null && usuario.rol == 'rrhh' ? 'display:block;' : 'display:none;'}">
                <label class="form-label">Empresa (solo para RRHH)</label>
                <select name="idEmpresaCliente" class="form-select">
                    <option value="">-- Seleccione --</option>
                    <c:forEach var="e" items="${listaEmpresas}">
                        <option value="${e.idEmpresaCliente}" ${usuario != null && usuario.idEmpresaCliente == e.idEmpresaCliente ? 'selected' : ''}>${e.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="usuario" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script>
        function mostrarEmpresa() {
            const rol = document.getElementById('rol').value;
            document.getElementById('divEmpresa').style.display = (rol === 'rrhh') ? 'block' : 'none';
        }
    </script>
</body>
</html>