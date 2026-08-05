/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.EmpresaClienteDAO;
import com.mycompany.provitalsst.dao.EmpleadoDAO;
import com.mycompany.provitalsst.modelo.EmpresaCliente;
import com.mycompany.provitalsst.modelo.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmpresaClienteServlet", urlPatterns = {"/empresa"})
public class EmpresaClienteServlet extends HttpServlet {

    private final EmpresaClienteDAO dao = new EmpresaClienteDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                mostrarFormulario(request, response, null);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                EmpresaCliente empresa = dao.buscarPorId(idEditar);
                mostrarFormulario(request, response, empresa);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                listar(request, response);
                break;
            case "empleados":
                mostrarEmpleados(request, response);
                break;
            default:
                listar(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idEmpresaCliente");
        int ruc = Integer.parseInt(request.getParameter("ruc"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");

        if (idParam != null && !idParam.isEmpty()) {
        
            int id = Integer.parseInt(idParam);
            EmpresaCliente empresa = new EmpresaCliente(id, ruc, nombre, direccion);
            dao.actualizar(empresa);
        } else {
        
            EmpresaCliente empresa = new EmpresaCliente(0, ruc, nombre, direccion);
            dao.insertar(empresa);
        }

        listar(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<EmpresaCliente> lista = dao.listarTodos();
        request.setAttribute("listaEmpresas", lista);
        RequestDispatcher rd = request.getRequestDispatcher("empresaLista.jsp");
        rd.forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, EmpresaCliente empresa)
            throws ServletException, IOException {
        request.setAttribute("empresa", empresa);
        RequestDispatcher rd = request.getRequestDispatcher("empresaForm.jsp");
        rd.forward(request, response);
    }

    private void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEmpresa = Integer.parseInt(request.getParameter("id"));
        String buscar = request.getParameter("buscar");

        List<Empleado> lista;
        if (buscar != null && !buscar.trim().isEmpty()) {
            lista = empleadoDAO.buscarPorNombreOCI(idEmpresa, buscar);
        } else {
            lista = empleadoDAO.listarPorEmpresa(idEmpresa);
        }

        EmpresaCliente empresa = dao.buscarPorId(idEmpresa);

        request.setAttribute("empresa", empresa);
        request.setAttribute("listaEmpleados", lista);
        request.setAttribute("buscar", buscar);

        RequestDispatcher rd = request.getRequestDispatcher("empresaDetalle.jsp");
        rd.forward(request, response);
    }
}