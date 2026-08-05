package com.mycompany.provitalsst.servlets;


import com.mycompany.provitalsst.dao.UsuarioDAO;
import com.mycompany.provitalsst.dao.EmpresaClienteDAO;
import com.mycompany.provitalsst.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuario"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();
    private final EmpresaClienteDAO empresaDAO = new EmpresaClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                request.setAttribute("listaEmpresas", empresaDAO.listarTodos());
                request.getRequestDispatcher("usuarioForm.jsp").forward(request, response);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("usuario", dao.buscarPorId(idEditar));
                request.setAttribute("listaEmpresas", empresaDAO.listarTodos());
                request.getRequestDispatcher("usuarioForm.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("usuario");
                break;
            default:
                List<Usuario> lista = dao.listarTodos();
                request.setAttribute("listaUsuarios", lista);
                request.getRequestDispatcher("usuarioLista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idUsuario");
        String correo = request.getParameter("correo");
        String rol = request.getParameter("rol");
        String idEmpresaParam = request.getParameter("idEmpresaCliente");

        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setRol(rol);
        usuario.setIdEmpresaCliente(rol.equals("rrhh") && idEmpresaParam != null && !idEmpresaParam.isEmpty()
                ? Integer.parseInt(idEmpresaParam) : null);

        if (idParam != null && !idParam.isEmpty()) {
            usuario.setIdUsuario(Integer.parseInt(idParam));
            dao.actualizar(usuario);
        } else {
            usuario.setContrasenia(request.getParameter("contrasenia"));
            dao.insertar(usuario);
        }

        response.sendRedirect("usuario");
    }
}