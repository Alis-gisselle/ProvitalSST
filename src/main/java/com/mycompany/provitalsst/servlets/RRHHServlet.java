package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.AlertaDAO;
import com.mycompany.provitalsst.dao.EmpleadoDAO;
import com.mycompany.provitalsst.dao.EmpresaClienteDAO;
import com.mycompany.provitalsst.modelo.Empleado;
import com.mycompany.provitalsst.modelo.EmpresaCliente;
import com.mycompany.provitalsst.modelo.Usuario;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RRHHServlet extends HttpServlet {

    private final EmpresaClienteDAO empresaDAO = new EmpresaClienteDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final AlertaDAO alertaDAO = new AlertaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (usuarioLogueado == null) {
            response.sendRedirect("login");
            return;
        }

        int idEmpresa = usuarioLogueado.getIdEmpresaCliente();

        String buscar = request.getParameter("buscar");
        List<Empleado> listaEmpleados;

        if (buscar != null && !buscar.trim().isEmpty()) {
            listaEmpleados = empleadoDAO.buscarPorNombreOCI(idEmpresa, buscar);
        } else {
            listaEmpleados = empleadoDAO.listarPorEmpresa(idEmpresa);
        }

        EmpresaCliente empresa = empresaDAO.buscarPorId(idEmpresa);

        request.setAttribute("empresa", empresa);
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.setAttribute("usuario", usuarioLogueado);
        request.setAttribute("buscar", buscar);
        request.setAttribute("soloLectura", true);

        request.setAttribute("countVencidos", alertaDAO.contarVencidosPorEmpresa(idEmpresa));
        request.setAttribute("countProximos", alertaDAO.contarProximosPorEmpresa(idEmpresa));
        request.setAttribute("countSinFicha", alertaDAO.contarSinFichaPorEmpresa(idEmpresa));
        request.setAttribute("countPendientes", alertaDAO.contarEstudiosPendientesPorEmpresa(idEmpresa));
        request.setAttribute("listaVencidos", alertaDAO.listarVencidosPorEmpresa(idEmpresa));
        request.setAttribute("listaProximos", alertaDAO.listarProximosPorEmpresa(idEmpresa));

        RequestDispatcher rd = request.getRequestDispatcher("RRHH.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}