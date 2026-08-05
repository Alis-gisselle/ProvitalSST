/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.ColaboradorDAO;
import com.mycompany.provitalsst.modelo.Colaborador;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ColaboradorServlet", urlPatterns = {"/colaborador"})
public class ColaboradorServlet extends HttpServlet {

    private final ColaboradorDAO dao = new ColaboradorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                mostrarFormulario(request, response, null);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                mostrarFormulario(request, response, dao.buscarPorId(idEditar));
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                listar(request, response);
                break;
            default:
                listar(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPersonaParam = request.getParameter("idPersona");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int ci = Integer.parseInt(request.getParameter("ci"));
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
        String categoria = request.getParameter("categoria");

        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(nombre);
        colaborador.setApellido(apellido);
        colaborador.setCi(ci);
        colaborador.setFechaNacimiento(fechaNacimiento);
        colaborador.setCategoria(categoria);

        if (idPersonaParam != null && !idPersonaParam.isEmpty()) {
            colaborador.setIdPersona(Integer.parseInt(idPersonaParam));
            dao.actualizar(colaborador);
        } else {
            String correo = request.getParameter("correo");
            String contrasenia = request.getParameter("contrasenia");
            colaborador.setCorreo(correo);
            dao.insertar(colaborador, contrasenia);
        }

        listar(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buscar = request.getParameter("buscar");
        List<Colaborador> lista = (buscar != null && !buscar.trim().isEmpty())
                ? dao.buscarPorNombreOCI(buscar)
                : dao.listarTodos();
        request.setAttribute("listaColaboradores", lista);
        request.setAttribute("buscar", buscar);
        RequestDispatcher rd = request.getRequestDispatcher("colaboradorLista.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Colaborador colaborador)
            throws ServletException, IOException {
        request.setAttribute("colaborador", colaborador);
        RequestDispatcher rd = request.getRequestDispatcher("colaboradorForm.jsp");
        rd.forward(request, response);
    }
}