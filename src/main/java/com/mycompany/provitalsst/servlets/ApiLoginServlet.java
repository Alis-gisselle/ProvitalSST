package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.ColaboradorDAO;
import com.mycompany.provitalsst.dao.UsuarioDAO;
import com.mycompany.provitalsst.modelo.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name = "ApiLoginServlet", urlPatterns = {"/api/login"})
public class ApiLoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ColaboradorDAO colaboradorDAO = new ColaboradorDAO();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {

            StringBuilder contenido = new StringBuilder();

            BufferedReader reader = request.getReader();

            String linea;

            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }

            JSONObject entrada =
                    new JSONObject(contenido.toString());

            String correo =
                    entrada.optString("correo", "").trim();

            String contrasenia =
                    entrada.optString("contrasenia", "");

            if (correo.isEmpty() || contrasenia.isEmpty()) {

                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST
                );

                JSONObject respuesta = new JSONObject();

                respuesta.put("exito", false);
                respuesta.put(
                        "mensaje",
                        "Correo y contraseña son obligatorios"
                );

                out.print(respuesta);
                return;
            }

            Usuario usuario =
                    usuarioDAO.autenticar(
                            correo,
                            contrasenia
                    );

            if (usuario == null) {

                response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                );

                JSONObject respuesta = new JSONObject();

                respuesta.put("exito", false);
                respuesta.put(
                        "mensaje",
                        "Correo o contraseña incorrectos"
                );

                out.print(respuesta);
                return;
            }

            JSONObject respuesta = new JSONObject();

            respuesta.put("exito", true);
            respuesta.put(
                    "idUsuario",
                    usuario.getIdUsuario()
            );
            respuesta.put(
                    "correo",
                    usuario.getCorreo()
            );
            respuesta.put(
                    "rol",
                    usuario.getRol()
            );

            /*
             * Si es colaborador,
             * buscamos automáticamente su persona.
             */
            if ("colaborador".equalsIgnoreCase(
                    usuario.getRol())) {

                Integer idPersona =
                        colaboradorDAO
                                .obtenerIdPersonaPorUsuario(
                                        usuario.getIdUsuario()
                                );

                if (idPersona != null) {
                    respuesta.put(
                            "idPersona",
                            idPersona
                    );
                } else {
                    respuesta.put(
                            "idPersona",
                            JSONObject.NULL
                    );
                }

            } else {

                respuesta.put(
                        "idPersona",
                        JSONObject.NULL
                );
            }

            /*
             * Empresa asociada al usuario,
             * si corresponde.
             */
            if (usuario.getIdEmpresaCliente() != null) {

                respuesta.put(
                        "idEmpresaCliente",
                        usuario.getIdEmpresaCliente()
                );

            } else {

                respuesta.put(
                        "idEmpresaCliente",
                        JSONObject.NULL
                );
            }

            response.setStatus(
                    HttpServletResponse.SC_OK
            );

            out.print(respuesta);

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            JSONObject respuesta = new JSONObject();

            respuesta.put("exito", false);
            respuesta.put(
                    "mensaje",
                    "Error interno del servidor"
            );

            out.print(respuesta);
        }
    }
}