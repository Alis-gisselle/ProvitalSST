<%-- 
    Document   : DashboardAdmin
    Created on : 1 ago. 2026, 14:44:06
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Admin - Provitall SST</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <span class="navbar-brand">Provitall SST - Admin</span>
            <a href="logout" class="btn btn-outline-light btn-sm">Cerrar Sesión</a>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row mb-4">
            <div class="col-12">
                <a href="alerta" class="btn btn-warning w-100 py-3">🔔 Alertas</a>
            </div>
        </div>

        <div class="row g-4">
            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Empresas</h5>
                        <p class="card-text">Ver empresas y sus trabajadores.</p>
                        <a href="empresa" class="btn btn-primary">Ver Empresas</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Colaboradores</h5>
                        <p class="card-text">Ver y gestionar colaboradores independientes.</p>
                        <a href="colaborador" class="btn btn-primary">Ver Colaboradores</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Configuración</h5>
                        <p class="card-text">Agregar médicos y usuarios del sistema.</p>
                        <a href="configuracion" class="btn btn-secondary">Configuración</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>