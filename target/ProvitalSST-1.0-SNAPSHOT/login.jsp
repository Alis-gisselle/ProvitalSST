<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión - Provital SST</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="imagenes/logo-entero.png">
    <style>
        :root {
            --verde-principal: #075B42;
            --verde-claro: #E8F5EF;
            --verde-boton: #087F4F;
            --texto: #172B3A;
        }
        body {
            height: 100vh;
            margin: 0;
            display: flex;
            color: var(--texto);
            overflow: hidden;
            background-image: url("${pageContext.request.contextPath}/Fondo/fondo_login.jpeg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .espacio-izquierdo {
            flex: 0 0 25%;
        }
        .panel-derecho {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }
        .card-login {
            background: white;
            border-radius: 16px;
            padding: 40px;
            width: 420px;
            box-shadow: 0 20px 50px rgba(0,0,0,0.25);
        }
        .icono-check {
            width: 44px; height: 44px; border-radius: 10px;
            background: var(--verde-principal); color: white;
            display: flex; align-items: center; justify-content: center;
            font-size: 22px;
        }
        .input-icono { position: relative; }
        .input-icono i.icono-izq {
            position: absolute; left: 14px; top: 50%; transform: translateY(-50%); color: #888;
        }
        .input-icono input { padding-left: 42px; }
        .toggle-pass {
            position: absolute; right: 14px; top: 50%; transform: translateY(-50%);
            color: #888; cursor: pointer; background: none; border: none;
        }
        .btn-ingresar {
            background-color: var(--verde-boton); border-color: var(--verde-boton); color: white;
            font-weight: 600; padding: 12px;
        }
        .btn-ingresar:hover { background-color: #066a41; border-color: #066a41; color: white; }
    </style>
</head>
<body>
    <div class="espacio-izquierdo"></div>

    <div class="panel-derecho">
        <div class="card-login">
            <div class="d-flex align-items-center gap-3 mb-1">
                <div class="icono-check"><i class="bi bi-shield-check"></i></div>
                <div>
                    <h4 class="fw-bold mb-0">Inicio de Sesión</h4>
                    <small class="text-muted">Ingresa tus credenciales para continuar</small>
                </div>
            </div>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
            <% } %>

            <form action="login" method="post" class="mt-4">
                <div class="mb-3 input-icono">
                    <i class="bi bi-envelope icono-izq"></i>
                    <input type="email" name="correo" class="form-control py-2" placeholder="Correo" required autofocus />
                </div>
                <div class="mb-4 input-icono">
                    <i class="bi bi-lock icono-izq"></i>
                    <input type="password" name="contrasenia" id="contrasenia" class="form-control py-2" placeholder="Contraseña" required />
                    <button type="button" class="toggle-pass" onclick="togglePass()">
                        <i class="bi bi-eye-slash" id="iconoOjo"></i>
                    </button>
                </div>
                <button type="submit" class="btn btn-ingresar w-100">
                    Ingresar <i class="bi bi-arrow-right"></i>
                </button>
            </form>
        </div>
    </div>

    <script>
        function togglePass() {
            const input = document.getElementById('contrasenia');
            const icono = document.getElementById('iconoOjo');
            if (input.type === 'password') {
                input.type = 'text';
                icono.className = 'bi bi-eye';
            } else {
                input.type = 'password';
                icono.className = 'bi bi-eye-slash';
            }
        }
    </script>
</body>
</html>
