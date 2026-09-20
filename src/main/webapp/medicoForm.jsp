<%-- 
    Document   : medicoForm
    Created on : 1 ago. 2026, 16:44:24
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("paginaActiva", "configuracion"); %>
<!DOCTYPE html>
<html>
<head>
    <title>${medico != null ? "Editar" : "Nuevo"} Médico Laboral</title>
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
        .icono-circulo {
            width: 90px; height: 90px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 40px;
            position: relative;
        }
        .icono-circulo .badge-plus {
            position: absolute; bottom: 0; right: 0;
            width: 28px; height: 28px; border-radius: 50%;
            background-color: var(--verde-boton); color: white;
            display: flex; align-items: center; justify-content: center; font-size: 14px;
            border: 3px solid white;
        }
        label span { color: #dc3545; }
        label i { color: var(--verde-principal); margin-right: 6px; }
        .btn-guardar { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-guardar:hover { background-color: #066a41; border-color: #066a41; color: white; }
    </style>
</head>
<body>
    <div class="layout-admin">
        <jsp:include page="sidebar.jsp" />
        <div class="contenido-admin">
            <jsp:include page="topbar.jsp" />
                <div class="container mt-4" style="max-width: 900px;">
                    <a href="medico" class="btn btn-outline-secondary btn-sm mb-3">← Volver</a>

                    <div class="card border-0 shadow-sm">
                        <div class="card-body p-4">
                            <form action="medico" method="post" id="formMedico" novalidate>
                                <c:if test="${medico != null}">
                                    <input type="hidden" name="idMedicoLaboral" value="${medico.idMedicoLaboral}" />
                                </c:if>

                                <div class="row">
                                    <div class="col-md-3 text-center mb-4">
                                        <div class="icono-circulo mx-auto">
                                            <i class="bi bi-person-badge"></i>
                                            <div class="badge-plus"><i class="bi bi-plus"></i></div>
                                        </div>
                                        <h5 class="fw-bold mt-3 mb-1">${medico != null ? "Editar" : "Nuevo"} Médico</h5>
                                        <p class="text-muted small">Completa los datos del ${medico != null ? "médico para actualizarlo" : "nuevo médico para registrarlo"} en el sistema.</p>
                                    </div>

                                    <div class="col-md-9">
                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label class="form-label fw-semibold"><i class="bi bi-person"></i> Nombre <span>*</span></label>
                                                <input type="text" name="nombre" class="form-control" placeholder="Ingrese el nombre" value="${medico.nombre}" required minlength="2" />
                                                <div class="invalid-feedback">Ingrese un nombre válido.</div>
                                            </div>
                                            <div class="col-md-6 mb-3">
                                                <label class="form-label fw-semibold"><i class="bi bi-person"></i> Apellido <span>*</span></label>
                                                <input type="text" name="apellido" class="form-control" placeholder="Ingrese el apellido" value="${medico.apellido}" required minlength="2" />
                                                <div class="invalid-feedback">Ingrese un apellido válido.</div>
                                            </div>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold"><i class="bi bi-mortarboard"></i> Especialidad <span>*</span></label>
                                            <input type="text" name="especialidad" class="form-control" placeholder="Ej: Medicina del Trabajo" value="${medico.especialidad}" required minlength="3" />
                                            <div class="invalid-feedback">Ingrese una especialidad válida.</div>
                                        </div>

                                        <div class="mb-3">
                                            <label class="form-label fw-semibold"><i class="bi bi-file-earmark-text"></i> Matrícula Profesional <span>*</span></label>
                                            <input type="text" name="matricula" class="form-control" placeholder="Ej: 15.925" value="${medico.matricula}" required />
                                            <div class="invalid-feedback">Ingrese una matrícula válida.</div>
                                        </div>

                                        <c:if test="${medico == null}">
                                            <hr class="my-4">
                                            <h6 class="fw-semibold mb-3"><i class="bi bi-shield-lock"></i> Acceso al Sistema</h6>

                                            <div class="mb-3">
                                                <label class="form-label fw-semibold">Correo <span>*</span></label>
                                                <input type="email" name="correo" class="form-control" placeholder="correo@ejemplo.com" required />
                                                <div class="invalid-feedback">Ingrese un correo válido.</div>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label fw-semibold">Contraseña <span>*</span></label>
                                                <input type="password" name="contrasenia" class="form-control" placeholder="Mínimo 6 caracteres" required minlength="6" />
                                                <div class="invalid-feedback">La contraseña debe tener al menos 6 caracteres.</div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>

                                <hr class="my-4">

                                <div class="d-flex justify-content-end gap-2">
                                    <a href="medico" class="btn btn-outline-secondary">
                                        <i class="bi bi-x-lg"></i> Cancelar
                                    </a>
                                    <button type="submit" class="btn btn-guardar">
                                        <i class="bi bi-save"></i> Guardar Médico
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </div>

    <script>
    document.getElementById('formMedico').addEventListener('submit', function(e) {
        if (!this.checkValidity()) {
            e.preventDefault();
            e.stopPropagation();
        }
        this.classList.add('was-validated');
    });
    </script>
</body>
</html>