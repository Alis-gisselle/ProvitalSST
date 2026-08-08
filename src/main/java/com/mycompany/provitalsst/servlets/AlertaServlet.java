/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AlertaDAO;
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
@WebServlet(name = "AlertaServlet", urlPatterns = {"/alertas"})
public class AlertaServlet extends HttpServlet {

    private final AlertaDAO dao = new AlertaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        request.setAttribute("tipo", tipo);

        if ("vencidos".equals(tipo)) {
            request.setAttribute("listaCertificados", dao.listarVencidos());
        } else if ("proximos".equals(tipo)) {
            request.setAttribute("listaCertificados", dao.listarProximos());
        } else if ("sinFicha".equals(tipo)) {
            request.setAttribute("listaPersonas", dao.listarSinFicha());
        } else if ("estudios".equals(tipo)) {
            request.setAttribute("listaPersonas", dao.listarEstudiosPendientes());
        } else {
            request.setAttribute("listaCertificados", dao.listarVencidos());
        }

        RequestDispatcher rd = request.getRequestDispatcher("alertaLista.jsp");
        rd.forward(request, response);
    }
}