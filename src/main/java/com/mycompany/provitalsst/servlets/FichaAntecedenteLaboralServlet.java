/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AntecedenteLaboralDAO;
import com.mycompany.provitalsst.modelo.AntecedenteLaboral;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(name = "FichaAntecedenteLaboralServlet", urlPatterns = {"/fichaAntecedenteLaboral"})
public class FichaAntecedenteLaboralServlet extends HttpServlet {

    private final AntecedenteLaboralDAO dao = new AntecedenteLaboralDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));
        RequestDispatcher rd = request.getRequestDispatcher("fichaAntecedenteLaboral.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        AntecedenteLaboral a = new AntecedenteLaboral();
        a.setIdFicha(idFicha);
        a.setEmpresaAnterior(request.getParameter("empresaAnterior"));
        a.setPuestoAnterior(request.getParameter("puestoAnterior"));

        String desde = request.getParameter("periodoDesde");
        if (desde != null && !desde.isEmpty()) a.setPeriodoDesde(LocalDate.parse(desde));

        String hasta = request.getParameter("periodoHasta");
        if (hasta != null && !hasta.isEmpty()) a.setPeriodoHasta(LocalDate.parse(hasta));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(a); 
        } else { dao.insertar(a); }

        response.sendRedirect("fichaPuestoErgonomia?idFicha=" + idFicha);
    }
}