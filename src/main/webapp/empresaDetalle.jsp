<%-- 
    Document   : empresaDetalle
    Created on : 1 ago. 2026, 15:22:27
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Empleados - ${empresa.nombre}</title>
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
        .breadcrumb-custom a { color: var(--verde-principal); text-decoration: none; }
        .icono-circulo {
            width: 64px; height: 64px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-size: 28px; flex-shrink: 0;
        }
        .btn-provital { background-color: var(--verde-boton); border-color: var(--verde-boton); color: white; }
        .btn-provital:hover { background-color: #066a41; border-color: #066a41; color: white; }
        table thead { background-color: var(--verde-principal); color: white; }
        .avatar-iniciales {
            width: 38px; height: 38px; border-radius: 50%;
            background-color: var(--verde-claro); color: var(--verde-principal);
            display: flex; align-items: center; justify-content: center; font-weight: 600; font-size: 13px;
        }
        .badge-manipulador { background-color: #f3e8fd; color: #8a3ffc; }
        .badge-admisional { background-color: #e3efff; color: #1769aa; }
    </style>
</head>
<body>
    <div class="container mt-4">
        <nav class="breadcrumb-custom small mb-3">
            <a href="empresa">Empresas</a> <i class="bi bi-chevron-right small mx-1"></i>
            <span>${empresa.nombre}</span> <i class="bi bi-chevron-right small mx-1"></i>
            <span class="text-muted">Empleados</span>
        </nav>

        <div class="d-flex gap-3 mb-4">
            <div class="icono-circulo"><i class="bi bi-briefcase"></i></div>
            <div>
                <div class="text-muted">Empleados de</div>
                <h2 class="fw-bold mb-0">${empresa.nombre}</h2>
                <p class="text-muted mb-0">Gestiona la información de los empleados de esta empresa.</p>
            </div>
        </div>

        <div class="d-flex gap-2 mb-4">
            <a href="empresa" class="btn btn-outline-secondary"><i class="bi bi-arrow-left"></i> Volver a Empresas</a>
            <a href="empleado?accion=nuevo&idEmpresa=${empresa.idEmpresaCliente}" class="btn btn-provital">
                <i class="bi bi-plus-lg"></i> Nuevo Empleado
            </a>
        </div>

        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <form action="empresa" method="get" class="row g-2 align-items-center">
                    <input type="hidden" name="accion" value="empleados" />
                    <input type="hidden" name="id" value="${empresa.idEmpresaCliente}" />
                    <div class="col">
                        <input type="text" name="buscar" class="form-control" placeholder="Buscar por nombre o CI..." value="${buscar}" />
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

        <div class="card border-0 shadow-sm">
            <table class="table table-hover mb-0" id="tablaEmpleados">
                <thead>
                    <tr>
                        <th></th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>CI</th>
                        <th>Cargo</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${listaEmpleados}">
                        <tr data-categoria="${emp.categoria}">
                            <td>
                                <div class="avatar-iniciales">${emp.nombre.substring(0,1)}${emp.apellido.substring(0,1)}</div>
                            </td>
                            <td>${emp.nombre}</td>
                            <td>${emp.apellido}</td>
                            <td>${emp.ci}</td>
                            <td>${emp.cargo}</td>
                            <td>
                                <span class="badge rounded-pill ${emp.categoria == 'manipulador' ? 'badge-manipulador' : 'badge-admisional'}">
                                    ${emp.categoria == 'manipulador' ? 'Manipulador' : 'Admisional'}
                                </span>
                            </td>
                            <td>
                                <a href="persona?id=${emp.idPersona}" class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i> Ver Detalle</a>
                                <a href="empleado?accion=editar&id=${emp.idPersona}" class="btn btn-sm btn-outline-warning"><i class="bi bi-pencil"></i> Editar</a>
                                <a href="empleado?accion=eliminar&id=${emp.idPersona}&idEmpresa=${empresa.idEmpresaCliente}"
                                   class="btn btn-sm btn-outline-danger"
                                   onclick="return confirm('¿Seguro que deseas eliminar este empleado?');">
                                    <i class="bi bi-trash"></i> Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

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
            const categoria = document.getElementById('filtroCategoria').value;
            const porPagina = parseInt(document.getElementById('filasPorPagina').value);
            const todasLasFilas = Array.from(document.querySelectorAll('#tablaEmpleados tbody tr'));

            const filasCoincidentes = todasLasFilas.filter(fila =>
                categoria === '' || fila.dataset.categoria === categoria
            );

            const totalPaginas = Math.max(1, Math.ceil(filasCoincidentes.length / porPagina));
            if (paginaActual > totalPaginas) paginaActual = totalPaginas;
            if (paginaActual < 1) paginaActual = 1;

            todasLasFilas.forEach(fila => fila.style.display = 'none');

            const inicio = (paginaActual - 1) * porPagina;
            filasCoincidentes.slice(inicio, inicio + porPagina).forEach(fila => fila.style.display = '');

            document.getElementById('numeroPagina').textContent = paginaActual + ' / ' + totalPaginas;
            document.getElementById('infoPaginacion').textContent =
                'Mostrando ' + (filasCoincidentes.length === 0 ? 0 : inicio + 1) + ' a ' +
                Math.min(inicio + porPagina, filasCoincidentes.length) + ' de ' + filasCoincidentes.length + ' empleados';
        }

        document.addEventListener('DOMContentLoaded', renderizarTabla);
    </script>
</body>
</html>