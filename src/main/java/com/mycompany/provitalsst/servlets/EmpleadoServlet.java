/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.provitalsst.servlets;

import com.mycompany.provitalsst.dao.EmpleadoDAO;
import com.mycompany.provitalsst.modelo.Empleado;
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
@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/empleado"})
public class EmpleadoServlet extends HttpServlet {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "nuevo";

        switch (accion) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                mostrarFormulario(request, response, dao.buscarPorId(idEditar));
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                int idEmpresaVolver = Integer.parseInt(request.getParameter("idEmpresa"));
                dao.eliminar(idEliminar);
                response.sendRedirect("empresa?accion=empleados&id=" + idEmpresaVolver);
                break;
            default:
                mostrarFormulario(request, response, null);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPersonaParam = request.getParameter("idPersona");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int ci = Integer.parseInt(request.getParameter("ci"));
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
        String categoria = request.getParameter("categoria");
        int idEmpresaCliente = Integer.parseInt(request.getParameter("idEmpresaCliente"));
        String cargo = request.getParameter("cargo");

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCi(ci);
        empleado.setFechaNacimiento(fechaNacimiento);
        empleado.setCategoria(categoria);
        empleado.setIdEmpresaCliente(idEmpresaCliente);
        empleado.setCargo(cargo);

        if (idPersonaParam != null && !idPersonaParam.isEmpty()) {
            empleado.setIdPersona(Integer.parseInt(idPersonaParam));
            dao.actualizar(empleado);
        } else {
            dao.insertar(empleado);
        }

        response.sendRedirect("empresa?accion=empleados&id=" + idEmpresaCliente);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Empleado empleado)
            throws ServletException, IOException {

        if (empleado == null) {
            // idEmpresa viene por parámetro cuando es "nuevo", para saber a qué empresa pertenece
            String idEmpresa = request.getParameter("idEmpresa");
            request.setAttribute("idEmpresaNuevo", idEmpresa);
        }

        request.setAttribute("empleado", empleado);
        RequestDispatcher rd = request.getRequestDispatcher("empleadoForm.jsp");
        rd.forward(request, response);
    }
}