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
import com.mycompany.provitalsst.modelo.DatosPersonalesFicha;
import java.sql.*;

public class DatosPersonalesFichaDAO {

    public boolean insertar(DatosPersonalesFicha d) {
        String sql = "INSERT INTO DatosPersonalesFicha (idFicha, sexo, estadoCivil, fum, area, antiguedadCargo, " +
                     "gradoFormacion, telefono, domicilio, contactoEmergencia, telefonoEmergencia, numHijos, edadesHijos) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getIdFicha());
            ps.setString(2, d.getSexo());
            ps.setString(3, d.getEstadoCivil());
            if (d.getFum() != null) ps.setDate(4, Date.valueOf(d.getFum())); else ps.setNull(4, Types.DATE);
            ps.setString(5, d.getArea());
            ps.setString(6, d.getAntiguedadCargo());
            ps.setString(7, d.getGradoFormacion());
            ps.setString(8, d.getTelefono());
            ps.setString(9, d.getDomicilio());
            ps.setString(10, d.getContactoEmergencia());
            ps.setString(11, d.getTelefonoEmergencia());
            if (d.getNumHijos() != null) ps.setInt(12, d.getNumHijos()); else ps.setNull(12, Types.INTEGER);
            ps.setString(13, d.getEdadesHijos());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean actualizar(DatosPersonalesFicha d) {
        String sql = "UPDATE DatosPersonalesFicha SET sexo=?, estadoCivil=?, fum=?, area=?, antiguedadCargo=?, " +
                 "gradoFormacion=?, telefono=?, domicilio=?, contactoEmergencia=?, telefonoEmergencia=?, numHijos=?, edadesHijos=? " +
                 "WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getSexo());
            ps.setString(2, d.getEstadoCivil());
            if (d.getFum() != null) 
                ps.setDate(3, Date.valueOf(d.getFum())); 
            else ps.setNull(3, Types.DATE);
            ps.setString(4, d.getArea());
            ps.setString(5, d.getAntiguedadCargo());
            ps.setString(6, d.getGradoFormacion());
            ps.setString(7, d.getTelefono());
            ps.setString(8, d.getDomicilio());
            ps.setString(9, d.getContactoEmergencia());
            ps.setString(10, d.getTelefonoEmergencia());
            if (d.getNumHijos() != null) 
                ps.setInt(11, d.getNumHijos()); 
            else ps.setNull(11, Types.INTEGER);
            ps.setString(12, d.getEdadesHijos());
            ps.setInt(13, d.getIdFicha());
            return ps.executeUpdate() > 0;
        }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public DatosPersonalesFicha buscarPorId(int idFicha) {
        String sql = "SELECT * FROM DatosPersonalesFicha WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DatosPersonalesFicha d = new DatosPersonalesFicha();
                d.setIdFicha(rs.getInt("idFicha"));
                d.setSexo(rs.getString("sexo"));
                d.setEstadoCivil(rs.getString("estadoCivil"));
            if (rs.getDate("fum") != null) d.setFum(rs.getDate("fum").toLocalDate());
                d.setArea(rs.getString("area"));
                d.setAntiguedadCargo(rs.getString("antiguedadCargo"));
                d.setGradoFormacion(rs.getString("gradoFormacion"));
                d.setTelefono(rs.getString("telefono"));
                d.setDomicilio(rs.getString("domicilio"));
                d.setContactoEmergencia(rs.getString("contactoEmergencia"));
                d.setTelefonoEmergencia(rs.getString("telefonoEmergencia"));
                d.setNumHijos(rs.getObject("numHijos") != null ? rs.getInt("numHijos") : null);
                d.setEdadesHijos(rs.getString("edadesHijos"));
                return d;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}