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
</head>
<body>
    <div class="container mt-4">
        <h2>Configuración</h2>
        <a href="dashboard" class="btn btn-secondary btn-sm mb-3">← Volver al Dashboard</a>

        <div class="row g-4">
            <div class="col-md-6">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Médicos Laborales</h5>
                        <p class="card-text">Agregar o ver médicos del sistema.</p>
                        <a href="medico" class="btn btn-primary">Gestionar Médicos</a>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card shadow-sm h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Usuarios</h5>
                        <p class="card-text">Agregar nuevos usuarios (admin, rrhh, etc.).</p>
                        <a href="usuario" class="btn btn-primary">Gestionar Usuarios</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>