<%-- 
    Document   : fichaFirma
    Created on : 2 ago. 2026, 17:16:29
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 11: Firmas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaExamenFisico?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 11 de 12: Declaración Jurada y Firmas</h4>
        <p class="text-muted">Al guardar, se registra la fecha de firma de hoy para ambas partes.</p>

        <form action="fichaFirma" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="mb-3">
                <label class="form-label">C.I. del Trabajador (firma)</label>
                <input type="text" name="ciTrabajador" class="form-control" value="${datos.ciTrabajador}" />
            </div>

            <div class="mb-3">
                <label class="form-label">C.I. del Técnico / Evaluador (firma)</label>
                <input type="text" name="ciTecnico" class="form-control" value="${datos.ciTecnico}" />
            </div>

            <button type="submit" class="btn btn-success">Finalizar Ficha ✓</button>
        </form>
    </div>
</body>
</html>
