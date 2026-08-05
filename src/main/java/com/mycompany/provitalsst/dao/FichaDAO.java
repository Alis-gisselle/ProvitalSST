/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.Ficha;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
 */
public class FichaDAO {

    public int insertar(Ficha f) {
        String sql = "INSERT INTO Ficha (numeroFicha, fecha, tipoEvaluacion, idPersona) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, f.getNumeroFicha());
            ps.setDate(2, Date.valueOf(f.getFecha()));
            ps.setString(3, f.getTipoEvaluacion());
            ps.setInt(4, f.getIdPersona());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public boolean actualizar(Ficha f) {
        String sql = "UPDATE Ficha SET numeroFicha=?, tipoEvaluacion=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNumeroFicha());
            ps.setString(2, f.getTipoEvaluacion());
            ps.setInt(3, f.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Ficha buscarPorId(int idFicha) {
        String sql = "SELECT * FROM Ficha WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Ficha> listarPorPersona(int idPersona) {
        List<Ficha> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ficha WHERE idPersona = ? ORDER BY fecha DESC";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private Ficha mapear(ResultSet rs) throws SQLException {
        Ficha f = new Ficha();
        f.setIdFicha(rs.getInt("idFicha"));
        f.setNumeroFicha(rs.getString("numeroFicha"));
        f.setFecha(rs.getDate("fecha").toLocalDate());
        f.setTipoEvaluacion(rs.getString("tipoEvaluacion"));
        f.setIdPersona(rs.getInt("idPersona"));
        return f;
    }
    
    public boolean eliminar(int idFicha) {
        String[] tablasHijas = {
            "DatosPersonalesFicha", "AntecedentePersonal", "Habito", "AntecedenteLaboral",
            "PuestoErgonomia", "RiesgoLaboral", "EPP", "TrastornoMusculo", "ZonaAfectada",
            "ExamenFisico", "FirmaFicha"
        };

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);

            for (String tabla : tablasHijas) {
                String sql = "DELETE FROM " + tabla + " WHERE idFicha = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idFicha);
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement ps = con.prepareStatement("DELETE FROM Ficha WHERE idFicha = ?")) {
                ps.setInt(1, idFicha);
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
