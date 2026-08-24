<%-- 
    Document   : fichaPuestoErgonomia
    Created on : 2 ago. 2026, 15:11:11
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 6: Puesto y Ergonomía</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <c:choose>
            <c:when test="${tipoEvaluacion == 'admisional'}">
                <a href="fichaAntecedenteLaboral?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
            </c:when>
            <c:otherwise>
                <a href="fichaHabito?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
            </c:otherwise>
        </c:choose>
        <h4>Paso 6 de 12: Descripción del Puesto y Ergonomía</h4>

        <form action="fichaPuestoErgonomia" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="mb-3">
                <label class="form-label">Descripción General del Puesto</label>
                <textarea name="descripcionGeneral" class="form-control">${datos.descripcionGeneral}</textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Tareas Principales</label>
                <textarea name="tareasPrincipales" class="form-control">${datos.tareasPrincipales}</textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Tipo de Actividad</label>
                <select name="tipoActividad" class="form-select">
                    <option value="dinamica" ${datos.tipoActividad == 'dinamica' ? 'selected' : ''}>Dinámica</option>
                    <option value="estatica" ${datos.tipoActividad == 'estatica' ? 'selected' : ''}>Estática</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Esfuerzo Físico</label>
                <select name="esfuerzoFisico" class="form-select">
                    <option value="liviano" ${datos.esfuerzoFisico == 'liviano' ? 'selected' : ''}>Liviano</option>
                    <option value="moderado" ${datos.esfuerzoFisico == 'moderado' ? 'selected' : ''}>Moderado</option>
                    <option value="intenso" ${datos.esfuerzoFisico == 'intenso' ? 'selected' : ''}>Intenso</option>
                </select>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="levantaCargas" class="form-check-input" id="cargas" ${datos.levantaCargas ? 'checked' : ''} />
                <label class="form-check-label" for="cargas">Levanta / Transporta Cargas</label>
            </div>
            <div class="mb-3">
                <label class="form-label">Peso Aproximado (kg)</label>
                <input type="number" step="0.01" name="pesoAprox" class="form-control" value="${datos.pesoAprox}" />
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>