/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.EPPDAO;
import com.mycompany.provitalsst.modelo.EPP;
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
@WebServlet(name = "FichaEppServlet", urlPatterns = {"/fichaEpp"})
public class FichaEppServlet extends HttpServlet {

    private final EPPDAO dao = new EPPDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));

        RequestDispatcher rd = request.getRequestDispatcher("fichaEpp.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        EPP e = new EPP();
        e.setIdFicha(idFicha);
        e.setTapaboca(request.getParameter("tapaboca") != null);
        e.setProteccionAuditiva(request.getParameter("proteccionAuditiva") != null);
        e.setCasco(request.getParameter("casco") != null);
        e.setGafas(request.getParameter("gafas") != null);
        e.setBotas(request.getParameter("botas") != null);
        e.setGuantes(request.getParameter("guantes") != null);
        e.setDelantal(request.getParameter("delantal") != null);
        e.setOtros(request.getParameter("otros"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(e);
        } else {
            dao.insertar(e);
        }

        response.sendRedirect("fichaTrastornoMusculo?idFicha=" + idFicha);
    }
}