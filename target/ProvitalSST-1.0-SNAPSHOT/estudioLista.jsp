<%-- 
    Document   : estudioLista
    Created on : 1 ago. 2026, 19:56:19
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estudios - ${persona.nombre} ${persona.apellido}</title>
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
            <div class="icono-circulo"><i class="bi bi-file-earmark-medical"></i></div>
            <h3 class="fw-bold mb-0">Estudios - ${persona.nombre} ${persona.apellido}</h3>
        </div>

        <c:if test="${!soloLectura}">
            <div class="card border-0 shadow-sm mb-4">
                <div class="card-body p-4">
                    <h6 class="fw-bold mb-3"><i class="bi bi-cloud-arrow-up"></i> Subir Nuevo Estudio (PDF)</h6>
                    <form action="estudio" method="post" enctype="multipart/form-data">
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
                                <label class="form-label">Archivo PDF</label>
                                <input type="file" name="archivoPdf" class="form-control" accept="application/pdf" required />
                            </div>
                        </div>

                        <button type="submit" class="btn btn-provital">
                            <i class="bi bi-upload"></i> Subir Estudio
                        </button>
                    </form>
                </div>
            </div>
        </c:if>

        <h6 class="fw-bold mb-3"><i class="bi bi-clock-history"></i> Historial de Estudios</h6>
        <div class="card border-0 shadow-sm">
            <table class="table table-hover mb-0">
                <thead>
                    <tr>
                        <th><i class="bi bi-calendar3"></i> Fecha</th>
                        <th><i class="bi bi-person-badge"></i> Médico</th>
                        <th><i class="bi bi-file-earmark-pdf"></i> Archivo</th>
                        <c:if test="${!soloLectura}">
                            <th><i class="bi bi-lightning-fill"></i> Acciones</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="est" items="${listaEstudios}">
                        <tr>
                            <td>${est.fecha}</td>
                            <td>${est.nombreMedico}</td>
                            <td>
                                <a href="uploads/estudios/${est.archivoPdf}" target="_blank" class="btn btn-sm btn-provital">
                                    <i class="bi bi-eye"></i> Ver PDF
                                </a>
                            </td>
                            <c:if test="${!soloLectura}">
                                <td>
                                    <a href="estudio?accion=eliminar&id=${est.idEstudio}&idPersona=${persona.idPersona}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Seguro que deseas eliminar este estudio?');">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>