/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.ExamenFisicoDAO;
import com.mycompany.provitalsst.modelo.ExamenFisico;
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
@WebServlet(name = "FichaExamenFisicoServlet", urlPatterns = {"/fichaExamenFisico"})
public class FichaExamenFisicoServlet extends HttpServlet {

    private final ExamenFisicoDAO dao = new ExamenFisicoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));

        RequestDispatcher rd = request.getRequestDispatcher("fichaExamenFisico.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        ExamenFisico e = new ExamenFisico();
        e.setIdFicha(idFicha);

        String peso = request.getParameter("peso");
        if (peso != null && !peso.isEmpty()) e.setPeso(new BigDecimal(peso));

        String estatura = request.getParameter("estatura");
        if (estatura != null && !estatura.isEmpty()) e.setEstatura(new BigDecimal(estatura));

        e.setPresionArterial(request.getParameter("presionArterial"));

        String frecuencia = request.getParameter("frecuenciaCardiaca");
        if (frecuencia != null && !frecuencia.isEmpty()) e.setFrecuenciaCardiaca(Integer.parseInt(frecuencia));

        e.setAgudezaVisualDerecho(request.getParameter("agudezaVisualDerecho"));
        e.setAgudezaVisualIzquierdo(request.getParameter("agudezaVisualIzquierdo"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(e);
        } else {
            dao.insertar(e);
        }

        response.sendRedirect("fichaFirma?idFicha=" + idFicha);
    }
}