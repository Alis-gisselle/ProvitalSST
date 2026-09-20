/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;


import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.MedicoLaboral;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */

public class MedicoLaboralDAO {

    public boolean insertar(MedicoLaboral m, String contrasenia) {
        String sqlUsuario = "INSERT INTO usuario (correo, contrasenia, rol, idEmpresaCliente) VALUES (?, ?, 'medico', NULL)";
        String sqlMedico = "INSERT INTO medicolaboral (nombre, apellido, especialidad, matricula, idUsuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);//desactivar el guardado automatico

            int idUsuarioGenerado;
            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                psUsuario.setString(1, m.getCorreo());
                psUsuario.setString(2, contrasenia);
                psUsuario.executeUpdate();
                ResultSet rs = psUsuario.getGeneratedKeys();
                idUsuarioGenerado = rs.next() ? rs.getInt(1) : 0;
            }

            try (PreparedStatement psMedico = con.prepareStatement(sqlMedico)) {
                psMedico.setString(1, m.getNombre());
                psMedico.setString(2, m.getApellido());
                psMedico.setString(3, m.getEspecialidad());
                psMedico.setString(4, m.getMatricula());
                psMedico.setInt(5, idUsuarioGenerado);//relaciona el medico con el usuario que se creo
                psMedico.executeUpdate();//ejecuta el insert
            }

            con.commit();// le dice a sql que ahora si guarde definitivamente todo
            return true;//indica que todo salio bien

        } catch (SQLException ex) {// falla la consulta / SQLException significa que ocurrio un error relacionado a la base de datps
            ex.printStackTrace();//muestra el error en la consola
            return false;
        }
    }

    public boolean actualizar(MedicoLaboral m) {
        String sql = "UPDATE medicolaboral SET nombre=?, apellido=?, especialidad=?, matricula=? WHERE idMedicoLaboral=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getApellido());
            ps.setString(3, m.getEspecialidad());
            ps.setString(4, m.getMatricula());
            ps.setInt(5, m.getIdMedicoLaboral());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idMedicoLaboral) {
        MedicoLaboral m = buscarPorId(idMedicoLaboral);
        if (m == null) return false;

        String sqlMedico = "DELETE FROM medicolaboral WHERE idMedicoLaboral=?";
        String sqlUsuario = "DELETE FROM usuario WHERE idUsuario=?";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(sqlMedico)) {
                ps1.setInt(1, idMedicoLaboral);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = con.prepareStatement(sqlUsuario)) {
                ps2.setInt(1, m.getIdUsuario());
                ps2.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public MedicoLaboral buscarPorId(int idMedicoLaboral) {
        String sql = "SELECT m.*, u.correo FROM medicolaboral m JOIN usuario u ON m.idUsuario = u.idUsuario WHERE m.idMedicoLaboral = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMedicoLaboral);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<MedicoLaboral> listarTodos() {
        List<MedicoLaboral> lista = new ArrayList<>();
        String sql = "SELECT m.*, u.correo FROM medicolaboral m JOIN usuario u ON m.idUsuario = u.idUsuario";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private MedicoLaboral mapear(ResultSet rs) throws SQLException {
        MedicoLaboral m = new MedicoLaboral();
        m.setIdMedicoLaboral(rs.getInt("idMedicoLaboral"));
        m.setNombre(rs.getString("nombre"));
        m.setApellido(rs.getString("apellido"));
        m.setEspecialidad(rs.getString("especialidad"));
        m.setMatricula(rs.getString("matricula"));
        m.setIdUsuario(rs.getInt("idUsuario"));
        m.setCorreo(rs.getString("correo"));
        return m;
    }
}