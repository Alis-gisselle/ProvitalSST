/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AntecedenteLaboralDAO;
import com.mycompany.provitalsst.dao.AntecedentePersonalDAO;
import com.mycompany.provitalsst.dao.DatosPersonalesFichaDAO;
import com.mycompany.provitalsst.dao.EPPDAO;
import com.mycompany.provitalsst.dao.EmpleadoDAO;
import com.mycompany.provitalsst.dao.ExamenFisicoDAO;
import com.mycompany.provitalsst.dao.FichaDAO;
import com.mycompany.provitalsst.dao.FirmaFichaDAO;
import com.mycompany.provitalsst.dao.HabitoDAO;
import com.mycompany.provitalsst.dao.PersonaDAO;
import com.mycompany.provitalsst.dao.PuestoErgonomiaDAO;
import com.mycompany.provitalsst.dao.RiesgoLaboralDAO;
import com.mycompany.provitalsst.dao.TrastornoMusculoDAO;
import com.mycompany.provitalsst.dao.ZonaAfectadaDAO;
import com.mycompany.provitalsst.modelo.Ficha;
import com.mycompany.provitalsst.modelo.FichaCompleta;
import com.mycompany.provitalsst.modelo.Persona;
import com.mycompany.provitalsst.modelo.Usuario;
import com.mycompany.provitalsst.util.GeneradorFichaPDF;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alis
 */
@WebServlet(name = "FichaServlet", urlPatterns = {"/ficha"})
public class FichaServlet extends HttpServlet {

    private final FichaDAO dao = new FichaDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();
    private final DatosPersonalesFichaDAO datosPersonalesDAO = new DatosPersonalesFichaDAO();
    private final AntecedentePersonalDAO antecedentePersonalDAO = new AntecedentePersonalDAO();
    private final HabitoDAO habitoDAO = new HabitoDAO();
    private final AntecedenteLaboralDAO antecedenteLaboralDAO = new AntecedenteLaboralDAO();
    private final PuestoErgonomiaDAO puestoErgonomiaDAO = new PuestoErgonomiaDAO();
    private final RiesgoLaboralDAO riesgoLaboralDAO = new RiesgoLaboralDAO();
    private final EPPDAO eppDAO = new EPPDAO();
    private final TrastornoMusculoDAO trastornoMusculoDAO = new TrastornoMusculoDAO();
    private final ZonaAfectadaDAO zonaAfectadaDAO = new ZonaAfectadaDAO();
    private final ExamenFisicoDAO examenFisicoDAO = new ExamenFisicoDAO();
    private final FirmaFichaDAO firmaFichaDAO = new FirmaFichaDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    private void generarYDescargarPdf(HttpServletRequest request, HttpServletResponse response, int idFicha)
        throws IOException {

        Ficha ficha = dao.buscarPorId(idFicha);
        Persona persona = personaDAO.buscarPorId(ficha.getIdPersona());
        String tipo = personaDAO.determinarTipo(ficha.getIdPersona());

        FichaCompleta d = new FichaCompleta();
        d.setFicha(ficha);
        d.setPersona(persona);
        d.setDatosPersonales(datosPersonalesDAO.buscarPorId(idFicha));
        d.setAntecedentePersonal(antecedentePersonalDAO.buscarPorId(idFicha));
        d.setHabito(habitoDAO.buscarPorId(idFicha));
        d.setAntecedenteLaboral(antecedenteLaboralDAO.buscarPorId(idFicha));
        d.setPuestoErgonomia(puestoErgonomiaDAO.buscarPorId(idFicha));
        d.setRiesgos(riesgoLaboralDAO.listarPorFicha(idFicha));
        d.setEpp(eppDAO.buscarPorId(idFicha));
        d.setTrastornoMusculo(trastornoMusculoDAO.buscarPorId(idFicha));
        d.setZonas(zonaAfectadaDAO.listarPorFicha(idFicha));
        d.setExamenFisico(examenFisicoDAO.buscarPorId(idFicha));
        d.setFirma(firmaFichaDAO.buscarPorId(idFicha));

        if ("empleado".equals(tipo)) {
            d.setCargo(empleadoDAO.obtenerCargo(ficha.getIdPersona()));
            d.setNombreEmpresa(personaDAO.obtenerNombreEmpresa(ficha.getIdPersona()));
        }

        String rutaPlantilla = getServletContext().getRealPath("/WEB-INF/plantillas/ficha.pdf");
        String carpetaSalida = getServletContext().getRealPath("/uploads/fichas");
        new File(carpetaSalida).mkdirs();
        String rutaSalida = carpetaSalida + File.separator + "ficha_" + idFicha + ".pdf";

        try {
            GeneradorFichaPDF.generar(rutaPlantilla, rutaSalida, d);
            response.sendRedirect("uploads/fichas/ficha_" + idFicha + ".pdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        if (usuarioLogueado == null) {
            response.sendRedirect("login");
            return;
        }
        boolean soloLectura = "colaborador".equals(usuarioLogueado.getRol());
        request.setAttribute("soloLectura", soloLectura);

        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            if (soloLectura) {
                response.sendRedirect("ficha?idPersona=" + request.getParameter("idPersona"));
                return;
            }
            int idFicha = Integer.parseInt(request.getParameter("idFicha"));
            int idPersona = Integer.parseInt(request.getParameter("idPersona"));
            dao.eliminar(idFicha);
            response.sendRedirect("ficha?idPersona=" + idPersona);
            return;
        }
      

        if ("nueva".equals(accion)) {
            if (soloLectura){
                response.sendRedirect("ficha?idPersona="+ request.getParameter("idPersona"));
                return;
            }
            int idPersona = Integer.parseInt(request.getParameter("idPersona"));
            request.setAttribute("idPersona", idPersona);
            request.setAttribute("datos", null);
            RequestDispatcher rd = request.getRequestDispatcher("fichaNueva.jsp");
            rd.forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            if (soloLectura){
                response.sendRedirect("ficha?idPersona="+ request.getParameter("idPersona").toString());
                return;
            }
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
        if ("descargar".equals(accion)) {
            int idFicha = Integer.parseInt(request.getParameter("idFicha"));
            generarYDescargarPdf(request, response, idFicha);
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
        
        HttpSession session = request.getSession(false);
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        if (usuarioLogueado == null || "colaborador".equals(usuarioLogueado.getRol())) {
            response.sendRedirect("login");
            return;
        }
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