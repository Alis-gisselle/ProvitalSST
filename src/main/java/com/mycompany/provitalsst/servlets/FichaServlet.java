/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.Ficha;
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
@WebServlet(name = "FichaServlet", urlPatterns = {"/ficha"})
public class FichaServlet extends HttpServlet {

    private final FichaDAO dao = new FichaDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("nueva".equals(accion)) {
            int idPersona = Integer.parseInt(request.getParameter("idPersona"));
            request.setAttribute("idPersona", idPersona);
            request.setAttribute("datos", null);
            RequestDispatcher rd = request.getRequestDispatcher("fichaNueva.jsp");
            rd.forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            int idFicha = Integer.parseInt(request.getParameter("idFicha"));
            Ficha ficha = dao.buscarPorId(idFicha);
            request.setAttribute("idPersona", ficha.getIdPersona());
            request.setAttribute("idFichaEditar", idFicha);
            request.setAttribute("datos", ficha);
            RequestDispatcher rd = request.getRequestDispatcher("fichaNueva.jsp");
            rd.forward(request, response);
            return;
        }
        if ("eliminar".equals(accion)) {
            int idFicha = Integer.parseInt(request.getParameter("idFicha"));
            int idPersona = Integer.parseInt(request.getParameter("idPersona"));
            dao.eliminar(idFicha);
            response.sendRedirect("ficha?idPersona=" + idPersona);
            return;
        }

        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        request.setAttribute("persona", personaDAO.buscarPorId(idPersona));
        request.setAttribute("listaFichas", dao.listarPorPersona(idPersona));

        RequestDispatcher rd = request.getRequestDispatcher("fichaLista.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idFichaEditarParam = request.getParameter("idFichaEditar");

        Ficha f = new Ficha();
        f.setNumeroFicha(request.getParameter("numeroFicha"));
        f.setTipoEvaluacion(request.getParameter("tipoEvaluacion"));
        f.setIdPersona(Integer.parseInt(request.getParameter("idPersona")));

        int idFicha;
        if (idFichaEditarParam != null && !idFichaEditarParam.isEmpty()) {
            idFicha = Integer.parseInt(idFichaEditarParam);
            f.setIdFicha(idFicha);
            dao.actualizar(f);
        } else {
            f.setFecha(LocalDate.now());
            idFicha = dao.insertar(f);
        }

        response.sendRedirect("fichaDatosPersonales?idFicha=" + idFicha);
    }
}