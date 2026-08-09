<%-- 
    Document   : empresaForm
    Created on : 1 ago. 2026, 15:27:28
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empresa != null ? "Editar" : "Nueva"} Empresa Cliente</title>
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
        }
        .form-control { padding: 10px 14px; }
        .input-icono { position: relative; }
        .input-icono i { position: absolute; right: 14px; top: 50%; transform: translateY(-50%); color: #aaa; }
        .btn-guardar { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-guardar:hover { background-color: #066a41; border-color: #066a41; color: white; }
        .alerta-info { background-color: var(--verde-claro); border-radius: 12px; padding: 18px; }
        .alerta-info-icono {
            width: 32px; height: 32px; border-radius: 50%;
            background-color: var(--verde-principal); color: white;
            display: flex; align-items: center; justify-content: center; flex-shrink: 0;
        }
        label span { color: #dc3545; }
    </style>
</head>
<body>
    <div class="container mt-4" style="max-width: 750px;">
       <a href="empresa" class="btn btn-outline-secondary btn-sm mb-3">← Volver</a>

        <h2 class="fw-bold mb-0">${empresa != null ? "Editar" : "Nueva"} Empresa Cliente</h2>
        <p class="text-muted">Completa los datos para ${empresa != null ? "actualizar la" : "registrar una nueva"} empresa cliente en el sistema.</p>
        <hr>

        <div class="card border-0 shadow-sm mt-4">
            <div class="card-body p-4">
                <form action="empresa" method="post">
                    <c:if test="${empresa != null}">
                        <input type="hidden" name="idEmpresaCliente" value="${empresa.idEmpresaCliente}" />
                    </c:if>

                    <div class="row">
                        <div class="col-md-3 text-center mb-4">
                            <div class="icono-circulo mx-auto">
                                <i class="bi bi-building"></i>
                            </div>
                        </div>

                        <div class="col-md-9">
                            <div class="mb-3">
                                <label class="form-label fw-semibold">RUC <span>*</span></label>
                                <div class="input-icono">
                                    <input type="number" name="ruc" class="form-control" placeholder="Ej: 80012345-6" value="${empresa.ruc}" required />
                                    <i class="bi bi-person-vcard"></i>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-semibold">Nombre de la Empresa <span>*</span></label>
                                <div class="input-icono">
                                    <input type="text" name="nombre" class="form-control" placeholder="Ej: Servicios Tecnológicos del Paraguay S.A." value="${empresa.nombre}" required />
                                    <i class="bi bi-building"></i>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-semibold">Ciudad <span>*</span></label>
                                <div class="input-icono">
                                    <input type="text" name="direccion" class="form-control" placeholder="Ej: Fernando de la Mora" value="${empresa.direccion}" required />
                                    <i class="bi bi-geo-alt"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="d-flex justify-content-end gap-2">
                        <a href="empresa" class="btn btn-outline-secondary">
                            <i class="bi bi-x-lg"></i> Cancelar
                        </a>
                        <button type="submit" class="btn btn-guardar">
                            <i class="bi bi-save"></i> Guardar Empresa
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="alerta-info d-flex gap-3 mt-4 mb-4">
            <div class="alerta-info-icono"><i class="bi bi-info-lg"></i></div>
            <div>
                <div class="fw-semibold" style="color: var(--verde-principal);">Información</div>
                <div class="text-muted small">Asegúrate de ingresar correctamente los datos de la empresa. Los campos marcados con <span class="text-danger">*</span> son obligatorios.</div>
            </div>
        </div>
    </div>
</body>
</html>