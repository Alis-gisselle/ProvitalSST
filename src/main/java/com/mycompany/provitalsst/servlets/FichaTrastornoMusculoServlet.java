/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.TrastornoMusculoDAO;
import com.mycompany.provitalsst.dao.ZonaAfectadaDAO;
import com.mycompany.provitalsst.modelo.TrastornoMusculo;
import com.mycompany.provitalsst.modelo.ZonaAfectada;
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
@WebServlet(name = "FichaTrastornoMusculoServlet", urlPatterns = {"/fichaTrastornoMusculo"})
public class FichaTrastornoMusculoServlet extends HttpServlet {

    private final TrastornoMusculoDAO dao = new TrastornoMusculoDAO();
    private final ZonaAfectadaDAO zonaDAO = new ZonaAfectadaDAO();

    private static final String[] ZONAS = {"cuello", "hombro", "espalda", "muneca_mano", "rodilla", "otros"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));
        request.setAttribute("idFicha", idFicha);
        request.setAttribute("datos", dao.buscarPorId(idFicha));

        List<ZonaAfectada> lista = zonaDAO.listarPorFicha(idFicha);
        Map<String, ZonaAfectada> mapaZonas = new HashMap<>();
        for (ZonaAfectada z : lista) {
            mapaZonas.put(z.getZona(), z);
        }
        request.setAttribute("mapaZonas", mapaZonas);

        RequestDispatcher rd = request.getRequestDispatcher("fichaTrastornoMusculo.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idFicha = Integer.parseInt(request.getParameter("idFicha"));

        TrastornoMusculo t = new TrastornoMusculo();
        t.setIdFicha(idFicha);
        t.setPosturasForzadas(request.getParameter("posturasForzadas") != null);
        t.setMovimientosRepetitivos(request.getParameter("movimientosRepetitivos") != null);
        t.setRitmoElevado(request.getParameter("ritmoElevado") != null);
        t.setReposoInsuficiente(request.getParameter("reposoInsuficiente") != null);
        t.setPosturaPredominante(request.getParameter("posturaPredominante"));
        t.setTiempoSintoma(request.getParameter("tiempoSintoma"));
        t.setRecibioTratamiento(request.getParameter("recibioTratamiento") != null);
        t.setRealizaRestricciones(request.getParameter("realizaRestricciones") != null);
        t.setObservaciones(request.getParameter("observaciones"));

        if (dao.buscarPorId(idFicha) != null) {
            dao.actualizar(t);
        } else {
            dao.insertar(t);
        }

        // Zonas afectadas: borra todas las anteriores y reinserta las marcadas ahora
        zonaDAO.eliminarPorFicha(idFicha);
        for (String zona : ZONAS) {
            String marcada = request.getParameter("zona_" + zona);
            if (marcada != null) {
                String intensidadParam = request.getParameter("intensidad_" + zona);
                ZonaAfectada z = new ZonaAfectada();
                z.setIdFicha(idFicha);
                z.setZona(zona);
                if (intensidadParam != null && !intensidadParam.isEmpty()) {
                    z.setIntensidad(Integer.parseInt(intensidadParam));
                }
                zonaDAO.insertar(z);
            }
        }

        response.sendRedirect("fichaExamenFisico?idFicha=" + idFicha);
    }
}