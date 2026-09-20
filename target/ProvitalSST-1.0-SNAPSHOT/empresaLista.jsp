<%-- 
    Document   : empresaLista
    Created on : 1 ago. 2026, 15:29:44
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("paginaActiva", "empresas"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Empresas Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
    <style>
        :root { --verde-provitall: #6DA343; }
        body { background-color: #f8f9fa; }
        .navbar-provitall { background-color: #075B42 !important; }
        .btn-provitall { background-color: var(--verde-provitall); border-color: var(--verde-provitall); color: white; }
        .btn-provitall:hover { background-color: #5c8a38; border-color: #5c8a38; color: white; }
        .stat-card { border-radius: 12px; }
        .stat-icon {
            width: 56px; height: 56px; border-radius: 50%;
            background-color: #e1f3e6; color: var(--verde-provitall);
            display: flex; align-items: center; justify-content: center; font-size: 26px;
        }
        .table-dark{
            --bs-table-bg: #075B42;
            --bs-table-color: white;
        }
        table thead { background-color: #294145; color: white; }
        a { color: var(--verde-provitall); }
    </style>
</head>
<script>
    function filtrarTabla() {
        const filtro = document.getElementById('buscador').value.toLowerCase();
        const filas = document.querySelectorAll('#tablaEmpresas tbody tr');
        filas.forEach(fila => {
            fila.style.display = fila.textContent.toLowerCase().includes(filtro) ? '' : 'none';
        });
    }
</script>
<body>
    <div class="layout-admin">
        <jsp:include page="sidebar.jsp" />
        <div class="contenido-admin">
            <jsp:include page="topbar.jsp" />
                <div class="container mt-4">
                    <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>
                    <div class="d-flex justify-content-between align-items-start mb-4">
                        <div>
                            <h2 class="fw-bold mb-0">Empresas Cliente</h2>
                            <p class="text-muted">Gestiona el listado de empresas registradas en el sistema.</p>
                        </div>
                        <a href="empresa?accion=nuevo" class="btn btn-provitall">
                            <i class="bi bi-plus-lg"></i> Nueva Empresa
                        </a>
                    </div>

                    <div class="card stat-card shadow-sm mb-4" style="max-width: 320px;">
                        <div class="card-body d-flex align-items-center gap-3">
                            <div class="stat-icon"><i class="bi bi-building"></i></div>
                            <div>
                                <div class="text-muted small">Total de Empresas</div>
                                <div class="fs-3 fw-bold text-success">${listaEmpresas.size()}</div>
                                <div class="text-muted small">empresas registradas</div>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <input type="text" id="buscador" class="form-control" placeholder="Buscar por nombre, RUC o dirección..." onkeyup="filtrarTabla()">
                    </div>
                    <div class="card shadow-sm">
                        <table class="table table-hover mb-0" id="tablaEmpresas">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>RUC</th>
                                    <th>Nombre</th>
                                    <th>Ciudad</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="emp" items="${listaEmpresas}">
                                    <tr>
                                        <td><span class="badge bg-light text-dark border">${emp.idEmpresaCliente}</span></td>
                                        <td>${emp.ruc}</td>
                                        <td><i class="bi bi-building text-muted me-1"</i>${emp.nombre}</td>
                                        <td><i class="bi bi-geo-alt text-muted me-1"</i>${emp.direccion}</td>
                                        <td>
                                            <a href="empresa?accion=empleados&id=${emp.idEmpresaCliente}" class="btn btn-sm btn-outline-primary"><i class="bi bi-people"></i>Empleados</a>
                                            <a href="empresa?accion=editar&id=${emp.idEmpresaCliente}" class="btn btn-sm btn-outline-warning"><i class="bi bi-pencil"></i>Editar</a>
                                            <a href="empresa?accion=eliminar&id=${emp.idEmpresaCliente}"
                                            class="btn btn-sm btn-outline-danger"
                                            onclick="return confirm('¿Seguro que deseas eliminar esta empresa?');">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
    </body>
</html>