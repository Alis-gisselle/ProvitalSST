<%-- 
    Document   : empresaForm
    Created on : 1 ago. 2026, 15:27:28
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Empresa Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h2>${empresa != null ? "Editar" : "Nueva"} Empresa Cliente</h2>

        <form action="empresa" method="post">
            <c:if test="${empresa != null}">
                <input type="hidden" name="idEmpresaCliente" value="${empresa.idEmpresaCliente}" />
            </c:if>
            <div class="mb-3">
                <label class="form-label">RUC</label>
                <input type="number" name="ruc" class="form-control" value="${empresa != null ? empresa.ruc : ''}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" value="${empresa != null ? empresa.nombre : ''}" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Dirección</label>
                <input type="text" name="direccion" class="form-control" value="${empresa != null ? empresa.direccion : ''}" required />
            </div>

            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="empresa?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>