<%-- 
    Document   : fichaRiesgoLaboral
    Created on : 2 ago. 2026, 15:26:06
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 7: Riesgos Laborales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4">
        <a href="fichaPuestoErgonomia?idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 7 de 12: Exposición a Riesgos Laborales</h4>

        <form action="fichaRiesgoLaboral" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr><th>Factor de Riesgo</th><th>Expuesto</th><th>Tiempo de Exposición</th></tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Ruido</td>
                        <td><input type="checkbox" name="expuesto_ruido" class="form-check-input" ${mapaRiesgos.ruido.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_ruido" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.ruido.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.ruido.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.ruido.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Vibraciones</td>
                        <td><input type="checkbox" name="expuesto_vibraciones" class="form-check-input" ${mapaRiesgos.vibraciones.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_vibraciones" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.vibraciones.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.vibraciones.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.vibraciones.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Polvo</td>
                        <td><input type="checkbox" name="expuesto_polvo" class="form-check-input" ${mapaRiesgos.polvo.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_polvo" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.polvo.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.polvo.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.polvo.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Estrés Térmico (calor/frío)</td>
                        <td><input type="checkbox" name="expuesto_estres_termico" class="form-check-input" ${mapaRiesgos.estres_termico.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_estres_termico" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.estres_termico.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.estres_termico.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.estres_termico.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Material Biológico</td>
                        <td><input type="checkbox" name="expuesto_material_biologico" class="form-check-input" ${mapaRiesgos.material_biologico.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_material_biologico" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.material_biologico.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.material_biologico.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.material_biologico.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Manipulación de Cargas</td>
                        <td><input type="checkbox" name="expuesto_manipulacion_cargas" class="form-check-input" ${mapaRiesgos.manipulacion_cargas.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_manipulacion_cargas" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.manipulacion_cargas.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.manipulacion_cargas.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.manipulacion_cargas.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Radiación Ionizante</td>
                        <td><input type="checkbox" name="expuesto_radiacion_ionizante" class="form-check-input" ${mapaRiesgos.radiacion_ionizante.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_radiacion_ionizante" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.radiacion_ionizante.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.radiacion_ionizante.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.radiacion_ionizante.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Trabajo en Alturas</td>
                        <td><input type="checkbox" name="expuesto_trabajo_alturas" class="form-check-input" ${mapaRiesgos.trabajo_alturas.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_trabajo_alturas" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.trabajo_alturas.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.trabajo_alturas.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.trabajo_alturas.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Espacios Confinados</td>
                        <td><input type="checkbox" name="expuesto_espacios_confinados" class="form-check-input" ${mapaRiesgos.espacios_confinados.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_espacios_confinados" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.espacios_confinados.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.espacios_confinados.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.espacios_confinados.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Uso Prolongado de Pantallas (PVD)</td>
                        <td><input type="checkbox" name="expuesto_pantallas" class="form-check-input" ${mapaRiesgos.pantallas.expuesto ? 'checked' : ''} /></td>
                        <td>
                            <select name="tiempo_pantallas" class="form-select">
                                <option value="">-</option>
                                <option value="menos_2h" ${mapaRiesgos.pantallas.tiempoExposicion == 'menos_2h' ? 'selected' : ''}>Menos de 2h/día</option>
                                <option value="2_a_4h" ${mapaRiesgos.pantallas.tiempoExposicion == '2_a_4h' ? 'selected' : ''}>2 a 4h/día</option>
                                <option value="mas_4h" ${mapaRiesgos.pantallas.tiempoExposicion == 'mas_4h' ? 'selected' : ''}>Más de 4h/día</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>