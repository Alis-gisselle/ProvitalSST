<%-- 
    Document   : fichaExamenFisico
    Created on : 2 ago. 2026, 17:07:48
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 10: Examen Físico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaTrastornoMusculo?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 10 de 12: Examen Físico y Medidas Clínicas</h4>

        <form action="fichaExamenFisico" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="mb-3">
                <label class="form-label">Peso (kg)</label>
                <input type="number" step="0.01" name="peso" class="form-control" value="${datos.peso}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Estatura (cm)</label>
                <input type="number" step="0.01" name="estatura" class="form-control" value="${datos.estatura}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Presión Arterial</label>
                <input type="text" name="presionArterial" class="form-control" placeholder="ej: 120/80" value="${datos.presionArterial}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Frecuencia Cardíaca (lpm)</label>
                <input type="number" name="frecuenciaCardiaca" class="form-control" value="${datos.frecuenciaCardiaca}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Agudeza Visual - Ojo Derecho</label>
                <input type="text" name="agudezaVisualDerecho" class="form-control" value="${datos.agudezaVisualDerecho}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Agudeza Visual - Ojo Izquierdo</label>
                <input type="text" name="agudezaVisualIzquierdo" class="form-control" value="${datos.agudezaVisualIzquierdo}" />
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>
