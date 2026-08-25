<%-- 
    Document   : FichaDatosPersonales
    Created on : 2 ago. 2026, 14:04:05
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 2: Datos Personales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 600px;">
        <a href="ficha?accion=editar&idFicha=${idFicha}" class="btn btn-secondary btn-sm mb-3">← Atrás</a>
        <h4>Paso 2 de 12: Datos Personales</h4>

        <form action="fichaDatosPersonales" method="post">
            <input type="hidden" name="idFicha" value="${idFicha}" />

            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <select name="sexo" class="form-select">
                    <option value="M" ${datos.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                    <option value="F" ${datos.sexo == 'F' ? 'selected' : ''}>Femenino</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Estado Civil</label>
                <input type="text" name="estadoCivil" class="form-control" value="${datos.estadoCivil}" />
            </div>
            <div class="mb-3">
                <label class="form-label">FUM (si aplica)</label>
                <input type="date" name="fum" class="form-control" value="${datos.fum}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Área</label>
                <input type="text" name="area" class="form-control" value="${datos.area}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Antigüedad en el Cargo</label>
                <input type="text" name="antiguedadCargo" class="form-control" value="${datos.antiguedadCargo}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Grado de Formación</label>
                <select name="gradoFormacion" class="form-select">
                    <option value="primaria" ${datos.gradoFormacion == 'primaria' ? 'selected' : ''}>Primaria</option>
                    <option value="secundaria" ${datos.gradoFormacion == 'secundaria' ? 'selected' : ''}>Secundaria</option>
                    <option value="tecnica" ${datos.gradoFormacion == 'tecnica' ? 'selected' : ''}>Técnica</option>
                    <option value="universitaria" ${datos.gradoFormacion == 'universitaria' ? 'selected' : ''}>Universitaria</option>
                    <option value="posgrado" ${datos.gradoFormacion == 'posgrado' ? 'selected' : ''}>Posgrado</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Teléfono</label>
                <input type="text" name="telefono" class="form-control" value="${datos.telefono}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Domicilio</label>
                <input type="text" name="domicilio" class="form-control" value="${datos.domicilio}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Contacto de Emergencia</label>
                <input type="text" name="contactoEmergencia" class="form-control" value="${datos.contactoEmergencia}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Teléfono de Emergencia</label>
                <input type="text" name="telefonoEmergencia" class="form-control" value="${datos.telefonoEmergencia}" />
            </div>
            <div class="mb-3">
                <label class="form-label">N° de Hijos</label>
                <input type="number" name="numHijos" class="form-control" value="${datos.numHijos}" />
            </div>
            <div class="mb-3">
                <label class="form-label">Edades de los Hijos (separadas por coma)</label>
                <input type="text" name="edadesHijos" class="form-control" placeholder="ej: 5, 8, 12" value="${datos.edadesHijos}" />
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>