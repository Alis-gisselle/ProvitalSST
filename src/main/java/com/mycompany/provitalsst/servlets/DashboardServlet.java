/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AlertaDAO;
import com.mycompany.provitalsst.dao.ColaboradorDAO;
import com.mycompany.provitalsst.modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        request.setAttribute("usuario", usuario);

        String vista;
        switch (usuario.getRol()) {
            case "admin":
                vista = "dashboardAdmin.jsp";
                break;
            case "rrhh":
                RequestDispatcher rd = request.getRequestDispatcher("/rrhh");
                rd.forward(request, response);
                return; // Importante para detener la ejecución aquí
            case "medico":
            RequestDispatcher rdMedico = request.getRequestDispatcher("/panelMed2");
            rdMedico.forward(request, response);
            return; // Importante para detener la ejecución aquí
            case "colaborador":
                ColaboradorDAO colabDAO = new ColaboradorDAO();
                Integer idPersona = colabDAO.obtenerIdPersonaPorUsuario(usuario.getIdUsuario());
                response.sendRedirect("persona?id=" + idPersona);
                return; 
            default:
                 vista = "dashboardAdmin.jsp";
               
        }
        AlertaDAO alertaDAO = new AlertaDAO();
        request.setAttribute("countVencidos", alertaDAO.contarVencidos());
        request.setAttribute("countProximos", alertaDAO.contarProximos());
        request.setAttribute("countSinFicha", alertaDAO.contarSinFicha());
        request.setAttribute("countEstudios", alertaDAO.contarEstudiosPendientes());
        
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }
}