<%-- 
    Document   : fichaLista
    Created on : 2 ago. 2026, 12:39:48
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fichas - ${persona.nombre} ${persona.apellido}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        :root {
            --verde-principal: #075B42;
            --verde-claro: #E8F5EF;
            --verde-boton: #087F4F;
            --fondo: #F8FAFC;
            --texto: #172B3A;
        }
        body { background-color: var(--fondo); color: var(--texto); margin: 0; }
        .topbar-simple {
            background: var(--verde-principal); color: white;
            display: flex; justify-content: space-between; align-items: center;
            padding: 14px 28px;
        }
        .topbar-simple .marca { display: flex; align-items: center; gap: 8px; font-weight: 700; font-size: 18px; }
        .topbar-simple .usuario { display: flex; align-items: center; gap: 8px; opacity: 0.9; }
        .icono-circulo {
            width: 40px; height: 40px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 20px; flex-shrink: 0;
        }
        .btn-volver { border-color: var(--verde-principal); color: var(--verde-principal); }
        .btn-volver:hover { background-color: var(--verde-claro); color: var(--verde-principal); }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        table thead { background-color: var(--verde-principal); color: white; }
    </style>
</head>
<body>
    <div class="topbar-simple">
        <div class="marca"><i class="bi bi-shield-fill-check"></i> PROVITAL SST</div>
        <div class="usuario"><i class="bi bi-person-circle"></i> ${sessionScope.usuarioLogueado.correo}</div>
    </div>

    <div class="container mt-4 mb-4">
        <a href="persona?id=${persona.idPersona}" class="btn btn-outline-secondary btn-volver btn-sm mb-3">
            <i class="bi bi-arrow-left"></i> Volver
        </a>

        <div class="d-flex align-items-center gap-2 mb-4">
            <div class="icono-circulo"><i class="bi bi-clipboard2-pulse"></i></div>
            <h3 class="fw-bold mb-0">Fichas Médicas - ${persona.nombre} ${persona.apellido}</h3>
        </div>

        <c:if test="${!soloLectura}">
            <a href="ficha?accion=nueva&idPersona=${persona.idPersona}" class="btn btn-provital mb-4">
                <i class="bi bi-plus-lg"></i> Nueva Ficha
            </a>
        </c:if>

        <div class="card border-0 shadow-sm">
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th><i class="bi bi-hash"></i> N° Ficha</th>
                        <th><i class="bi bi-calendar3"></i> Fecha</th>
                        <th><i class="bi bi-tag"></i> Tipo</th>
                        <th><i class="bi bi-lightning-fill"></i> Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${listaFichas}">
                        <tr>
                            <td>${f.numeroFicha}</td>
                            <td>${f.fecha}</td>
                            <td>${f.tipoEvaluacion}</td>
                            <td>
                                <a href="ficha?accion=descargar&idFicha=${f.idFicha}" target="_blank" class="btn btn-sm btn-provital">
                                    <i class="bi bi-file-earmark-arrow-down"></i> Descargar PDF
                                </a>
                                <c:if test="${!soloLectura}">
                                    <a href="ficha?accion=eliminar&idFicha=${f.idFicha}&idPersona=${persona.idPersona}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Seguro que deseas eliminar esta ficha completa? Esta acción no se puede deshacer.');">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>