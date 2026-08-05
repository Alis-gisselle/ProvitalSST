/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.RiesgoLaboralDAO;
import com.mycompany.provitalsst.modelo.RiesgoLaboral;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "FichaRiesgoLaboralServlet", urlPatterns = {"/fichaRiesgoLaboral"})
public class FichaRiesgoLaboralServlet extends HttpServlet {

    private final RiesgoLaboralDAO dao = new RiesgoLaboralDAO();

    private static final String[] FACTORES = {
        "ruido", "vibraciones", "polvo", "estres_termico", "material_biologico",
        "manipulacion_cargas", "radiacion_ionizante", "trabajo_alturas", "espacios_confinados", "pantallas"
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);

        List<RiesgoLaboral> lista = dao.listarPorFicha(idFicha);
        Map<String, RiesgoLaboral> mapaRiesgos = new HashMap<>();
        for (RiesgoLaboral r : lista) {
            mapaRiesgos.put(r.getFactor(), r);
        }
        request.setAttribute("mapaRiesgos", mapaRiesgos);

        RequestDispatcher rd = request.getRequestDispatcher("fichaRiesgoLaboral.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        dao.eliminarPorFicha(idFicha);
        for (String factor : FACTORES) {
            boolean expuesto = request.getParameter("expuesto_" + factor) != null;
            String tiempo = request.getParameter("tiempo_" + factor);

            RiesgoLaboral r = new RiesgoLaboral();
            r.setIdFicha(idFicha);
            r.setFactor(factor);
            r.setExpuesto(expuesto);
            r.setTiempoExposicion(tiempo);

            dao.insertar(r);
        }

        response.sendRedirect("fichaEpp?idFicha=" + idFicha);
    }
}