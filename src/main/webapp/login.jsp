<%-- 
    Document   : login
    Created on : 1 ago. 2026, 14:00:26
    Author     : alis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
        <!-- Configuración PWA -->
    <link rel="manifest" href="manifest.json">
    <meta name="theme-color" content="#0056b3">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>Inicio de Sesión</title>
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
      <script>
        if ('serviceWorker' in navigator) {
          window.addEventListener('load', () => {
            navigator.serviceWorker.register('sw.js')
              .then(reg => console.log('Service Worker registrado con éxito:', reg.scope))
              .catch(err => console.error('Error al registrar Service Worker:', err));
          });
        }
      </script>
      
  </body>
</html>