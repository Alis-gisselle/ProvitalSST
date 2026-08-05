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
import com.mycompany.provitalsst.modelo.Colaborador;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    public boolean insertar(Colaborador c, String contrasenia) {
        String sqlUsuario = "INSERT INTO Usuario (correo, contrasenia, rol, idEmpresaCliente) VALUES (?, ?, 'colaborador', NULL)";
        String sqlPersona = "INSERT INTO Persona (nombre, apellido, ci, fechaNacimiento, categoria) VALUES (?, ?, ?, ?, ?)";
        String sqlColaborador = "INSERT INTO Colaborador (idPersona, idUsuario) VALUES (?, ?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            int idUsuarioGenerado;
            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                psUsuario.setString(1, c.getCorreo());
                psUsuario.setString(2, contrasenia);
                psUsuario.executeUpdate();
                ResultSet rsUsuario = psUsuario.getGeneratedKeys();
                idUsuarioGenerado = rsUsuario.next() ? rsUsuario.getInt(1) : 0;
            }

            int idPersonaGenerado;
            try (PreparedStatement psPersona = con.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, c.getNombre());
                psPersona.setString(2, c.getApellido());
                psPersona.setInt(3, c.getCi());
                psPersona.setDate(4, Date.valueOf(c.getFechaNacimiento()));
                psPersona.setString(5, c.getCategoria());
                psPersona.executeUpdate();
                ResultSet rsPersona = psPersona.getGeneratedKeys();
                idPersonaGenerado = rsPersona.next() ? rsPersona.getInt(1) : 0;
            }

            try (PreparedStatement psColaborador = con.prepareStatement(sqlColaborador)) {
                psColaborador.setInt(1, idPersonaGenerado);
                psColaborador.setInt(2, idUsuarioGenerado);
                psColaborador.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Colaborador c) {
        String sqlPersona = "UPDATE Persona SET nombre=?, apellido=?, ci=?, fechaNacimiento=?, categoria=? WHERE idPersona=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sqlPersona)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getCi());
            ps.setDate(4, Date.valueOf(c.getFechaNacimiento()));
            ps.setString(5, c.getCategoria());
            ps.setInt(6, c.getIdPersona());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idPersona) {
        // Necesita el idUsuario para borrar también el login
        Colaborador c = buscarPorId(idPersona);
        if (c == null) return false;

        String sqlColaborador = "DELETE FROM Colaborador WHERE idPersona=?";
        String sqlPersona = "DELETE FROM Persona WHERE idPersona=?";
        String sqlUsuario = "DELETE FROM Usuario WHERE idUsuario=?";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlColaborador)) {
                ps1.setInt(1, idPersona);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = con.prepareStatement(sqlPersona)) {
                ps2.setInt(1, idPersona);
                ps2.executeUpdate();
            }
            try (PreparedStatement ps3 = con.prepareStatement(sqlUsuario)) {
                ps3.setInt(1, c.getIdUsuario());
                ps3.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Colaborador buscarPorId(int idPersona) {
        String sql = "SELECT p.*, c.idUsuario, u.correo " +
                     "FROM Persona p " +
                     "JOIN Colaborador c ON p.idPersona = c.idPersona " +
                     "JOIN Usuario u ON c.idUsuario = u.idUsuario " +
                     "WHERE p.idPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Colaborador> listarTodos() {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.idUsuario, u.correo " +
                     "FROM Persona p " +
                     "JOIN Colaborador c ON p.idPersona = c.idPersona " +
                     "JOIN Usuario u ON c.idUsuario = u.idUsuario";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Colaborador> buscarPorNombreOCI(String texto) {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.idUsuario, u.correo " +
                     "FROM Persona p " +
                     "JOIN Colaborador c ON p.idPersona = c.idPersona " +
                     "JOIN Usuario u ON c.idUsuario = u.idUsuario " +
                     "WHERE p.nombre LIKE ? OR p.apellido LIKE ? OR p.ci LIKE ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ps.setString(3, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private Colaborador mapear(ResultSet rs) throws SQLException {
        Colaborador c = new Colaborador();
        c.setIdPersona(rs.getInt("idPersona"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setCi(rs.getInt("ci"));
        c.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        c.setCategoria(rs.getString("categoria"));
        c.setIdUsuario(rs.getInt("idUsuario"));
        c.setCorreo(rs.getString("correo"));
        return c;
    }
}
