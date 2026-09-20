/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

/**
 *
 * @author alis
 */
import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Empleado;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public boolean insertar(Empleado e) {
        String sqlPersona = "INSERT INTO persona (nombre, apellido, ci, fechaNacimiento, categoria) VALUES (?, ?, ?, ?, ?)";
        String sqlEmpleado = "INSERT INTO empleado (idPersona, idEmpresaCliente, cargo) VALUES (?, ?, ?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, e.getNombre());
                psPersona.setString(2, e.getApellido());
                psPersona.setInt(3, e.getCi());
                psPersona.setDate(4, Date.valueOf(e.getFechaNacimiento()));
                psPersona.setString(5, e.getCategoria());
                psPersona.executeUpdate();

                ResultSet rs = psPersona.getGeneratedKeys();
                int idPersonaGenerado = 0;
                if (rs.next()) {
                    idPersonaGenerado = rs.getInt(1);
                }

                try (PreparedStatement psEmpleado = con.prepareStatement(sqlEmpleado)) {
                    psEmpleado.setInt(1, idPersonaGenerado);
                    psEmpleado.setInt(2, e.getIdEmpresaCliente());
                    psEmpleado.setString(3, e.getCargo());
                    psEmpleado.executeUpdate();
                }
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Empleado e) {
        String sqlPersona = "UPDATE persona SET nombre=?, apellido=?, ci=?, fechaNacimiento=?, categoria=? WHERE idPersona=?";
        String sqlEmpleado = "UPDATE empleado SET idEmpresaCliente=?, cargo=? WHERE idPersona=?";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona)) {
                psPersona.setString(1, e.getNombre());
                psPersona.setString(2, e.getApellido());
                psPersona.setInt(3, e.getCi());
                psPersona.setDate(4, Date.valueOf(e.getFechaNacimiento()));
                psPersona.setString(5, e.getCategoria());
                psPersona.setInt(6, e.getIdPersona());
                psPersona.executeUpdate();
            }

            try (PreparedStatement psEmpleado = con.prepareStatement(sqlEmpleado)) {
                psEmpleado.setInt(1, e.getIdEmpresaCliente());
                psEmpleado.setString(2, e.getCargo());
                psEmpleado.setInt(3, e.getIdPersona());
                psEmpleado.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPersona) {
        // Elimina primero Empleado (por la FK), luego Persona
        String sqlEmpleado = "DELETE FROM empleado WHERE idPersona=?";
        String sqlPersona = "DELETE FROM persona WHERE idPersona=?";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psEmpleado = con.prepareStatement(sqlEmpleado)) {
                psEmpleado.setInt(1, idPersona);
                psEmpleado.executeUpdate();
            }
            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona)) {
                psPersona.setInt(1, idPersona);
                psPersona.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Empleado buscarPorId(int idPersona) {
        String sql = "SELECT p.*, e.idEmpresaCliente, e.cargo " +
                     "FROM persona p JOIN empleado e ON p.idPersona = e.idPersona " +
                     "WHERE p.idPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Empleado> listarPorEmpresa(int idEmpresaCliente) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT p.*, e.idEmpresaCliente, e.cargo " +
                     "FROM persona p JOIN empleado e ON p.idPersona = e.idPersona " +
                     "WHERE e.idEmpresaCliente = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresaCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Empleado> buscarPorNombreOCI(int idEmpresaCliente, String texto) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT p.*, e.idEmpresaCliente, e.cargo " +
                     "FROM persona p JOIN empleado e ON p.idPersona = e.idPersona " +
                     "WHERE e.idEmpresaCliente = ? AND (p.nombre LIKE ? OR p.apellido LIKE ? OR p.ci LIKE ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresaCliente);
            ps.setString(2, "%" + texto + "%");
            ps.setString(3, "%" + texto + "%");
            ps.setString(4, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private Empleado mapear(ResultSet rs) throws SQLException {
        Empleado e = new Empleado();
        e.setIdPersona(rs.getInt("idPersona"));
        e.setNombre(rs.getString("nombre"));
        e.setApellido(rs.getString("apellido"));
        e.setCi(rs.getInt("ci"));
        e.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        e.setCategoria(rs.getString("categoria"));
        e.setIdEmpresaCliente(rs.getInt("idEmpresaCliente"));
        e.setCargo(rs.getString("cargo"));
        return e;
    }
    public String obtenerCargo(int idPersona) {
        String sql = "SELECT cargo FROM empleado WHERE idPersona = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("cargo");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
