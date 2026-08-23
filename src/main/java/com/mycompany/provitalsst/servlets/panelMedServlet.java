/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AlertaDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vivobook
 */
@WebServlet(name = "panelMedServlet", urlPatterns = {"/panelMed2"})
public class panelMedServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet panelMedServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet panelMedServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PersonaDAO personaDAO = new PersonaDAO();

        // 1. Obtener y limpiar el término de búsqueda
        String buscar = request.getParameter("buscar");
        if (buscar != null) {
            buscar = buscar.trim();
        }

        List<Persona> listaPacientes;

        // 2. Determinar si se realiza búsqueda o se listan todos
        if (buscar != null && !buscar.isEmpty()) {
            listaPacientes = personaDAO.buscarPacientesGlobal(buscar);
        } else {
            listaPacientes = personaDAO.listarTodosLosPacientesGlobal();
            buscar = ""; // Para evitar enviar 'null' al value del input en el JSP
        }

        // 3. Pasar variables a la vista
        request.setAttribute("listaPacientesGlobal", listaPacientes);
        request.setAttribute("buscar", buscar);
        System.out.println("Cantidad de pacientes: " + listaPacientes.size());
        
        AlertaDAO alertaDAO = new AlertaDAO();
        request.setAttribute("countVencidos", alertaDAO.contarVencidos());
        request.setAttribute("countProximos", alertaDAO.contarProximos());
        request.setAttribute("countSinFicha", alertaDAO.contarSinFicha());
        request.setAttribute("countEstudios", alertaDAO.contarEstudiosPendientes());
        // 4. Redirección interna hacia el JSP
        request.getRequestDispatcher("panelMedico.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
