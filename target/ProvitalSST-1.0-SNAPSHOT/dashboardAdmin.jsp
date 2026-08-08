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
        a { color:black; text-decoration: none; }
        a:hover { color: #333; text-decoration: underline; }
        .alertas-container {
            background: white;
            border-radius: 12px;
            padding: 18px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .alertas-titulo {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 15px;
        }

        .alertas-titulo h5 {
            margin: 0;
            font-weight: 600;
        }

        .alertas-titulo h5 i {
            color: var(--verde-provitall);
        }

        .ver-alertas {
            color: #3b70b8;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
        }

        .ver-alertas:hover {
            text-decoration: underline;
        }

        .alerta-card {
            border-radius: 12px;
            overflow: hidden;
            background: white;
            border: 1px solid;
            height: 100%;
        }

        .alerta-contenido {
            padding: 18px;
            display: flex;
            align-items: center;
            gap: 15px;
            min-height: 125px;
        }

        .alerta-icono {
            width: 48px;
            height: 48px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 22px;
            flex-shrink: 0;
        }

        .alerta-info h4 {
            margin: 0;
            font-size: 28px;
            font-weight: 700;
        }

        .alerta-info small {
            font-size: 13px;
            color: #555;
        }

        .alerta-detalles {
            border-top: 1px solid;
            padding: 10px;
            text-align: center;
            font-size: 13px;
            font-weight: 600;
        }

        .alerta-detalles a {
            text-decoration: none;
        }


        .alerta-roja {
            border-color: #f2b8bd;
        }

        .alerta-roja .alerta-icono {
            background-color: #fde2e4;
            color: #dc3545;
        }

        .alerta-roja .alerta-info h4,
        .alerta-roja .alerta-detalles a {
            color: #dc3545;
        }

        .alerta-roja .alerta-detalles {
            border-color: #f2b8bd;
        }

        .alerta-amarilla {
            border-color: #ead9a0;
        }

        .alerta-amarilla .alerta-icono {
            background-color: #fff3cd;
            color: #d9a400;
        }

        .alerta-amarilla .alerta-info h4,
        .alerta-amarilla .alerta-detalles a {
            color: #d9a400;
        }

        .alerta-amarilla .alerta-detalles {
            border-color: #ead9a0;
        }

        .alerta-azul {
            border-color: #b8d0ed;
        }

        .alerta-azul .alerta-icono {
            background-color: #e3efff;
            color: #1769aa;
        }

        .alerta-azul .alerta-info h4,
        .alerta-azul .alerta-detalles a {
            color: #1769aa;
        }

        .alerta-azul .alerta-detalles {
            border-color: #b8d0ed;
        }


        .alerta-verde {
            border-color: #b8ddc5;
        }

        .alerta-verde .alerta-icono {
            background-color: #e1f3e6;
            color: #198754;
        }

        .alerta-verde .alerta-info h4,
        .alerta-verde .alerta-detalles a {
            color: #198754;
        }

        .alerta-verde .alerta-detalles {
            border-color: #b8ddc5;
        }
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

    <div class="alertas-container mb-4">
        <div class="alertas-titulo">
            <h5>
                <i class="bi bi-lightning-charge-fill"></i>
                Alertas
            </h5>
        </div>

        <div class="row g-3">

        <div class="col-md-6 col-lg-3">
            <div class="alerta-card alerta-roja">

                <div class="alerta-contenido">
                    <div class="alerta-icono">
                        <i class="bi bi-exclamation-triangle-fill"></i>
                    </div>

                    <div class="alerta-info">
                        <h4>${countVencidos}</h4>
                        <small>Certificados médicos vencidos</small>
                    </div>
                </div>

                <div class="alerta-detalles">
                    <a href="alertas?tipo=vencidos">
                        Ver detalles
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>

            </div>
        </div>

        <!-- SIN FICHA MÉDICA -->
        <div class="col-md-6 col-lg-3">
            <div class="alerta-card alerta-amarilla">

                <div class="alerta-contenido">
                    <div class="alerta-icono">
                        <i class="bi bi-person-fill"></i>
                    </div>

                    <div class="alerta-info">
                        <h4>${countSinFicha}</h4> 
                        <small>Trabajadores sin ficha médica</small>
                    </div>
                </div>

                <div class="alerta-detalles">
                    <a href="alertas?tipo=sinFicha">
                        Ver detalles
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>

            </div>
        </div>

        <!-- ESTUDIOS PENDIENTES -->
        <div class="col-md-6 col-lg-3">
            <div class="alerta-card alerta-azul">

                <div class="alerta-contenido">
                    <div class="alerta-icono">
                        <i class="bi bi-file-earmark-medical-fill"></i>
                    </div>

                    <div class="alerta-info">
                        <h4>${countEstudios}</h4>
                        <small>Estudios pendientes de cargar</small>
                    </div>
                </div>

                <div class="alerta-detalles">
                    <a href="alertas?tipo=estudios">
                        Ver detalles
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>

            </div>
        </div>

        <!-- PRÓXIMOS A VENCER -->
        <div class="col-md-6 col-lg-3">
            <div class="alerta-card alerta-verde">

                <div class="alerta-contenido">
                    <div class="alerta-icono">
                        <i class="bi bi-check-circle-fill"></i>
                    </div>

                    <div class="alerta-info">
                        <h4>${countProximos}</h4>
                        <small>Certificados próximos a vencer</small>
                    </div>
                </div>

                <div class="alerta-detalles">
                    <a href="alertas?tipo=proximos">
                        Ver detalles
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>

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