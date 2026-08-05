/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.Persona;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alis
 */

@WebServlet(name = "PersonaServlet", urlPatterns = {"/persona"})
public class PersonaServlet extends HttpServlet {

    private final PersonaDAO dao = new PersonaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Persona persona = dao.buscarPorId(id);
        String tipo = dao.determinarTipo(id);
        String nombreEmpresa = tipo.equals("empleado") ? dao.obtenerNombreEmpresa(id) : null;

        request.setAttribute("persona", persona);
        request.setAttribute("tipo", tipo);
        request.setAttribute("nombreEmpresa", nombreEmpresa);

        RequestDispatcher rd = request.getRequestDispatcher("personaDetalle.jsp");
        rd.forward(request, response);
    }
}