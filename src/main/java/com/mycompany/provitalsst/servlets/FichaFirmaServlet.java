/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.dao.FirmaFichaDAO;
import com.mycompany.provitalsst.modelo.Ficha;
import com.mycompany.provitalsst.modelo.FirmaFicha;
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
@WebServlet(name = "FichaFirmaServlet", urlPatterns = {"/fichaFirma"})
public class FichaFirmaServlet extends HttpServlet {

    private final FirmaFichaDAO dao = new FirmaFichaDAO();
    private final FichaDAO fichaDAO = new FichaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));

        RequestDispatcher rd = request.getRequestDispatcher("fichaFirma.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        FirmaFicha f = new FirmaFicha();
        f.setIdFicha(idFicha);
        f.setCiTrabajador(request.getParameter("ciTrabajador"));
        f.setFechaFirmaTrabajador(LocalDate.now());
        f.setCiTecnico(request.getParameter("ciTecnico"));
        f.setFechaFirmaTecnico(LocalDate.now());

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(f);
        } else {
            dao.insertar(f);
        }

        Ficha ficha = fichaDAO.buscarPorId(idFicha);
        response.sendRedirect("ficha?idPersona=" + ficha.getIdPersona());
    }
}