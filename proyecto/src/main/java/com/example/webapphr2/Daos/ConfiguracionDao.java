package com.example.webapphr2.Daos;

import com.example.webapphr2.Beans.Configuracion;
import com.example.webapphr2.Beans.Posicion;

import java.sql.*;
import java.util.ArrayList;

public class ConfiguracionDao extends DaoBase {

    public Configuracion obtenerConfiguracionActiva(){
        Configuracion configuracion = null;
        String sql = "SELECT * FROM configuracion ORDER BY idMina DESC LIMIT 1";
        try(Connection conn = this.getConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if(rs.next()){
                configuracion = new Configuracion();
                configuracion.setIdMina(rs.getInt("idMina"));
                configuracion.setDimMinaX(rs.getInt("dimMinaX"));
                configuracion.setDimMinaY(rs.getInt("dimMinaY"));
                configuracion.setCantBombas(rs.getInt("cantBombas"));
                configuracion.setCantIntentos(rs.getInt("cantIntentos"));
                configuracion.setCantIntentosActual(rs.getInt("cantIntentosActual"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configuracion;
    }

    public ArrayList<Posicion> listarBombas(int idMina){
        ArrayList<Posicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM posicionbomba WHERE idMina = ?";
        try(Connection conn = this.getConection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,idMina);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Posicion p = new Posicion();
                    p.setIdBomba(rs.getInt("idBomba"));
                    p.setCoordenadaX(rs.getInt("coordenadaX"));
                    p.setCoordenadaY(rs.getInt("coordenadaY"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}