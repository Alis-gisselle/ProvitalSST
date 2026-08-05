/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.dao.PuestoErgonomiaDAO;
import com.mycompany.provitalsst.modelo.Ficha;
import com.mycompany.provitalsst.modelo.PuestoErgonomia;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
@WebServlet(name = "FichaPuestoErgonomiaServlet", urlPatterns = {"/fichaPuestoErgonomia"})
public class FichaPuestoErgonomiaServlet extends HttpServlet {

    private final PuestoErgonomiaDAO dao = new PuestoErgonomiaDAO();
    private final FichaDAO fichaDAO = new FichaDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));

        Ficha ficha = fichaDAO.buscarPorId(idFicha);
        request.setAttribute("tipoEvaluacion", ficha.getTipoEvaluacion());

        RequestDispatcher rd = request.getRequestDispatcher("fichaPuestoErgonomia.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        PuestoErgonomia p = new PuestoErgonomia();
        p.setIdFicha(idFicha);
        p.setDescripcionGeneral(request.getParameter("descripcionGeneral"));
        p.setTareasPrincipales(request.getParameter("tareasPrincipales"));
        p.setTipoActividad(request.getParameter("tipoActividad"));
        p.setEsfuerzoFisico(request.getParameter("esfuerzoFisico"));
        p.setLevantaCargas(request.getParameter("levantaCargas") != null);

        String peso = request.getParameter("pesoAprox");
        if (peso != null && !peso.isEmpty()) p.setPesoAprox(new BigDecimal(peso));

        dao.insertar(p);

        response.sendRedirect("fichaRiesgoLaboral?idFicha=" + idFicha);
    }
}