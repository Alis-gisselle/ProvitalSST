package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ApiPersonaServlet", urlPatterns = {"/api/persona"})
public class ApiPersonaServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession sesion = request.getSession(false);

        if (sesion == null ||
                sesion.getAttribute("usuarioLogueado") == null) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.getWriter().write(
                    "{\"error\":\"Sesión no válida\"}"
            );

            return;
        }

        Usuario usuario =
                (Usuario) sesion.getAttribute("usuarioLogueado");

        int idUsuario = usuario.getIdUsuario();

        String sql =
                "SELECT p.idPersona, p.nombre, p.apellido, p.ci, " +
                "p.fechaNacimiento, p.categoria " +
                "FROM colaborador c " +
                "INNER JOIN persona p ON c.idPersona = p.idPersona " +
                "WHERE c.idUsuario = ?";

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {

                    response.setStatus(
                            HttpServletResponse.SC_NOT_FOUND
                    );

                    response.getWriter().write(
                            "{\"error\":\"No se encontró la persona del colaborador\"}"
                    );

                    return;
                }

                int idPersona = rs.getInt("idPersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int ci = rs.getInt("ci");
                String categoria = rs.getString("categoria");

                String json =
                        "{"
                        + "\"idPersona\":" + idPersona + ","
                        + "\"nombre\":\"" + escaparJson(nombre) + "\","
                        + "\"apellido\":\"" + escaparJson(apellido) + "\","
                        + "\"ci\":\"" + ci + "\","
                        + "\"tipo\":\"colaborador\","
                        + "\"empresa\":null,"
                        + "\"categoria\":\"" + escaparJson(categoria) + "\""
                        + "}";

                response.setStatus(
                        HttpServletResponse.SC_OK
                );

                response.getWriter().write(json);
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"error\":\"Error al consultar los datos de la persona\"}"
            );
        }
    }

    private String escaparJson(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}