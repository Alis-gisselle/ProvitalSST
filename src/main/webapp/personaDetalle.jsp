<%-- 
    Document   : personaDetalle
    Created on : 1 ago. 2026, 19:42:59
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="manifest" href="manifest.json">
    <meta name="theme-color" content="#0056b3">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${persona.nombre} ${persona.apellido}</title>
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
        .avatar-grande {
            width: 110px; height: 110px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center;
            font-size: 34px; font-weight: 700; position: relative;
        }
        .avatar-grande .badge-tipo {
            position: absolute; bottom: 4px; right: 4px;
            width: 30px; height: 30px; border-radius: 50%;
            background-color: var(--verde-boton); color: white;
            display: flex; align-items: center; justify-content: center; font-size: 14px;
            border: 3px solid white;
        }
        .info-row { display: flex; align-items: center; gap: 14px; padding: 12px 0; border-bottom: 1px solid #f0f0f0; }
        .info-row:last-child { border-bottom: none; }
        .info-row i { color: var(--verde-principal); font-size: 18px; }
        .info-row .label { font-weight: 600; width: 110px; }
        .accion-card { border-radius: 14px; background: white; padding: 20px; height: 100%; }
        .accion-icono {
            width: 56px; height: 56px; border-radius: 14px;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 26px;
        }
        .btn-accion { background-color: var(--verde-principal); border-color: var(--verde-principal); color: white; width: 100%; }
        .btn-accion:hover { background-color: #05412f; border-color: #05412f; color: white; }
    </style>
</head>
<body>
    <nav class="navbar navbar-dark navbar-provital">
        <div class="container-fluid px-4">
            <span class="navbar-brand"><i class="bi bi-shield-fill-check"></i> PROVITAL SST</span>
            <div class="d-flex align-items-center gap-3 text-white">
                <i class="bi bi-bell"></i>
                <div class="text-end small">
                    <div class="fw-semibold">${nombreRolMostrar}</div>
                    <div>${correoUsuario}</div>
                </div>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <c:if test="${rolUsuario != 'colaborador'}">
            <a href="dashboard" class="btn btn-outline-secondary btn-sm mb-3">← Volver al Dashboard</a>
        </c:if>

        <div class="card border-0 shadow-sm p-4 mb-4">
            <div class="d-flex gap-4 align-items-center flex-wrap">
                <div class="avatar-grande">
                    ${persona.nombre.substring(0,1)}${persona.apellido.substring(0,1)}
                    <div class="badge-tipo">
                        <i class="bi ${tipo == 'empleado' ? 'bi-person-badge' : 'bi-person-workspace'}"></i>
                    </div>
                </div>

                <div class="flex-grow-1">
                    <h2 class="fw-bold mb-3">${persona.nombre} ${persona.apellido}</h2>

                    <div class="info-row">
                        <i class="bi bi-person-vcard"></i>
                        <span class="label">CI</span>
                        <span>${persona.ci}</span>
                    </div>
                    <div class="info-row">
                        <i class="bi bi-person"></i>
                        <span class="label">Tipo</span>
                        <span>${tipo == 'empleado' ? 'Empleado' : 'Colaborador'}</span>
                    </div>
                    <c:if test="${nombreEmpresa != null}">
                        <div class="info-row">
                            <i class="bi bi-building"></i>
                            <span class="label">Empresa</span>
                            <span>${nombreEmpresa}</span>
                        </div>
                    </c:if>
                    <div class="info-row">
                        <i class="bi bi-tag"></i>
                        <span class="label">Categoría</span>
                        <span>${persona.categoria == 'admisional' ? 'Admisional' : 'Manipulador de Alimentos'}</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row g-4">
            <c:if test="${persona.categoria == 'admisional'}">
                <div class="col-md-4">
                    <div class="accion-card shadow-sm">
                        <div class="accion-icono mb-3"><i class="bi bi-clipboard2-pulse"></i></div>
                        <h6 class="fw-bold">Ficha Médica</h6>
                        <p class="text-muted small">Consulta, crea y descarga la ficha médica ocupacional.</p>
                        <a href="ficha?idPersona=${persona.idPersona}" class="btn btn-accion">
                            Ver / Crear Ficha <i class="bi bi-chevron-right"></i>
                        </a>
                    </div>
                </div>
            </c:if>

            <div class="col-md-4">
                <div class="accion-card shadow-sm">
                    <div class="accion-icono mb-3"><i class="bi bi-file-earmark-medical"></i></div>
                    <h6 class="fw-bold">Estudios</h6>
                    <p class="text-muted small">Consulta, gestiona y sube los estudios médicos.</p>
                    <a href="estudio?idPersona=${persona.idPersona}" class="btn btn-accion">
                        Ver / Subir Estudios <i class="bi bi-chevron-right"></i>
                    </a>
                </div>
            </div>

            <div class="col-md-4">
                <div class="accion-card shadow-sm">
                    <div class="accion-icono mb-3"><i class="bi bi-patch-check"></i></div>
                    <h6 class="fw-bold">Certificado</h6>
                    <p class="text-muted small">Crea o visualiza los certificados médicos.</p>
                    <c:choose>
                        <c:when test="${persona.categoria == 'admisional'}">
                            <a href="certifMed?idPersona=${persona.idPersona}" class="btn btn-accion">
                                Ver / Crear Certificado <i class="bi bi-chevron-right"></i>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="certifManipulador?idPersona=${persona.idPersona}" class="btn btn-accion">
                                Ver / Crear Certificado <i class="bi bi-chevron-right"></i>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
        <script>
        if ('serviceWorker' in navigator) {
          window.addEventListener('load', () => {
            navigator.serviceWorker.register('sw.js')
              .then(reg => console.log('Service Worker registrado con éxito:', reg.scope))
              .catch(err => console.error('Error al registrar Service Worker:', err));
          });
        }
      </script>
</body>
</html>