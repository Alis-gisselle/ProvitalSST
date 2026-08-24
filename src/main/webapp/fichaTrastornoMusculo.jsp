<%-- 
    Document   : fichaTrastornoMusculo
    Created on : 2 ago. 2026, 16:57:32
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 9: Trastornos Musculoesqueléticos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 700px;">
        <a href="fichaEpp?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 9 de 12: Trastornos Musculoesqueléticos</h4>

        <form action="fichaTrastornoMusculo" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <h6>Factores de Riesgo</h6>
            <div class="form-check mb-2">
                <input type="checkbox" name="posturasForzadas" class="form-check-input" id="pf" ${datos.posturasForzadas ? 'checked' : ''} />
                <label class="form-check-label" for="pf">Posturas Forzadas</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="movimientosRepetitivos" class="form-check-input" id="mr" ${datos.movimientosRepetitivos ? 'checked' : ''} />
                <label class="form-check-label" for="mr">Movimientos Repetitivos</label>
            </div>
            <div class="form-check mb-2">
                <input type="checkbox" name="ritmoElevado" class="form-check-input" id="re" ${datos.ritmoElevado ? 'checked' : ''} />
                <label class="form-check-label" for="re">Ritmo de Trabajo Elevado</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="reposoInsuficiente" class="form-check-input" id="ri" ${datos.reposoInsuficiente ? 'checked' : ''} />
                <label class="form-check-label" for="ri">Tiempos de Reposo Insuficientes</label>
            </div>

            <div class="mb-3">
                <label class="form-label">Postura Predominante</label>
                <select name="posturaPredominante" class="form-select">
                    <option value="sentado" ${datos.posturaPredominante == 'sentado' ? 'selected' : ''}>Sentado</option>
                    <option value="de_pie" ${datos.posturaPredominante == 'de_pie' ? 'selected' : ''}>De Pie</option>
                    <option value="arrodillado" ${datos.posturaPredominante == 'arrodillado' ? 'selected' : ''}>Arrodillado</option>
                    <option value="agachado" ${datos.posturaPredominante == 'agachado' ? 'selected' : ''}>Agachado</option>
                    <option value="acostado" ${datos.posturaPredominante == 'acostado' ? 'selected' : ''}>Acostado</option>
                </select>
            </div>

            <h6>Zonas Afectadas (síntomas musculoesqueléticos)</h6>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr><th>Zona</th><th>¿Presenta síntoma?</th><th>Intensidad (1-5)</th></tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Cuello</td>
                            <td><input type="checkbox" name="zona_cuello" class="form-check-input" ${mapaZonas.cuello.zona == 'cuello' ? 'checked' : ''} /></td>
                            <td><input type="number" name="intensidad_cuello" min="1" max="5" class="form-control" value="${mapaZonas.cuello.intensidad}" /></td>
                    </tr>
                    <tr>
                        <td>Hombro</td>
                        <td><input type="checkbox" name="zona_hombro" class="form-check-input" ${mapaZonas.hombro.zona == 'hombro' ? 'checked' : ''} /></td>
                        <td><input type="number" name="intensidad_hombro" min="1" max="5" class="form-control" value="${mapaZonas.hombro.intensidad}" /></td>
                    </tr>
                    <tr>
                        <td>Espalda (dorsal/lumbar)</td>
                        <td><input type="checkbox" name="zona_espalda" class="form-check-input" ${mapaZonas.espalda.zona == 'espalda' ? 'checked' : ''} /></td>
                        <td><input type="number" name="intensidad_espalda" min="1" max="5" class="form-control" value="${mapaZonas.espalda.intensidad}" /></td>
                    </tr>
                    <tr>
                        <td>Muñeca / Mano</td>
                        <td><input type="checkbox" name="zona_muneca_mano" class="form-check-input" ${mapaZonas.muneca_mano.zona == 'muneca_mano' ? 'checked' : ''} /></td>
                        <td><input type="number" name="intensidad_muneca_mano" min="1" max="5" class="form-control" value="${mapaZonas.muneca_mano.intensidad}" /></td>
                    </tr>
                    <tr>
                        <td>Rodilla</td>
                        <td><input type="checkbox" name="zona_rodilla" class="form-check-input" ${mapaZonas.rodilla.zona == 'rodilla' ? 'checked' : ''} /></td>
                        <td><input type="number" name="intensidad_rodilla" min="1" max="5" class="form-control" value="${mapaZonas.rodilla.intensidad}" /></td>
                    </tr>
                    <tr>
                        <td>Otros</td>
                        <td><input type="checkbox" name="zona_otros" class="form-check-input" ${mapaZonas.otros.zona == 'otros' ? 'checked' : ''} /></td>
                        <td><input type="number" name="intensidad_otros" min="1" max="5" class="form-control" value="${mapaZonas.otros.intensidad}" /></td>
                    </tr>
                </tbody>
            </table>

            <div class="mb-3">
                <label class="form-label">¿Cuánto tiempo tiene el síntoma?</label>
                <input type="text" name="tiempoSintoma" class="form-control" value="${datos.tiempoSintoma}" />
            </div>

            <div class="form-check mb-2">
                <input type="checkbox" name="recibioTratamiento" class="form-check-input" id="tra" ${datos.recibioTratamiento ? 'checked' : ''} />
                <label class="form-check-label" for="tra">¿Ha recibido tratamiento?</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="realizaRestricciones" class="form-check-input" id="res" ${datos.realizaRestricciones ? 'checked' : ''} />
                <label class="form-check-label" for="res">¿Realiza restricciones en su trabajo?</label>
            </div>

            <div class="mb-3">
                <label class="form-label">Observaciones adicionales</label>
                <textarea name="observaciones" class="form-control">${datos.observaciones}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>
