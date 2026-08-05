/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.CertifManipuladorDAO;
import com.mycompany.provitalsst.dao.MedicoLaboralDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.CertifManipulador;
import com.mycompany.provitalsst.modelo.Persona;
import com.mycompany.provitalsst.util.GeneradorCertifManipuladorPDF;
import java.io.File;
import java.io.IOException;
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

@WebServlet(name = "CertifManipuladorServlet", urlPatterns = {"/certifManipulador"})
public class CertifManipuladorServlet extends HttpServlet {

    private final CertifManipuladorDAO dao = new CertifManipuladorDAO();
    private final MedicoLaboralDAO medicoDAO = new MedicoLaboralDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String accion = request.getParameter("accion");

    if ("eliminar".equals(accion)) {
        int id = Integer.parseInt(request.getParameter("id"));
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        dao.eliminar(id);
        response.sendRedirect("certifManipulador?idPersona=" + idPersona);
        return;
    }

    if ("descargar".equals(accion)) {
        int id = Integer.parseInt(request.getParameter("id"));
        generarYDescargarPdf(request, response, id);
        return;
    }

    int idPersona = Integer.parseInt(request.getParameter("idPersona"));
    request.setAttribute("persona", personaDAO.buscarPorId(idPersona));
    request.setAttribute("listaCertificados", dao.listarPorPersona(idPersona));
    request.setAttribute("listaMedicos", medicoDAO.listarTodos());

    RequestDispatcher rd = request.getRequestDispatcher("certifManipuladorLista.jsp");
    rd.forward(request, response);
}

    private void generarYDescargarPdf(HttpServletRequest request, HttpServletResponse response, int idCert)
        throws IOException {

        CertifManipulador cert = dao.buscarPorId(idCert);
        Persona persona = personaDAO.buscarPorId(cert.getIdPersona());
        String tipo = personaDAO.determinarTipo(cert.getIdPersona());
        String nombreEmpresa = tipo.equals("empleado") ? personaDAO.obtenerNombreEmpresa(cert.getIdPersona()) : null;

        String rutaPlantilla = getServletContext().getRealPath("/WEB-INF/plantillas/certifManipulador.pdf");
        String carpetaSalida = getServletContext().getRealPath("/uploads/certificados");
        new File(carpetaSalida).mkdirs();
        String rutaSalida = carpetaSalida + File.separator + "certifManip_" + idCert + ".pdf";

        try {
            GeneradorCertifManipuladorPDF.generar(rutaPlantilla, rutaSalida, cert, persona, nombreEmpresa);
            response.sendRedirect("uploads/certificados/certifManip_" + idCert + ".pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CertifManipulador c = new CertifManipulador();
        c.setAptitud(request.getParameter("aptitud"));
        c.setRecomendaciones(request.getParameter("recomendaciones"));
        c.setFechaEmision(LocalDate.now());
        c.setIdPersona(Integer.parseInt(request.getParameter("idPersona")));
        c.setIdMedicoLaboral(Integer.parseInt(request.getParameter("idMedicoLaboral")));

        dao.insertar(c);

        response.sendRedirect("certifManipulador?idPersona=" + c.getIdPersona());
    }
}