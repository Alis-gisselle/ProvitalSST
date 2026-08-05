<%-- 
    Document   : DashboardAdmin
    Created on : 1 ago. 2026, 14:44:06
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Admin - Provital SST</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        :root { --verde-provitall: #6DA343; }
        .navbar-provitall { background-color: #294145 !important; }
        .btn-provitall { background-color: var(--verde-provitall); border-color: var(--verde-provitall); color: white; }
        .btn-provitall:hover { background-color: #5c8a38; border-color: #5c8a38; color: white; }
        h3, h5, h6 i { color: black; }
        .card-header-provitall { background-color: var(--verde-provitall); color: white; }
        a { color:blue; text-decoration: none; }
        a:hover { color: #333; text-decoration: underline; }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark navbar-provitall">
        <div class="container">
            <span class="navbar-brand">Provital SST - Admin</span>
            <a href="logout" class="btn btn-outline-light btn-sm">Cerrar Sesión</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3 class="mb-4"><i class="bi bi-shield-check"></i> Panel de Administrador</h3>

        <h5>Alertas</h5>
        <div class="card mb-4">
            <div class="card-body">
                <div class="row text-center">
                    <div class="col">
                        <i class="bi bi-exclamation-triangle-fill text-danger fs-2"></i>
                        <h4 class="mt-2">5</h4>
                        <small>Certificados médicos vencidos</small>
                    </div>
                    <div class="col">
                        <i class="bi bi-person-badge fs-2" style="color: #FFD700;"></i>
                        <h4 class="mt-2">12</h4>
                        <small>Trabajadores sin ficha médica</small>
                    </div>
                    <div class="col">
                        <i class="bi bi-file-earmark-medical fs-2" style="color: #FFD700;"></i>
                        <h4 class="mt-2">8</h4>
                        <small>Estudios pendientes de cargar</small>
                    </div>
                    <div class="col">
                        <i class="bi bi-check-circle fs-2" style="color: #FFD700;"></i>
                        <h4 class="mt-2">20</h4>
                        <small>Certificados próximos a vencer</small>
                    </div>
                </div>
            </div>
        </div>

        <h5>Menú</h5>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-header card-header-provitall">
                        <i class="bi bi-building"></i> Empresas
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled">
                            <li class="mb-2"><a href="empresa">Ver / Gestionar Empresas</a></li>
                            <li class="mb-2"><a href="empresa?accion=nuevo">Registrar Empresa</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-header card-header-provitall">
                        <i class="bi bi-person-workspace"></i> Colaboradores
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled">
                            <li class="mb-2"><a href="colaborador">Ver / Gestionar Colaboradores</a></li>
                            <li class="mb-2"><a href="colaborador?accion=nuevo">Registrar Colaborador</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-header card-header-provitall">
                        <i class="bi bi-gear"></i> Configuración
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled">
                            <li class="mb-2"><a href="medico">Médicos Laborales</a></li>
                            <li class="mb-2"><a href="usuario">Usuarios del Sistema</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>