<%-- 
    Document   : medicoLista
    Created on : 1 ago. 2026, 16:43:36
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Médicos Laborales</title>
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
        body { background-color: var(--fondo); color: var(--texto); }
        .breadcrumb-custom a { color: var(--verde-principal); text-decoration: none; }
        .icono-circulo {
            width: 64px; height: 64px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 28px; flex-shrink: 0;
        }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        table thead { background-color: var(--verde-principal); color: white; }
        .avatar-iniciales {
            width: 38px; height: 38px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-weight: 600; font-size: 13px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <nav class="breadcrumb-custom small mb-3">
            <a href="dashboard">Dashboard</a> <i class="bi bi-chevron-right small mx-1"></i>
            <a href="configuracion">Configuración</a> <i class="bi bi-chevron-right small mx-1"></i>
            <span class="text-muted">Médicos Laborales</span>
        </nav>

        <div class="d-flex gap-3 mb-4">
            <div class="icono-circulo"><i class="bi bi-person-badge"></i></div>
            <div>
                <h2 class="fw-bold mb-0">Médicos Laborales</h2>
                <p class="text-muted mb-0">Gestiona los médicos laborales del sistema.</p>
            </div>
        </div>

        <div class="d-flex gap-2 mb-4">
            <a href="configuracion" class="btn btn-outline-secondary"><i class="bi bi-arrow-left"></i> Volver a Configuración</a>
            <a href="medico?accion=nuevo" class="btn btn-provital">
                <i class="bi bi-plus-lg"></i> Nuevo Médico
            </a>
        </div>

        <div class="card border-0 shadow-sm">
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th></th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Especialidad</th>
                        <th>Matrícula</th>
                        <th>Correo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="med" items="${listaMedicos}">
                        <tr>
                            <td>
                                <div class="avatar-iniciales">${med.nombre.substring(0,1)}${med.apellido.substring(0,1)}</div>
                            </td>
                            <td>${med.nombre}</td>
                            <td>${med.apellido}</td>
                            <td>${med.especialidad}</td>
                            <td>${med.matricula}</td>
                            <td>${med.correo}</td>
                            <td>
                                <a href="medico?accion=editar&id=${med.idMedicoLaboral}" class="btn btn-sm btn-outline-warning"><i class="bi bi-pencil"></i> Editar</a>
                                <a href="medico?accion=eliminar&id=${med.idMedicoLaboral}"
                                   class="btn btn-sm btn-outline-danger"
                                   onclick="return confirm('¿Seguro que deseas eliminar este médico?');">
                                    <i class="bi bi-trash"></i> Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>