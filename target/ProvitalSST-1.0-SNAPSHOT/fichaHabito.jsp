<%-- 
    Document   : fichaHabito
    Created on : 2 ago. 2026, 14:37:32
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 4: Hábitos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaAntecedentePersonal?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 4 de 12: Hábitos</h4>

        <form action="fichaHabito" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="form-check mb-2">
                <input type="checkbox" name="fuma" class="form-check-input" id="fuma" ${datos.fuma ? 'checked' : ''} />
                <label class="form-check-label" for="fuma">Fuma</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="consumeAlcohol" class="form-check-input" id="alc" ${datos.consumeAlcohol ? 'checked' : ''} />
                <label class="form-check-label" for="alc">Consume alcohol</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="actividadFisica" class="form-check-input" id="act" ${datos.actividadFisica ? 'checked' : ''} />
                <label class="form-check-label" for="act">Actividad física regular</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="suenoAdecuado" class="form-check-input" id="sue" ${datos.suenoAdecuado ? 'checked' : ''} />
                <label class="form-check-label" for="sue">Horas de sueño adecuadas (7-8h)</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="otrosHabitos" class="form-check-input" id="otr" ${datos.otrosHabitos ? 'checked' : ''} />
                <label class="form-check-label" for="otr">Otros hábitos relevantes</label>
            </div>

            <div class="mb-3">
                <label class="form-label">Observaciones</label>
                <textarea name="observaciones" class="form-control">${datos.observaciones}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>