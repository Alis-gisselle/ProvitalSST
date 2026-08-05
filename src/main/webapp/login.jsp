<%-- 
    Document   : login
    Created on : 1 ago. 2026, 14:00:26
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <title>Formulario Login</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
  </head>
  <body>
     <form action="login" method="POST">
    <section class="form-login">
      <h5>Inicio de Sesión</h5>
      <input class="controls" type="email" name="correo" placeholder="Correo">
      <input class="controls" type="password" name="contrasenia" placeholder="Contraseña">
      <input class="buttons" type="submit" value="Ingresar">
    </section>
    </form>
  </body>
</html>