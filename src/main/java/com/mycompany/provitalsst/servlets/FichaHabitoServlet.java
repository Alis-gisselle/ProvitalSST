/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.dao.HabitoDAO;
import com.mycompany.provitalsst.modelo.Habito;
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
@WebServlet(name = "FichaHabitoServlet", urlPatterns = {"/fichaHabito"})
public class FichaHabitoServlet extends HttpServlet {

    private final HabitoDAO dao = new HabitoDAO();
    private final FichaDAO fichaDAO = new FichaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha)); // null si es la primera vez

        RequestDispatcher rd = request.getRequestDispatcher("fichaHabito.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        Habito h = new Habito();
        h.setIdFicha(idFicha);
        h.setFuma(request.getParameter("fuma") != null);
        h.setConsumeAlcohol(request.getParameter("consumeAlcohol") != null);
        h.setActividadFisica(request.getParameter("actividadFisica") != null);
        h.setSuenoAdecuado(request.getParameter("suenoAdecuado") != null);
        h.setOtrosHabitos(request.getParameter("otrosHabitos") != null);
        h.setObservaciones(request.getParameter("observaciones"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(h);
        } else {
            dao.insertar(h);
        }

        com.mycompany.provitalsst.modelo.Ficha ficha = fichaDAO.buscarPorId(idFicha);
        if ("admisional".equals(ficha.getTipoEvaluacion())) {
            response.sendRedirect("fichaAntecedenteLaboral?idFicha=" + idFicha);
        } else {
            response.sendRedirect("fichaPuestoErgonomia?idFicha=" + idFicha);
        }
    }
}