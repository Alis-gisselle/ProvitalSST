<%-- 
    Document   : configuracion
    Created on : 1 ago. 2026, 16:35:21
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Configuración</title>
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
        .menu-card { border-radius: 12px; overflow: hidden; background: white; text-decoration: none; color: var(--texto); display: block; transition: transform 0.15s; }
        .menu-card:hover { transform: translateY(-3px); color: var(--texto); }
        .menu-card-icon {
            width: 56px; height: 56px; border-radius: 12px;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 26px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <nav class="breadcrumb-custom small mb-3">
            <a href="dashboard">Dashboard</a> <i class="bi bi-chevron-right small mx-1"></i>
            <span class="text-muted">Configuración</span>
        </nav>

        <div class="d-flex gap-3 mb-4">
            <div class="icono-circulo"><i class="bi bi-gear"></i></div>
            <div>
                <h2 class="fw-bold mb-0">Configuración</h2>
                <p class="text-muted mb-0">Administra médicos laborales y usuarios del sistema.</p>
            </div>
        </div>

        <a href="dashboard" class="btn btn-outline-secondary mb-4"><i class="bi bi-arrow-left"></i> Volver al Dashboard</a>

        <div class="row g-4">
            <div class="col-md-6">
                <a href="medico" class="menu-card shadow-sm p-4 d-flex align-items-center gap-3">
                    <div class="menu-card-icon"><i class="bi bi-person-badge"></i></div>
                    <div>
                        <h5 class="mb-1">Médicos Laborales</h5>
                        <p class="text-muted mb-0 small">Gestiona los médicos laborales del sistema.</p>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted"></i>
                </a>
            </div>

            <div class="col-md-6">
                <a href="usuario" class="menu-card shadow-sm p-4 d-flex align-items-center gap-3">
                    <div class="menu-card-icon"><i class="bi bi-shield-lock"></i></div>
                    <div>
                        <h5 class="mb-1">Usuarios del Sistema</h5>
                        <p class="text-muted mb-0 small">Administra los usuarios y roles del sistema.</p>
                    </div>
                    <i class="bi bi-chevron-right ms-auto text-muted"></i>
                </a>
            </div>
        </div>
    </div>
</body>
</html>