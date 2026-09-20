/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.AntecedenteLaboral;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author alis
 */
public class AntecedenteLaboralDAO {

    public boolean insertar(AntecedenteLaboral a) {
        String sql = "INSERT INTO antecedentelaboral (idFicha, empresaAnterior, puestoAnterior, periodoDesde, periodoHasta) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdFicha());
            ps.setString(2, a.getEmpresaAnterior());
            ps.setString(3, a.getPuestoAnterior());
            if (a.getPeriodoDesde() != null) ps.setDate(4, Date.valueOf(a.getPeriodoDesde())); else ps.setNull(4, Types.DATE);
            if (a.getPeriodoHasta() != null) ps.setDate(5, Date.valueOf(a.getPeriodoHasta())); else ps.setNull(5, Types.DATE);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean actualizar(AntecedenteLaboral a) {
        String sql = "UPDATE antecedentelaboral SET empresaAnterior=?, puestoAnterior=?, periodoDesde=?, periodoHasta=? WHERE idFicha=?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getEmpresaAnterior());
            ps.setString(2, a.getPuestoAnterior());
            if (a.getPeriodoDesde() != null) 
                ps.setDate(3, Date.valueOf(a.getPeriodoDesde())); 
            else 
                ps.setNull(3, Types.DATE);
            if (a.getPeriodoHasta() != null) 
                ps.setDate(4, Date.valueOf(a.getPeriodoHasta())); 
            else 
                ps.setNull(4, Types.DATE);
            ps.setInt(5, a.getIdFicha());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public AntecedenteLaboral buscarPorId(int idFicha) {
        String sql = "SELECT * FROM antecedentelaboral WHERE idFicha = ?";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFicha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AntecedenteLaboral a = new AntecedenteLaboral();
                a.setIdFicha(rs.getInt("idFicha"));
                a.setEmpresaAnterior(rs.getString("empresaAnterior"));
                a.setPuestoAnterior(rs.getString("puestoAnterior"));
                if (rs.getDate("periodoDesde") != null) 
                    a.setPeriodoDesde(rs.getDate("periodoDesde").toLocalDate());
                if (rs.getDate("periodoHasta") != null) 
                    a.setPeriodoHasta(rs.getDate("periodoHasta").toLocalDate());
                return a;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}