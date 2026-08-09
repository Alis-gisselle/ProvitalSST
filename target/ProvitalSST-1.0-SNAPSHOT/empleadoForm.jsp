<%-- 
    Document   : empleadoForm
    Created on : 1 ago. 2026, 15:44:26
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empleado != null ? "Editar" : "Nuevo"} Empleado</title>
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
    <div class="container mt-4" style="max-width: 900px;">
        <a href="empresa?accion=empleados&id=${empleado != null ? empleado.idEmpresaCliente : idEmpresaNuevo}" class="btn btn-outline-secondary btn-sm mb-3">← Volver</a>

        <div class="card border-0 shadow-sm">
            <div class="card-body p-4">
    <form action="empleado" method="post" id="formEmpleado" novalidate>
    <c:if test="${empleado != null}">
        <input type="hidden" name="idPersona" value="${empleado.idPersona}" />
    </c:if>
    <input type="hidden" name="idEmpresaCliente" value="${empleado != null ? empleado.idEmpresaCliente : idEmpresaNuevo}" />

    <div class="row">
        <div class="col-md-3 text-center mb-4">
            <div class="icono-circulo mx-auto">
                <i class="bi bi-person-vcard"></i>
                <div class="badge-plus"><i class="bi bi-plus"></i></div>
            </div>
            <h5 class="fw-bold mt-3 mb-1">${empleado != null ? "Editar" : "Nuevo"} Empleado</h5>
            <p class="text-muted small">Completa los datos del ${empleado != null ? "empleado para actualizarlo" : "nuevo empleado para registrarlo"} en el sistema.</p>
        </div>

        <div class="col-md-9">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-semibold"><i class="bi bi-person"></i> Nombre <span>*</span></label>
                    <input type="text" name="nombre" class="form-control" placeholder="Ingrese el nombre" value="${empleado.nombre}" required minlength="2" />
                    <div class="invalid-feedback">Ingrese un nombre válido (mínimo 2 letras).</div>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label fw-semibold"><i class="bi bi-person"></i> Apellido <span>*</span></label>
                    <input type="text" name="apellido" class="form-control" placeholder="Ingrese el apellido" value="${empleado.apellido}" required minlength="2" />
                    <div class="invalid-feedback">Ingrese un apellido válido (mínimo 2 letras).</div>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold"><i class="bi bi-person-badge"></i> CI <span>*</span></label>
                <input type="number" name="ci" class="form-control" placeholder="Ingrese el número de CI" value="${empleado.ci}" required min="100000" max="99999999" />
                <div class="invalid-feedback">Ingrese un número de CI válido (entre 6 y 8 dígitos).</div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold"><i class="bi bi-calendar3"></i> Fecha de Nacimiento <span>*</span></label>
                <input type="date" name="fechaNacimiento" class="form-control" value="${empleado.fechaNacimiento}" required id="fechaNacimiento" />
                <div class="invalid-feedback" id="errorFecha">Ingrese una fecha válida (la persona debe ser mayor de 18 años).</div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold"><i class="bi bi-briefcase"></i> Cargo <span>*</span></label>
                <input type="text" name="cargo" class="form-control" placeholder="Ingrese el cargo o puesto" value="${empleado.cargo}" required minlength="2" />
                <div class="invalid-feedback">Ingrese un cargo válido.</div>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold"><i class="bi bi-tag"></i> Categoría <span>*</span></label>
                <select name="categoria" class="form-select" required>
                    <option value="" ${empty empleado ? 'selected' : ''} disabled>-- Seleccione una categoría --</option>
                    <option value="admisional" ${empleado.categoria == 'admisional' ? 'selected' : ''}>Admisional</option>
                    <option value="manipulador" ${empleado.categoria == 'manipulador' ? 'selected' : ''}>Manipulador de Alimentos</option>
                </select>
                <div class="invalid-feedback">Seleccione una categoría.</div>
            </div>
        </div>
    </div>

    <hr class="my-4">

    <div class="d-flex justify-content-end gap-2">
        <a href="empresa?accion=empleados&id=${empleado != null ? empleado.idEmpresaCliente : idEmpresaNuevo}" class="btn btn-outline-secondary">
            <i class="bi bi-x-lg"></i> Cancelar
        </a>
        <button type="submit" class="btn btn-guardar">
            <i class="bi bi-save"></i> Guardar Empleado
        </button>
    </div>
</form>

<script>
document.getElementById('formEmpleado').addEventListener('submit', function(e) {
    const form = this;
    const fechaInput = document.getElementById('fechaNacimiento');

    // Validación extra: mayor de 18 años
    let fechaValida = true;
    if (fechaInput.value) {
        const fechaNac = new Date(fechaInput.value);
        const hoy = new Date();
        let edad = hoy.getFullYear() - fechaNac.getFullYear();
        const m = hoy.getMonth() - fechaNac.getMonth();
        if (m < 0 || (m === 0 && hoy.getDate() < fechaNac.getDate())) edad--;
        fechaValida = edad >= 18 && fechaNac <= hoy;
    }
    fechaInput.setCustomValidity(fechaValida ? '' : 'invalida');

    if (!form.checkValidity()) {
        e.preventDefault();
        e.stopPropagation();
    }
    form.classList.add('was-validated');
});
</script>
            </div>
        </div>
    </div>
</body>
</html>