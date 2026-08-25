<%-- 
    Document   : alertaLista
    Created on : 8 ago. 2026, 10:45:59
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Alertas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
    <style>
        :root {
            --verde-principal: #075B42;
            --verde-claro: #E8F5EF;
            --verde-boton: #087F4F;
            --fondo: #F8FAFC;
            --texto: #172B3A;
        }
        body { background-color: var(--fondo); color: var(--texto); }
        .breadcrumb-custom a { color: var(--verde-principal); text-decoration: none; }
        .icono-circulo {
            width: 64px; height: 64px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 28px; flex-shrink: 0;
        }
        table thead { background-color: var(--verde-principal); color: white; }
        .badge-med { background-color: #fde2e4; color: #dc3545; }
        .badge-manip { background-color: #f3e8fd; color: #8a3ffc; }
    </style>
</head>
<body>
    <div class="container mt-4">
        <nav class="breadcrumb-custom small mb-3">
            <a href="dashboard">Dashboard</a> <i class="bi bi-chevron-right small mx-1"></i>
            <span class="text-muted">Alertas</span>
        </nav>

        <div class="d-flex gap-3 mb-4">
            <div class="icono-circulo"><i class="bi bi-lightning-charge"></i></div>
            <div>
                <h2 class="fw-bold mb-0">Alertas</h2>
                <p class="text-muted mb-0">Detalle de las alertas del sistema.</p>
            </div>
        </div>

        <a href="dashboard" class="btn btn-outline-secondary mb-4"><i class="bi bi-arrow-left"></i> Volver al Dashboard</a>

        <c:if test="${listaCertificados != null}">
            <div class="card border-0 shadow-sm">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr><th>Trabajador</th><th>Tipo</th><th>Vence</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${listaCertificados}">
                            <tr>
                                <td>${a.nombrePersona} ${a.apellidoPersona}</td>
                                <td>
                                    <span class="badge rounded-pill ${a.tipoAlerta == 'Certificado Médico' ? 'badge-med' : 'badge-manip'}">
                                        ${a.tipoAlerta}
                                    </span>
                                </td>
                                <td>${a.fechaVenc}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${listaPersonas != null}">
            <div class="card border-0 shadow-sm">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr><th>Nombre</th><th>Apellido</th><th>CI</th><th>Categoría</th><th>Empresa</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${listaPersonas}">
                            <tr>
                                <td>${p.nombre}</td>
                                <td>${p.apellido}</td>
                                <td>${p.ci}</td>
                                <td>${p.categoria}</td>
                                <td>${p.nombreEmpresa != null ? p.nombreEmpresa : 'Independiente'}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</body>
</html>