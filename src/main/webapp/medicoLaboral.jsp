<%-- 
    Document   : medicoLaboral
    Created on : 16 ago. 2026, 3:22:30 p. m.
    Author     : Vivobook / PROVITAL SST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Médico Ocupacional - Provital SST</title>
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
        .navbar-provital { background-color: var(--verde-principal) !important; }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        .btn-outline-provital { color: var(--verde-principal); border-color: var(--verde-principal); }
        .btn-outline-provital:hover { background-color: var(--verde-claro); color: var(--verde-principal); }
        a.ver-detalles { color: inherit; font-weight: 600; text-decoration: none; font-size: 14px; }
        a.ver-detalles:hover { text-decoration: underline; }

        .alerta-card { border-radius: 12px; overflow: hidden; height: 100%; }
        .alerta-roja { background-color: #FDF8F8; border-color: #F9E6E8 !important; }
        .alerta-amarilla { background-color: #FFFDF8; border-color: #FDF7EA !important; }
        .alerta-azul { background-color: #F8FBFE; border-color: #E2ECF8 !important; }
        .alerta-verde { background-color: #F7FAF8; border-color: #E7F1EC !important; }
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
        .card-custom { border-radius: 12px; border: none; }
    </style>
</head>
<body class="bg-light">

    <!-- Navbar superior con colores de Provital SST -->
    <nav class="navbar navbar-dark navbar-provital">
        <div class="container-fluid px-4">
            <span class="navbar-brand"><i class="bi bi-heart-pulse-fill"></i> PROVITAL SST</span>
            <span class="text-white small">Dr(a). ${usuario.correo}</span>
        </div>
    </nav>

    <div class="container mt-4">

        <!-- Encabezado con bienvenida al médico -->
        <div class="d-flex justify-content-between align-items-start mb-4">
            <div class="d-flex gap-3">
                <i class="bi bi-person-badge-fill fs-2" style="color: var(--verde-principal);"></i>
                <div>
                    <h2 class="fw-bold mb-0">Portal del Médico Ocupacional</h2>
                    <p class="text-muted mb-0">Gestión de fichas médicas, evaluación de estudios y certificación para manipuladores de alimentos.</p>
                </div>
            </div>
            <a href="logout" class="btn btn-outline-provital">
                <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
            </a>
        </div>

        <!-- Sección de Alertas / Tarjetas KPI idénticas a DashboardAdmin -->
        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <h5 class="mb-3"><i class="bi bi-lightning-charge-fill" style="color: var(--verde-boton);"></i> Estado de Atenciones</h5>

                <div class="row g-3">
                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-azul border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-person-lines-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countPorAtender != null ? countPorAtender : 0}</h4>
                                    <small>Pacientes por atender</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="medico?filtro=pendientes" class="ver-detalles">Ver lista <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-amarilla border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-file-earmark-medical-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countEstudiosPendientes != null ? countEstudiosPendientes : 0}</h4>
                                    <small>Estudios por revisar</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="estudios?filtro=por_revisar" class="ver-detalles">Ver estudios <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-roja border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-exclamation-triangle-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countObservados != null ? countObservados : 0}</h4>
                                    <small>Casos observados / No Apto</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="medico?filtro=observados" class="ver-detalles">Ver detalles <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-verde border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-check-circle-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countCertificadosHoy != null ? countCertificadosHoy : 0}</h4>
                                    <small>Certificados dictaminados hoy</small>
                                </div>
                            </div>
                            <div class="alerta-detalles">
                                <a href="medico?filtro=hoy" class="ver-detalles">Ver historial <i class="bi bi-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Módulo de Accesos Rápidos (Estructura menu-card idéntica) -->
        <h5 class="mb-3">Menú Médico</h5>
        <div class="row g-4 mb-4">
            <!-- Columna 1: Fichas Médicas -->
            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-journal-medical"></i> Fichas Médicas</div>
                    <a href="colaboradorLista.jsp" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-plus-square"></i></div>
                        <div class="menu-item-texto">
                            <div>Nueva Ficha Médica</div>
                            <small>Inicia una nueva anamnesis y examen físico.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                    <a href="fichaMedica" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-card-checklist"></i></div>
                        <div class="menu-item-texto">
                            <div>Historial de Fichas</div>
                            <small>Consulta expedientes médicos cargados.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>

            <!-- Columna 2: Estudios y Laboratorios -->
            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-vial"></i> Estudios de Laboratorio</div>
                    <a href="estudioLista.jsp" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-search"></i></div>
                        <div class="menu-item-texto">
                            <div>Revisar Estudios</div>
                            <small>Laboratorios, imágenes, es</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>

            <!-- Columna 3: Certificados de Aptitud -->
            <div class="col-md-4">
                <div class="menu-card shadow-sm">
                    <div class="menu-card-header"><i class="bi bi-award"></i> Aptitud y Certificados</div>
                    <a href="certificacion" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-file-earmark-check"></i></div>
                        <div class="menu-item-texto">
                            <div>Emitir Certificado</div>
                            <small>Dictamina si el trabajador es APTO.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                    <a href="certificacion?accion=historial" class="menu-item">
                        <div class="menu-item-icon"><i class="bi bi-clock-history"></i></div>
                        <div class="menu-item-texto">
                            <div>Certificados Emitidos</div>
                            <small>Consulta y reimprime certificados emitidos.</small>
                        </div>
                        <i class="bi bi-chevron-right menu-item-chevron"></i>
                    </a>
                </div>
            </div>
        </div>


    </div>

    <!-- Script para filtro directo en tabla -->
    <script>
        document.getElementById('buscador').addEventListener('keyup', function() {
            let valor = this.value.toLowerCase();
            let filas = document.querySelectorAll('#tablaPacientes tbody tr');
            
            filas.forEach(fila => {
                let texto = fila.textContent.toLowerCase();
                fila.style.display = texto.includes(valor) ? '' : 'none';
            });
        });
    </script>
</body>
</html>