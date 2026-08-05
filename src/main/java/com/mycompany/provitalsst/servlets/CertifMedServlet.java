/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.CertifMedDAO;
import com.mycompany.provitalsst.dao.MedicoLaboralDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.CertifMed;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.provitalsst.dao.EmpleadoDAO;
import com.mycompany.provitalsst.modelo.Persona;
import com.mycompany.provitalsst.util.GeneradorCertifMedPDF;
import java.io.File;

/**
 *
 * @author alis
 */

@WebServlet(name = "CertifMedServlet", urlPatterns = {"/certifMed"})
public class CertifMedServlet extends HttpServlet {

    private final CertifMedDAO dao = new CertifMedDAO();
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
            response.sendRedirect("certifMed?idPersona=" + idPersona);
            return;
        }

        if ("descargar".equals(accion)) {
            int idCert = Integer.parseInt(request.getParameter("id"));
            generarYDescargarPdf(request, response, idCert);
            return;
        }

        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        request.setAttribute("persona", personaDAO.buscarPorId(idPersona));
        request.setAttribute("listaCertificados", dao.listarPorPersona(idPersona));
        request.setAttribute("listaMedicos", medicoDAO.listarTodos());

        RequestDispatcher rd = request.getRequestDispatcher("certifMedLista.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CertifMed c = new CertifMed();
        c.setTipoEvaluacion(request.getParameter("tipoEvaluacion"));
        c.setAptitud(request.getParameter("aptitud"));
        c.setRecomendacion(request.getParameter("recomendacion"));
        c.setObservaciones(request.getParameter("observaciones"));
        c.setFechaEmision(LocalDate.now());
        c.setIdPersona(Integer.parseInt(request.getParameter("idPersona")));
        c.setIdMedicoLaboral(Integer.parseInt(request.getParameter("idMedicoLaboral")));

        dao.insertar(c);

        response.sendRedirect("certifMed?idPersona=" + c.getIdPersona());
    }
    
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private void generarYDescargarPdf(HttpServletRequest request, HttpServletResponse response, int idCert)
    throws IOException {

        CertifMed cert = dao.buscarPorId(idCert);
        Persona persona = personaDAO.buscarPorId(cert.getIdPersona());
        String tipo = personaDAO.determinarTipo(cert.getIdPersona());
        String cargo = tipo.equals("empleado") ? empleadoDAO.obtenerCargo(cert.getIdPersona()) : "Independiente";

        String rutaPlantilla = getServletContext().getRealPath("/WEB-INF/plantillas/certifMed.pdf");
        String carpetaSalida = getServletContext().getRealPath("/uploads/certificados");
        new File(carpetaSalida).mkdirs();
        String rutaSalida = carpetaSalida + File.separator + "certif_" + idCert + ".pdf";

        try {
            GeneradorCertifMedPDF.generar(rutaPlantilla, rutaSalida, cert, persona, cargo);
            response.sendRedirect("uploads/certificados/certif_" + idCert + ".pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}