/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.DatosPersonalesFichaDAO;
import com.mycompany.provitalsst.modelo.DatosPersonalesFicha;
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
@WebServlet(name = "FichaDatosPersonalesServlet", urlPatterns = {"/fichaDatosPersonales"})
public class FichaDatosPersonalesServlet extends HttpServlet {

    private final DatosPersonalesFichaDAO dao = new DatosPersonalesFichaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));
        RequestDispatcher rd = request.getRequestDispatcher("fichaDatosPersonales.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        DatosPersonalesFicha d = new DatosPersonalesFicha();
        d.setIdFicha(idFicha);
        d.setSexo(request.getParameter("sexo"));
        d.setEstadoCivil(request.getParameter("estadoCivil"));

        String fumParam = request.getParameter("fum");
        if (fumParam != null && !fumParam.isEmpty()) d.setFum(LocalDate.parse(fumParam));

        d.setArea(request.getParameter("area"));
        d.setAntiguedadCargo(request.getParameter("antiguedadCargo"));
        d.setGradoFormacion(request.getParameter("gradoFormacion"));
        d.setTelefono(request.getParameter("telefono"));
        d.setDomicilio(request.getParameter("domicilio"));
        d.setContactoEmergencia(request.getParameter("contactoEmergencia"));
        d.setTelefonoEmergencia(request.getParameter("telefonoEmergencia"));

        String numHijosParam = request.getParameter("numHijos");
        if (numHijosParam != null && !numHijosParam.isEmpty()) d.setNumHijos(Integer.parseInt(numHijosParam));

        d.setEdadesHijos(request.getParameter("edadesHijos"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(d);
        } else {
            dao.insertar(d);
        }

        response.sendRedirect("fichaAntecedentePersonal?idFicha=" + idFicha);
    }
}