<%-- 
    Document   : certifMedLista
    Created on : 1 ago. 2026, 20:49:54
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Certificados - ${persona.nombre} ${persona.apellido}</title>
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
        body { background-color: var(--fondo); color: var(--texto); margin: 0; }
        .topbar-simple {
            background: var(--verde-principal); color: white;
            display: flex; justify-content: space-between; align-items: center;
            padding: 14px 28px;
        }
        .topbar-simple .marca { display: flex; align-items: center; gap: 8px; font-weight: 700; font-size: 18px; }
        .topbar-simple .usuario { display: flex; align-items: center; gap: 8px; opacity: 0.9; }
        .icono-circulo {
            width: 40px; height: 40px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 20px; flex-shrink: 0;
        }
        .btn-volver { border-color: var(--verde-principal); color: var(--verde-principal); }
        .btn-volver:hover { background-color: var(--verde-claro); color: var(--verde-principal); }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        table thead { background-color: var(--verde-principal); color: white; }
        .badge-apto { background-color: var(--verde-claro); color: var(--verde-principal); }
        .badge-no-apto { background-color: #fde2e4; color: #dc3545; }
        label { font-weight: 600; }
    </style>
</head>
<body>
    <div class="topbar-simple">
        <div class="marca"><i class="bi bi-shield-fill-check"></i> PROVITAL SST</div>
        <div class="usuario"><i class="bi bi-person-circle"></i> ${sessionScope.usuarioLogueado.correo}</div>
    </div>

    <div class="container mt-4 mb-4">
        <a href="persona?id=${persona.idPersona}" class="btn btn-outline-secondary btn-volver btn-sm mb-3">
            <i class="bi bi-arrow-left"></i> Volver
        </a>

        <div class="d-flex align-items-center gap-2 mb-4">
            <div class="icono-circulo"><i class="bi bi-patch-check"></i></div>
            <h3 class="fw-bold mb-0">Certificados Médicos - ${persona.nombre} ${persona.apellido}</h3>
        </div>

        <c:if test="${!soloLectura}">
            <div class="card border-0 shadow-sm mb-4">
                <div class="card-body p-4">
                    <h6 class="fw-bold mb-3"><i class="bi bi-file-earmark-plus"></i> Nuevo Certificado</h6>
                    <form action="certifMed" method="post">
                        <input type="hidden" name="idPersona" value="${persona.idPersona}" />

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Médico Laboral</label>
                                <select name="idMedicoLaboral" class="form-select" required>
                                    <option value="">-- Seleccione --</option>
                                    <c:forEach var="m" items="${listaMedicos}">
                                        <option value="${m.idMedicoLaboral}">${m.nombre} ${m.apellido}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Aptitud</label>
                                <select name="aptitud" class="form-select" required>
                                    <option value="" selected disabled>Seleccione</option>
                                    <option value="apto">APTO</option>
                                    <option value="no_apto">NO APTO</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Tipo de Evaluación</label>
                            <select name="tipoEvaluacion" class="form-select" required>
                                <option value="admisional">Admisional IPS / Pre Ocupacional</option>
                                <option value="periodico">Periódico según riesgo</option>
                                <option value="especial_dosis">Especial tras superación de límite de dosis</option>
                                <option value="ausencia_prolongada">Tras ausencia prolongada al trabajo</option>
                                <option value="nuevos_riesgos">Tras asignación de tareas con nuevos riesgos</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Recomendación</label>
                            <textarea name="recomendacion" class="form-control" rows="2" placeholder="Ingrese la recomendación..."></textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Observaciones</label>
                            <textarea name="observaciones" class="form-control" rows="2" placeholder="Ingrese observaciones..."></textarea>
                        </div>

                        <button type="submit" class="btn btn-provital">
                            <i class="bi bi-save"></i> Guardar Certificado
                        </button>
                    </form>
                </div>
            </div>
        </c:if>

        <h6 class="fw-bold mb-3"><i class="bi bi-clock-history"></i> Historial</h6>
        <div class="card border-0 shadow-sm">
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th><i class="bi bi-calendar3"></i> Emisión</th>
                        <th><i class="bi bi-calendar-x"></i> Vencimiento</th>
                        <th><i class="bi bi-tag"></i> Tipo</th>
                        <th><i class="bi bi-patch-check"></i> Aptitud</th>
                        <th><i class="bi bi-person-badge"></i> Médico</th>
                        <th><i class="bi bi-lightning-fill"></i> Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cert" items="${listaCertificados}">
                        <tr>
                            <td>${cert.fechaEmision}</td>
                            <td>${cert.fechaVenc}</td>
                            <td>${cert.tipoEvaluacion}</td>
                            <td>
                                <span class="badge rounded-pill ${cert.aptitud == 'apto' ? 'badge-apto' : 'badge-no-apto'}">
                                    ${cert.aptitud}
                                </span>
                            </td>
                            <td>${cert.nombreMedico}</td>
                            <td>
                                <a href="certifMed?accion=descargar&id=${cert.idCertifMed}" target="_blank" class="btn btn-sm btn-provital">
                                    <i class="bi bi-file-earmark-arrow-down"></i> Descargar PDF
                                </a>
                                <c:if test="${!soloLectura}">
                                    <a href="certifMed?accion=eliminar&id=${cert.idCertifMed}&idPersona=${persona.idPersona}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Seguro que deseas eliminar este certificado?');">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>