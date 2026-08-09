<%-- 
    Document   : usuarioForm
    Created on : 1 ago. 2026, 16:52:28
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${usuario != null ? "Editar" : "Nuevo"} Usuario</title>
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
    <div class="container mt-4" style="max-width: 700px;">
        <a href="usuario" class="btn btn-outline-secondary btn-sm mb-3">← Volver</a>

        <div class="card border-0 shadow-sm">
            <div class="card-body p-4">
                <form action="usuario" method="post" id="formUsuario" novalidate>
                    <c:if test="${usuario != null}">
                        <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                    </c:if>

                    <div class="row">
                        <div class="col-md-3 text-center mb-4">
                            <div class="icono-circulo mx-auto">
                                <i class="bi bi-shield-lock"></i>
                                <div class="badge-plus"><i class="bi bi-plus"></i></div>
                            </div>
                            <h5 class="fw-bold mt-3 mb-1">${usuario != null ? "Editar" : "Nuevo"} Usuario</h5>
                        </div>

                        <div class="col-md-9">
                            <div class="mb-3">
                                <label class="form-label fw-semibold"><i class="bi bi-envelope"></i> Correo <span>*</span></label>
                                <input type="email" name="correo" class="form-control" placeholder="correo@ejemplo.com" value="${usuario.correo}" required />
                                <div class="invalid-feedback">Ingrese un correo válido.</div>
                            </div>

                            <c:if test="${usuario == null}">
                                <div class="mb-3">
                                    <label class="form-label fw-semibold"><i class="bi bi-key"></i> Contraseña <span>*</span></label>
                                    <input type="password" name="contrasenia" class="form-control" placeholder="Mínimo 6 caracteres" required minlength="6" />
                                    <div class="invalid-feedback">La contraseña debe tener al menos 6 caracteres.</div>
                                </div>
                            </c:if>

                            <div class="mb-3">
                                <label class="form-label fw-semibold"><i class="bi bi-person-badge"></i> Rol <span>*</span></label>
                                <select name="rol" id="rol" class="form-select" required onchange="mostrarEmpresa()">
                                    <option value="" ${empty usuario ? 'selected' : ''} disabled>-- Seleccione --</option>
                                    <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Admin</option>
                                    <option value="rrhh" ${usuario.rol == 'rrhh' ? 'selected' : ''}>RRHH</option>
                                </select>
                                <div class="invalid-feedback">Seleccione un rol.</div>
                            </div>

                            <div class="mb-3" id="divEmpresa" style="${usuario != null && usuario.rol == 'rrhh' ? 'display:block;' : 'display:none;'}">
                                <label class="form-label fw-semibold"><i class="bi bi-building"></i> Empresa (solo para RRHH)</label>
                                <select name="idEmpresaCliente" class="form-select">
                                    <option value="">-- Seleccione --</option>
                                    <c:forEach var="e" items="${listaEmpresas}">
                                        <option value="${e.idEmpresaCliente}" ${usuario != null && usuario.idEmpresaCliente == e.idEmpresaCliente ? 'selected' : ''}>${e.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="d-flex justify-content-end gap-2">
                        <a href="usuario" class="btn btn-outline-secondary">
                            <i class="bi bi-x-lg"></i> Cancelar
                        </a>
                        <button type="submit" class="btn btn-guardar">
                            <i class="bi bi-save"></i> Guardar Usuario
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        function mostrarEmpresa() {
            const rol = document.getElementById('rol').value;
            document.getElementById('divEmpresa').style.display = (rol === 'rrhh') ? 'block' : 'none';
        }

        document.getElementById('formUsuario').addEventListener('submit', function(e) {
            if (!this.checkValidity()) {
                e.preventDefault();
                e.stopPropagation();
            }
            this.classList.add('was-validated');
        });
    </script>
</body>
</html>