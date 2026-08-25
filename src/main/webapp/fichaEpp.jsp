<%-- 
    Document   : fichaEpp
    Created on : 2 ago. 2026, 16:06:35
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 8: EPP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="fichaRiesgoLaboral?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 8 de 12: Equipos de Protección Personal (EPP)</h4>

        <form action="fichaEpp" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="form-check mb-2">
                <input type="checkbox" name="tapaboca" class="form-check-input" id="tap" ${datos.tapaboca ? 'checked' : ''} />
                <label class="form-check-label" for="tap">Tapaboca</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="proteccionAuditiva" class="form-check-input" id="pro" ${datos.proteccionAuditiva ? 'checked' : ''} />
                <label class="form-check-label" for="pro">Protección Auditiva</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="casco" class="form-check-input" id="cas" ${datos.casco ? 'checked' : ''} />
                <label class="form-check-label" for="cas">Casco</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="gafas" class="form-check-input" id="gaf" ${datos.gafas ? 'checked' : ''} />
                <label class="form-check-label" for="gaf">Gafas</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="botas" class="form-check-input" id="bot" ${datos.botas ? 'checked' : ''} />
                <label class="form-check-label" for="bot">Botas</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="guantes" class="form-check-input" id="gua" ${datos.guantes ? 'checked' : ''} />
                <label class="form-check-label" for="gua">Guantes</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="delantal" class="form-check-input" id="del" ${datos.delantal ? 'checked' : ''} />
                <label class="form-check-label" for="del">Delantal</label>
            </div>

            <div class="mb-3">
                <label class="form-label">Otros (especifique)</label>
                <input type="text" name="otros" class="form-control" value="${datos.otros}" />
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>
