<%-- 
    Document   : FichaAntecedentePersonal
    Created on : 2 ago. 2026, 14:22:31
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 3: Antecedentes Personales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaDatosPersonales?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 3 de 12: Antecedentes Personales</h4>

        <form action="fichaAntecedentePersonal" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="form-check mb-2">
                <input type="checkbox" name="enfermedades" class="form-check-input" id="enf" ${datos.enfermedades ? 'checked' : ''} />
                <label class="form-check-label" for="enf">Enfermedades actuales o previas</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="cirugias" class="form-check-input" id="cir" ${datos.cirugias ? 'checked' : ''} />
                <label class="form-check-label" for="cir">Cirugías / Hospitalizaciones</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="medicamentos" class="form-check-input" id="med" ${datos.medicamentos ? 'checked' : ''} />
                <label class="form-check-label" for="med">Medicamentos que toma actualmente</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="alergias" class="form-check-input" id="ale" ${datos.alergias ? 'checked' : ''} />
                <label class="form-check-label" for="ale">Alergias (medicamentos, alimentos, otras)</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="otrosRelevantes" class="form-check-input" id="otr" ${datos.otrosRelevantes ? 'checked' : ''} />
                <label class="form-check-label" for="otr">Antecedentes relevantes</label>
            </div>

            <div class="mb-3">
                <label class="form-label">Observaciones (si marcó algo como "Sí")</label>
                <textarea name="observaciones" class="form-control">${datos.observaciones}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>