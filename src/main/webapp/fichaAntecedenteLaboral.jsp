<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 5: Antecedentes Laborales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaHabito?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 5 de 12: Antecedentes Laborales</h4>
        <p class="text-muted">Solo aplica para evaluación admisional.</p>

        <form action="fichaAntecedenteLaboral" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="mb-3">
                <label class="form-label">Empresa Anterior</label>
                <input type="text" name="empresaAnterior" class="form-control" value="${datos.empresaAnterior}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Puesto / Cargo</label>
                <input type="text" name="puestoAnterior" class="form-control" value="${datos.puestoAnterior}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Período Desde</label>
                <input type="date" name="periodoDesde" class="form-control" value="${datos.periodoDesde}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Período Hasta</label>
                <input type="date" name="periodoHasta" class="form-control" value="${datos.periodoHasta}" />
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>