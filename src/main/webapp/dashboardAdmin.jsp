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
        :root {
            --verde-principal: #075B42;
            --verde-claro: #E8F5EF;
            --verde-boton: #087F4F;
            --fondo: #F8FAFC;
            --texto: #172B3A; 
        }
        body { background-color: var(--fondo); color: var(--texto); }
        .navbar-provital { background-color: var(--verde-principal) !important; }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        .btn-outline-provital{color:var(--verde-principal); border-color: var(--verde-principal); }
        .btn-outline-provital:hover { background-color: var(--verde-claro); color: var(--verde-principal); }
        a.ver-detalles { color: inherit; font-weight: 600; text-decoration: none; font-size: 14px; }
        a.ver-detalles:hover { text-decoration: underline; }
        

        .alerta-card { border-radius: 12px; overflow: hidden; height: 100%; }
        .alerta-roja {background-color: #FDF8F8; border-color: #F9E6E8 !important;}
        .alerta-amarilla {background-color: #FFFDF8; border-color: #FDF7EA !important;}
        .alerta-azul {background-color: #F8FBFE; border-color: #E2ECF8 !important;}
        .alerta-verde {background-color: #F7FAF8; border-color: #E7F1EC !important;}
        .alerta-contenido { padding: 18px; display: flex; align-items: center; gap: 15px; }
        .alerta-icono { width: 48px; height: 48px; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 22px; flex-shrink: 0; }
        .alerta-info h4 { margin: 0; font-size: 28px; font-weight: 700; }
        .alerta-info small { font-size: 13px; color: #666; }
        .alerta-detalles { border-top: 1px solid #eee; padding: 10px 18px; }

        .alerta-roja .alerta-icono { background-color: #fde2e4; color: #dc3545; }
        .alerta-roja h4, .alerta-roja .ver-detalles { color: #dc3545; }
        .alerta-amarilla .alerta-icono { background-color: #fff3cd; color: #d9a400; }
        .alerta-amarilla h4, .alerta-amarilla .ver-detalles { color: #d9a400; }
        .alerta-azul .alerta-icono { background-color: #e3efff; color: #1769aa; }
        .alerta-azul h4, .alerta-azul .ver-detalles { color: #1769aa; }
        .alerta-verde .alerta-icono { background-color: var(--verde-claro); color: var(--verde-principal); }
        .alerta-verde h4, .alerta-verde .ver-detalles { color: var(--verde-principal); }

        .menu-card { border-radius: 12px; overflow: hidden; background: white; }
        .menu-card-header { background-color: var(--verde-principal); color: white; padding: 16px 20px; font-weight: 600; display: flex; align-items: center; gap: 10px; }
        .menu-item { display: flex; align-items: center; gap: 15px; padding: 16px 20px; text-decoration: none; color: var(--texto); border-bottom: 1px solid #f0f0f0; }
        .menu-item:last-child { border-bottom: none; }
        .menu-item:hover { background-color: var(--verde-claro); }
        .menu-item-icon { width: 42px; height: 42px; border-radius: 8px; background-color: var(--verde-claro); color: var(--verde-principal); display: flex; align-items: center; justify-content: center; font-size: 20px; flex-shrink: 0; }
        .menu-item-texto small { color: #777; }
        .menu-item-chevron { margin-left: auto; color: #aaa; }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark navbar-provital">
        <div class="container-fluid px-4">
            <span class="navbar-brand"><i class="bi bi-shield-fill-check"></i>PROVITAL SST</span>
            <span class="text-white small">${usuario.correo}</span>
        </div>
    </nav>

    <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-start mb-4">
            <div class="d-flex gap-3">
                <i class="bi bi-shield-check fs-2" style="color: var(--verde-principal);"></i>
                <div>
                    <h2 class="fw-bold mb-0">Panel de Administrador</h2>
                    <p class="text-muted mb-0">Bienvenido al sistema Provital SST. Desde aquí puedes gestionar todos los módulos.</p>
                </div>
            </div>
            <a href="logout" class="btn btn-outline-provital">
                <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
            </a>
        </div>

        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <h5 class="mb-3"><i class="bi bi-lightning-charge-fill" style="color: var(--verde-boton);"></i> Alertas</h5>

                <div class="row g-3">
                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-roja border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-exclamation-triangle-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countVencidos}</h4>
                                    <small>Certificados médicos vencidos</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="alertas?tipo=vencidos" class="ver-detalles">Ver detalles <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-amarilla border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-person-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countSinFicha}</h4>
                                    <small>Trabajadores sin ficha médica</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="alertas?tipo=sinFicha" class="ver-detalles">Ver detalles <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-azul border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-file-earmark-medical-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countEstudios}</h4>
                                    <small>Estudios pendientes de cargar</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="alertas?tipo=estudios" class="ver-detalles">Ver detalles <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-verde border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-check-circle-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countProximos}</h4>
                                    <small>Certificados próximos a vencer</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="alertas?tipo=proximos" class="ver-detalles">Ver detalles <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>

        <h5 class="mb-3">Menú</h5>
        <div class="row g-4 mb-4">
            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-building"></i> Empresas</div>
                    <a href="empresa" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-building"></i></div>
                        <div class="menu-item-texto">
                            <div>Ver / Gestionar Empresas</div>
                            <small>Consulta y administra las empresas registradas.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                    <a href="empresa?accion=nuevo" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-plus-square"></i></div>
                        <div class="menu-item-texto">
                            <div>Registrar Empresa</div>
                            <small>Registra una nueva empresa en el sistema.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>

            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-people"></i> Colaboradores</div>
                    <a href="colaborador" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-people"></i></div>
                        <div class="menu-item-texto">
                            <div>Ver / Gestionar Colaboradores</div>
                            <small>Consulta y administra los colaboradores registrados.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                    <a href="colaborador?accion=nuevo" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-person-plus"></i></div>
                        <div class="menu-item-texto">
                            <div>Registrar Colaborador</div>
                            <small>Registra un nuevo colaborador en el sistema.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>

            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-gear"></i> Configuración</div>
                    <a href="medico" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-person-badge"></i></div>
                        <div class="menu-item-texto">
                            <div>Médicos Laborales</div>
                            <small>Gestiona los médicos laborales del sistema.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                    <a href="usuario" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-shield-lock"></i></div>
                        <div class="menu-item-texto">
                            <div>Usuarios del Sistema</div>
                            <small>Administra los usuarios y roles del sistema.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>
        </div>

    </div>
</body>
</html>