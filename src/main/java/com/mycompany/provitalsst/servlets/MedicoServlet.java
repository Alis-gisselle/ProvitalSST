/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.MedicoLaboralDAO;
import com.mycompany.provitalsst.modelo.MedicoLaboral;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MedicoServlet", urlPatterns = {"/medico"})
public class MedicoServlet extends HttpServlet {

    private final MedicoLaboralDAO dao = new MedicoLaboralDAO();

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

        String idParam = request.getParameter("idMedicoLaboral");
        MedicoLaboral medico = new MedicoLaboral();
        medico.setNombre(request.getParameter("nombre"));
        medico.setApellido(request.getParameter("apellido"));
        medico.setEspecialidad(request.getParameter("especialidad"));
        medico.setMatricula(request.getParameter("matricula"));

        if (idParam != null && !idParam.isEmpty()) {
            medico.setIdMedicoLaboral(Integer.parseInt(idParam));
            dao.actualizar(medico);
        } else {
            medico.setCorreo(request.getParameter("correo"));
            String contrasenia = request.getParameter("contrasenia");
            dao.insertar(medico, contrasenia);
        }

        listar(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MedicoLaboral> lista = dao.listarTodos();
        request.setAttribute("listaMedicos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("medicoLista.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, MedicoLaboral medico)
            throws ServletException, IOException {
        request.setAttribute("medico", medico);
        RequestDispatcher rd = request.getRequestDispatcher("medicoForm.jsp");
        rd.forward(request, response);
    }
}