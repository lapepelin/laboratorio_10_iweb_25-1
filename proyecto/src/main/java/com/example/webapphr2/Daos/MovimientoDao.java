package com.example.webapphr2.Daos;

import com.example.webapphr2.Beans.Movimiento;
import java.sql.*;
import java.util.ArrayList;

public class MovimientoDao extends DaoBase {

    public ArrayList<Movimiento> listarMovimientos(int idMina){
        ArrayList<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimiento WHERE idMina = ? ORDER BY idMovimiento";
        try(Connection conn = this.getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,idMina);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Movimiento m = new Movimiento();
                    m.setIdMovimiento(rs.getInt("idMovimiento"));
                    m.setX(rs.getInt("x"));
                    m.setY(rs.getInt("y"));
                    m.setEstado(rs.getString("estado"));
                    m.setFecha(rs.getString("fecha"));
                    lista.add(m);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void registrarMovimiento(Movimiento movimiento){
        String sql = "INSERT INTO movimiento(idMina,x,y,estado) VALUES(?,?,?,?)";
        try(Connection conn = this.getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,movimiento.getConfiguracion().getIdMina());
            pstmt.setInt(2,movimiento.getX());
            pstmt.setInt(3,movimiento.getY());
            pstmt.setString(4,movimiento.getEstado());
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}