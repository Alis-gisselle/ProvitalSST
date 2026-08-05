<%-- 
    Document   : usuarioLista
    Created on : 1 ago. 2026, 16:51:19
    Author     : alis
<th>Acciones</th>
...
<td>
    <a href="usuario?accion=editar&id=${u.idUsuario}" class="btn btn-sm btn-warning">Editar</a>
    <a href="usuario?accion=eliminar&id=${u.idUsuario}"
       class="btn btn-sm btn-danger"
       onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">Eliminar</a>
</td>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Usuarios del Sistema</h2>
        <a href="configuracion" class="btn btn-secondary btn-sm mb-3">← Volver a Configuración</a>

        <a href="usuario?accion=nuevo" class="btn btn-primary mb-3">+ Nuevo Usuario</a>

        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Correo</th>
                    <th>Rol</th>
                    <th>Empresa (si RRHH)</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${listaUsuarios}">
                    <tr>
                        <td>${u.correo}</td>
                        <td>${u.rol}</td>
                        <td>${u.idEmpresaCliente != null ? u.idEmpresaCliente : '-'}</td>
                        <td>
                            <a href="usuario?accion=editar&id=${u.idUsuario}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="usuario?accion=eliminar&id=${u.idUsuario}" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>