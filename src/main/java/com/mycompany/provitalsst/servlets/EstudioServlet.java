/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.EstudioDAO;
import com.mycompany.provitalsst.dao.MedicoLaboralDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.modelo.Estudio;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author alis
 */

@WebServlet(name = "EstudioServlet", urlPatterns = {"/estudio"})
@MultipartConfig(maxFileSize = 10 * 1024 * 1024) // máximo 10 MB por archivo
public class EstudioServlet extends HttpServlet {

    private final EstudioDAO dao = new EstudioDAO();
    private final MedicoLaboralDAO medicoDAO = new MedicoLaboralDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int idEstudio = Integer.parseInt(request.getParameter("id"));
            int idPersona = Integer.parseInt(request.getParameter("idPersona"));

            Estudio estudio = dao.buscarPorId(idEstudio);
        if (estudio != null) {
            String rutaCarpeta = getServletContext().getRealPath("/uploads/estudios");
            File archivo = new File(rutaCarpeta, estudio.getArchivoPdf());
            if (archivo.exists()) {
                archivo.delete();
            }
            dao.eliminar(idEstudio);
        }

        response.sendRedirect("estudio?idPersona=" + idPersona);
        return;
        }

        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        request.setAttribute("persona", personaDAO.buscarPorId(idPersona));
        request.setAttribute("listaEstudios", dao.listarPorPersona(idPersona));
        request.setAttribute("listaMedicos", medicoDAO.listarTodos());

        RequestDispatcher rd = request.getRequestDispatcher("estudioLista.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        int idMedicoLaboral = Integer.parseInt(request.getParameter("idMedicoLaboral"));

        Part archivo = request.getPart("archivoPdf");
        String nombreOriginal = archivo.getSubmittedFileName();
        String nombreArchivo = "estudio_" + idPersona + "_" + System.currentTimeMillis() + ".pdf";

        String rutaCarpeta = getServletContext().getRealPath("/uploads/estudios");
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        archivo.write(rutaCarpeta + File.separator + nombreArchivo);

        Estudio estudio = new Estudio();
        estudio.setFecha(LocalDate.now());
        estudio.setArchivoPdf(nombreArchivo);
        estudio.setIdPersona(idPersona);
        estudio.setIdMedicoLaboral(idMedicoLaboral);

        dao.insertar(estudio);

        response.sendRedirect("estudio?idPersona=" + idPersona);
    }
    
}