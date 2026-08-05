/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.TrastornoMusculo;
import java.sql.*;

/**
 *
 * @author alis
 */
public class TrastornoMusculoDAO {

    public boolean insertar(TrastornoMusculo t) {
        String sql = "INSERT INTO TrastornoMusculo (idFicha, posturasForzadas, movimientosRepetitivos, ritmoElevado, " +
                     "reposoInsuficiente, posturaPredominante, tiempoSintoma, recibioTratamiento, realizaRestricciones, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getIdFicha());
            ps.setBoolean(2, t.isPosturasForzadas());
            ps.setBoolean(3, t.isMovimientosRepetitivos());
            ps.setBoolean(4, t.isRitmoElevado());
            ps.setBoolean(5, t.isReposoInsuficiente());
            ps.setString(6, t.getPosturaPredominante());
            ps.setString(7, t.getTiempoSintoma());
            ps.setBoolean(8, t.isRecibioTratamiento());
            ps.setBoolean(9, t.isRealizaRestricciones());
            ps.setString(10, t.getObservaciones());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(TrastornoMusculo t) {
        String sql = "UPDATE TrastornoMusculo SET posturasForzadas=?, movimientosRepetitivos=?, ritmoElevado=?, " +
                     "reposoInsuficiente=?, posturaPredominante=?, tiempoSintoma=?, recibioTratamiento=?, realizaRestricciones=?, observaciones=? " +
                     "WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, t.isPosturasForzadas());
            ps.setBoolean(2, t.isMovimientosRepetitivos());
            ps.setBoolean(3, t.isRitmoElevado());
            ps.setBoolean(4, t.isReposoInsuficiente());
            ps.setString(5, t.getPosturaPredominante());
            ps.setString(6, t.getTiempoSintoma());
            ps.setBoolean(7, t.isRecibioTratamiento());
            ps.setBoolean(8, t.isRealizaRestricciones());
            ps.setString(9, t.getObservaciones());
            ps.setInt(10, t.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public TrastornoMusculo buscarPorId(int idFicha) {
        String sql = "SELECT * FROM TrastornoMusculo WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TrastornoMusculo t = new TrastornoMusculo();
                t.setIdFicha(rs.getInt("idFicha"));
                t.setPosturasForzadas(rs.getBoolean("posturasForzadas"));
                t.setMovimientosRepetitivos(rs.getBoolean("movimientosRepetitivos"));
                t.setRitmoElevado(rs.getBoolean("ritmoElevado"));
                t.setReposoInsuficiente(rs.getBoolean("reposoInsuficiente"));
                t.setPosturaPredominante(rs.getString("posturaPredominante"));
                t.setTiempoSintoma(rs.getString("tiempoSintoma"));
                t.setRecibioTratamiento(rs.getBoolean("recibioTratamiento"));
                t.setRealizaRestricciones(rs.getBoolean("realizaRestricciones"));
                t.setObservaciones(rs.getString("observaciones"));
                return t;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
