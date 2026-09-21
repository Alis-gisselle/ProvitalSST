package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.CertifManipuladorDAO;
import com.mycompany.provitalsst.dao.CertifMedDAO;
import com.mycompany.provitalsst.dao.EstudioDAO;
import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.modelo.CertifManipulador;
import com.mycompany.provitalsst.modelo.CertifMed;
import com.mycompany.provitalsst.modelo.Estudio;
import com.mycompany.provitalsst.modelo.Ficha;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Devuelve los historiales que las pantallas Android nativas muestran. */
@WebServlet(name = "ApiHistorialServlet", urlPatterns = {"/api/historial"})
public class ApiHistorialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuarioLogueado") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        int idPersona;
        try { idPersona = Integer.parseInt(request.getParameter("idPersona")); }
        catch (NumberFormatException ex) { response.sendError(HttpServletResponse.SC_BAD_REQUEST); return; }

        String modulo = request.getParameter("modulo");
        response.setContentType("application/json;charset=UTF-8");
        StringBuilder salida = new StringBuilder("{\"items\":[");
        if ("ficha".equals(modulo)) {
            List<Ficha> items = new FichaDAO().listarPorPersona(idPersona);
            for (Ficha f : items) agregar(salida, f.getFecha(), f.getNumeroFicha(), f.getTipoEvaluacion());
        } else if ("estudio".equals(modulo)) {
            List<Estudio> items = new EstudioDAO().listarPorPersona(idPersona);
            for (Estudio e : items) agregar(salida, e.getFecha(), e.getNombreMedico(), e.getArchivoPdf());
        } else if ("certifMed".equals(modulo)) {
            List<CertifMed> items = new CertifMedDAO().listarPorPersona(idPersona);
            for (CertifMed c : items) agregar(salida, c.getFechaEmision(), c.getAptitud(), c.getNombreMedico());
        } else if ("certifManipulador".equals(modulo)) {
            List<CertifManipulador> items = new CertifManipuladorDAO().listarPorPersona(idPersona);
            for (CertifManipulador c : items) agregar(salida, c.getFechaEmision(), c.getAptitud(), c.getNombreMedico());
        } else { response.sendError(HttpServletResponse.SC_BAD_REQUEST); return; }
        if (salida.charAt(salida.length() - 1) == ',') salida.setLength(salida.length() - 1);
        salida.append("]}");
        response.getWriter().write(salida.toString());
    }

    private void agregar(StringBuilder salida, Object fecha, String titulo, String detalle) {
        salida.append("{\"fecha\":\"").append(esc(fecha)).append("\",\"titulo\":\"")
                .append(esc(titulo)).append("\",\"detalle\":\"").append(esc(detalle)).append("\"},");
    }
    private String esc(Object valor) {
        return valor == null ? "" : valor.toString().replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
