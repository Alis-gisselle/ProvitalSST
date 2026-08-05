/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AntecedentePersonalDAO;
import com.mycompany.provitalsst.modelo.AntecedentePersonal;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FichaAntecedentePersonalServlet", urlPatterns = {"/fichaAntecedentePersonal"})
public class FichaAntecedentePersonalServlet extends HttpServlet {

    private final AntecedentePersonalDAO dao = new AntecedentePersonalDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));
        RequestDispatcher rd = request.getRequestDispatcher("fichaAntecedentePersonal.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        AntecedentePersonal a = new AntecedentePersonal();
        a.setIdFicha(idFicha);
        a.setEnfermedades(request.getParameter("enfermedades") != null);
        a.setCirugias(request.getParameter("cirugias") != null);
        a.setMedicamentos(request.getParameter("medicamentos") != null);
        a.setAlergias(request.getParameter("alergias") != null);
        a.setOtrosRelevantes(request.getParameter("otrosRelevantes") != null);
        a.setObservaciones(request.getParameter("observaciones"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(a); 
        } else { dao.insertar(a); }

        response.sendRedirect("fichaHabito?idFicha=" + idFicha);
    }
}