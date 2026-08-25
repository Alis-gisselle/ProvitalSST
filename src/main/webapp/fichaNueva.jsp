<%-- 
    Document   : fichaNueva
    Created on : 2 ago. 2026, 13:18:51
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ficha - Paso 1</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
</head>
<body>
    <div class="container mt-4" style="max-width: 500px;">
        <h4>Paso 1 de 12: Datos de la Ficha</h4>

        <form action="ficha" method="post">
            <input type="hidden" name="idPersona" value="${idPersona}" />
            <c:if test="${idFichaEditar != null}">
                <input type="hidden" name="idFichaEditar" value="${idFichaEditar}" />
            </c:if>

            <div class="mb-3">
                <label class="form-label">N° de Ficha</label>
                <input type="text" name="numeroFicha" class="form-control" value="${datos.numeroFicha}" required />
            </div>

            <div class="mb-3">
                <label class="form-label">Tipo de Evaluación</label>
                <select name="tipoEvaluacion" class="form-select" required>
                    <option value="admisional" ${datos.tipoEvaluacion == 'admisional' ? 'selected' : ''}>Admisional</option>
                    <option value="periodico" ${datos.tipoEvaluacion == 'periodico' ? 'selected' : ''}>Periódico</option>
                    <option value="reintegro" ${datos.tipoEvaluacion == 'reintegro' ? 'selected' : ''}>Reintegro</option>
                    <option value="egreso" ${datos.tipoEvaluacion == 'egreso' ? 'selected' : ''}>Egreso</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Siguiente →</button>
        </form>
    </div>
</body>
</html>