<%-- 
    Document   : panelMedico
    Created on : 15 ago. 2026
    Author     : Vivobook
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="manifest" href="manifest.json">
    <meta name="theme-color" content="#0056b3">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Médico - Provital SST</title>
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

        .card-custom { border-radius: 12px; border: none; }
        .tr-clickable { cursor: pointer; transition: background-color 0.2s; }
        .tr-clickable:hover { background-color: var(--verde-claro) !important; }

        .avatar-iniciales {
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: var(--verde-claro);
            color: var(--verde-principal);
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            font-size: 14px;
        }
        .badge-manipulador { background-color: #e3efff; color: #1769aa; }
        .badge-admisional { background-color: #f7faf8; color: var(--verde-principal); border: 1px solid var(--verde-principal); }
    </style>
</head>
<body class="bg-light">

    <!-- Navegación superior -->
    <nav class="navbar navbar-dark navbar-provital mb-4">
        <div class="container-fluid px-4">
            <span class="navbar-brand"><i class="bi bi-shield-fill-check"></i> PROVITAL SST</span>
            <span class="text-white small"><c:out value="${usuario.correo}" /></span>
        </div>
    </nav>

    <div class="container">
        <!-- Encabezado del Portal -->
        <div class="d-flex justify-content-between align-items-start mb-4">
            <div class="d-flex gap-3">
                <i class="bi bi-heart-pulse-fill fs-2" style="color: var(--verde-principal);"></i>
                <div>
                    <h2 class="fw-bold mb-0">Médico Laboral</h2>
                    <p class="text-muted mb-0">Listado general de pacientes para atención clínica y registro de documentos.</p>
                </div>
            </div>
            <a href="logout" class="btn btn-outline-provital">
                <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
            </a>
        </div>

        <!-- Tarjetas de Alertas -->
        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <h5 class="mb-3"><i class="bi bi-lightning-charge-fill" style="color: var(--verde-boton);"></i> Alertas de Pacientes</h5>

                <div class="row g-3">
                    <div class="col-md-6 col-lg-3">
                        <div class="alerta-card alerta-roja border">
                            <div class="alerta-contenido">
                                <div class="alerta-icono"><i class="bi bi-exclamation-triangle-fill"></i></div>
                                <div class="alerta-info">
                                    <h4>${countVencidos != null ? countVencidos : 0}</h4>
                                    <small>Certificados vencidos</small>
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
                                    <h4>${countSinFicha != null ? countSinFicha : 0}</h4>
                                    <small>Pacientes sin ficha médica</small>
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
                                    <h4>${countEstudios != null ? countEstudios : 0}</h4>
                                    <small>Estudios pendientes</small>
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
                                    <h4>${countProximos != null ? countProximos : 0}</h4>
                                    <small>Próximos a vencer</small>
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

        <!-- Buscador y Filtro por Categoría -->
        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <form action="panelMed2" method="get" class="row g-2 align-items-center">
                    <div class="col">
                        <input type="text" name="buscar" class="form-control" placeholder="Buscar paciente por nombre o CI..." value="<c:out value='${buscar}'/>" />
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-provital">Buscar</button>
                    </div>
                    <div class="col-auto">
                        <select id="filtroCategoria" class="form-select" onchange="renderizarTabla()">
                            <option value="">Filtrar por categoría</option>
                            <option value="admisional">Admisional</option>
                            <option value="manipulador">Manipulador</option>
                        </select>
                    </div>
                </form>
            </div>
        </div>

        <!-- Tabla de Pacientes -->
        <div class="card border-0 shadow-sm">
            <table id="tablaPacientes" class="table table-hover align-middle mb-0">
                <thead style="background-color: var(--verde-claro);">
                    <tr>
                        <th></th>
                        <th>CI</th>
                        <th>Nombre Completo</th>
                        <th>Categoría</th>
                        <th>Empresa</th>
                        <th class="text-center">Acciones Clínicas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${listaPacientesGlobal}">
                        <tr data-categoria="${p.categoria}">
                            <td>
                                <div class="avatar-iniciales">${p.nombre.substring(0,1)}${p.apellido.substring(0,1)}</div>
                            </td>
                            <td>${p.ci}</td>
                            <td class="fw-bold">${p.nombre} ${p.apellido}</td>
                            <td>
                                <span class="badge rounded-pill ${p.categoria == 'manipulador' ? 'badge-manipulador' : 'badge-admisional'}">
                                    ${p.categoria == 'manipulador' ? 'Manipulador' : (p.categoria != null ? 'Admisional' : 'N/A')}
                                </span>
                            </td>
                            <td>${p.nombreEmpresa != null ? p.nombreEmpresa : 'Sin Empresa'}</td>
                            <td class="text-center">
                                <div class="btn-group" role="group">
                                    <a href="persona?id=${p.idPersona}" class="btn btn-sm btn-outline-primary d-inline-flex align-items-center gap-1">
                                        <i class="bi bi-eye"></i> Ver Detalle
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty listaPacientesGlobal}">
                        <tr>
                            <td colspan="6" class="text-center text-muted py-4">
                                No se encontraron pacientes registrados.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <!-- Paginación -->
        <div class="d-flex justify-content-between align-items-center mt-3 pb-4">
            <span id="infoPaginacion" class="text-muted small"></span>
            <div class="d-flex align-items-center gap-2">
                <button class="btn btn-sm btn-outline-secondary" onclick="cambiarPagina(-1)">←</button>
                <span id="numeroPagina" class="fw-bold"></span>
                <button class="btn btn-sm btn-outline-secondary" onclick="cambiarPagina(1)">→</button>
                <select id="filasPorPagina" class="form-select form-select-sm" style="width: auto;" onchange="paginaActual = 1; renderizarTabla();">
                    <option value="10">10 por página</option>
                    <option value="25">25 por página</option>
                    <option value="50">50 por página</option>
                </select>
            </div>
        </div>
    </div>

    <script>
        let paginaActual = 1;

        function cambiarPagina(direccion) {
            paginaActual += direccion;
            renderizarTabla();
        }

        function renderizarTabla() {
            const categoriaSelect = document.getElementById('filtroCategoria');
            const categoria = categoriaSelect ? categoriaSelect.value.toLowerCase().trim() : '';
            const filasPorPaginaSelect = document.getElementById('filasPorPagina');
            const porPagina = filasPorPaginaSelect ? parseInt(filasPorPaginaSelect.value) || 10 : 10;

            const todasLasFilas = Array.from(document.querySelectorAll('#tablaPacientes tbody tr'));

            const filasCoincidentes = todasLasFilas.filter(fila => {
                const catFila = (fila.getAttribute('data-categoria') || '').toLowerCase().trim();
                return categoria === '' || catFila.includes(categoria);
            });

            const totalPaginas = Math.max(1, Math.ceil(filasCoincidentes.length / porPagina));
            if (paginaActual > totalPaginas) paginaActual = totalPaginas;
            if (paginaActual < 1) paginaActual = 1;

            todasLasFilas.forEach(fila => fila.style.display = 'none');

            const inicio = (paginaActual - 1) * porPagina;
            filasCoincidentes.slice(inicio, inicio + porPagina).forEach(fila => {
                fila.style.display = '';
            });

            const elemNumeroPagina = document.getElementById('numeroPagina');
            const elemInfoPaginacion = document.getElementById('infoPaginacion');

            document.getElementById('numeroPagina').textContent = paginaActual + ' / ' + totalPaginas;
            document.getElementById('infoPaginacion').textContent =
                'Mostrando ' + (filasCoincidentes.length === 0 ? 0 : inicio + 1) + ' a ' +
                Math.min(inicio + porPagina, filasCoincidentes.length) + ' de ' + filasCoincidentes.length + ' pacientes';
            }
        

        document.addEventListener('DOMContentLoaded', function() {
            renderizarTabla();
        });
    </script>
    <script>
        if ('serviceWorker' in navigator) {
            window.addEventListener('load', () => {
                navigator.serviceWorker.register('sw.js')
                    .then(reg => console.log('PWA lista, scope:', reg.scope))
                    .catch(err => console.error('Error al registrar PWA:', err));
            });
        }
    </script>
</body>
</html>