/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.provitalsst.dao;

import com.mycompany.provitalsst.conexiones.Conexion;
import com.mycompany.provitalsst.modelo.EmpresaCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alis
**/
public class EmpresaClienteDAO {

    public boolean insertar(EmpresaCliente e) {
        String sql = "INSERT INTO EmpresaCliente (ruc, Nombre, Direccion) VALUES (?, ?, ?)";
        try (Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getRuc());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(EmpresaCliente e) {
        String sql = "UPDATE EmpresaCliente SET ruc=?, Nombre=?, Direccion=? WHERE idEmpresaCliente=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getRuc());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getDireccion());
            ps.setInt(4, e.getIdEmpresaCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM EmpresaCliente WHERE idEmpresaCliente=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public EmpresaCliente buscarPorId(int id) {
        String sql = "SELECT * FROM EmpresaCliente WHERE idEmpresaCliente=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EmpresaCliente(
                    rs.getInt("idEmpresaCliente"),
                    rs.getInt("ruc"),
                    rs.getString("Nombre"),
                    rs.getString("Direccion")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<EmpresaCliente> listarTodos() {
        List<EmpresaCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM EmpresaCliente";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new EmpresaCliente(
                    rs.getInt("idEmpresaCliente"),
                    rs.getInt("ruc"),
                    rs.getString("Nombre"),
                    rs.getString("Direccion")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}