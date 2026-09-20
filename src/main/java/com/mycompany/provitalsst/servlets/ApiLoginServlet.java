package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.UsuarioDAO;
import com.mycompany.provitalsst.modelo.Usuario;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Punto de acceso para clientes móviles. La BD continúa protegida en el servidor. */
@WebServlet(name = "ApiLoginServlet", urlPatterns = {"/api/login"})
public class ApiLoginServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");

        Usuario usuario = usuarioDAO.autenticar(
                request.getParameter("correo"), request.getParameter("contrasenia"));
        if (usuario == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"Credenciales inválidas\"}");
            return;
        }

        HttpSession sesion = request.getSession(true);
        sesion.setAttribute("usuarioLogueado", usuario);
        response.getWriter().write("{\"idUsuario\":" + usuario.getIdUsuario()
                + ",\"rol\":\"" + escaparJson(usuario.getRol()) + "\"}");
    }

    private String escaparJson(String valor) {
        return valor == null ? "" : valor.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
