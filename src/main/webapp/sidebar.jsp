<%-- 
    Document   : sidebar
    Created on : 19 sept. 2026, 10:44:00
    Author     : alis
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    :root {
        --verde-principal: #075B42;
        --verde-claro: #E8F5EF;
        --verde-boton: #087F4F;
    }
    .layout-admin { display: flex; min-height: 100vh; }
    .sidebar {
        width: 260px; background: var(--verde-principal); color: white;
        flex-shrink: 0; padding: 24px 0;
    }
    .sidebar-logo { display: flex; align-items: center; gap: 10px; padding: 0 24px 24px; font-size: 20px; font-weight: 800; }
    .sidebar-logo .sst { color: #A9E8C7; }
    .sidebar-nav a {
        display: flex; align-items: center; gap: 12px;
        padding: 13px 24px; color: #d7e9e1; text-decoration: none; font-weight: 500;
    }
    .sidebar-nav a:hover { background: rgba(255,255,255,0.08); color: white; }
    .sidebar-nav a.active { background: rgba(255,255,255,0.15); color: white; border-right: 3px solid white; }
    .contenido-admin { flex: 1; background: #F8FAFC; }
    .topbar-admin {
        display: flex; justify-content: flex-end; align-items: center; gap: 16px;
        padding: 16px 32px; background: white; border-bottom: 1px solid #eee;
    }
</style>

<div class="sidebar">
    <div class="sidebar-logo">
        <i class="bi bi-shield-fill-check fs-4"></i> PROVITAL <span class="sst">SST</span>
    </div>

    <c:if test="${sessionScope.usuarioLogueado.rol == 'admin'}">
        <nav class="sidebar-nav">
            <a href="dashboard" class="${paginaActiva == 'inicio' ? 'active' : ''}"><i class="bi bi-house"></i> Inicio</a>
            <a href="empresa" class="${paginaActiva == 'empresas' ? 'active' : ''}"><i class="bi bi-building"></i> Empresas</a>
            <a href="colaborador" class="${paginaActiva == 'colaboradores' ? 'active' : ''}"><i class="bi bi-people"></i> Colaboradores</a>
            <a href="configuracion" class="${paginaActiva == 'configuracion' ? 'active' : ''}"><i class="bi bi-gear"></i> Configuración</a>
        </nav>
    </c:if>

    <c:if test="${sessionScope.usuarioLogueado.rol == 'colaborador'}">
        <nav class="sidebar-nav">
            <a href="dashboard"><i class="bi bi-house"></i> Mi Información</a>
        </nav>
    </c:if>
</div>