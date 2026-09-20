<%-- 
    Document   : topbar
    Created on : 19 sept. 2026, 10:44:46
    Author     : alis
--%>

<div class="topbar-admin">
    <i class="bi bi-person-circle fs-5 text-muted"></i>
    <span class="text-muted">${sessionScope.usuarioLogueado.correo}</span>
    <a href="logout" class="btn btn-outline-success btn-sm">
        <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
    </a>
</div>