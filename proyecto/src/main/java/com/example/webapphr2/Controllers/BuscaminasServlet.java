package com.example.webapphr2.Controllers;

import com.example.webapphr2.Beans.Configuracion;
import com.example.webapphr2.Beans.Movimiento;
import com.example.webapphr2.Beans.Posicion;
import com.example.webapphr2.Beans.Tablero;
import com.example.webapphr2.Daos.ConfiguracionDao;
import com.example.webapphr2.Daos.MovimientoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BuscaminasServlet", urlPatterns = {"/buscaminas"})
public class BuscaminasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConfiguracionDao configuracionDao = new ConfiguracionDao();
        MovimientoDao movimientoDao = new MovimientoDao();

        Configuracion configuracion = configuracionDao.obtenerConfiguracionActiva();
        if(configuracion != null){
            ArrayList<Posicion> bombas = configuracionDao.listarBombas(configuracion.getIdMina());
            Tablero tablero = new Tablero(configuracion, bombas);

            ArrayList<Movimiento> movimientos = movimientoDao.listarMovimientos(configuracion.getIdMina());
            for(Movimiento m : movimientos){
                tablero.revelar(m.getX(), m.getY());
            }

            request.setAttribute("configuracion", configuracion);
            request.setAttribute("tablero", tablero);
        }
        request.getRequestDispatcher("page.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xStr = request.getParameter("x");
        String yStr = request.getParameter("y");

        if(xStr != null && yStr != null){
            int x = Integer.parseInt(xStr);
            int y = Integer.parseInt(yStr);
            ConfiguracionDao configuracionDao = new ConfiguracionDao();
            MovimientoDao movimientoDao = new MovimientoDao();
            Configuracion configuracion = configuracionDao.obtenerConfiguracionActiva();
            if(configuracion != null){
                Movimiento movimiento = new Movimiento();
                movimiento.setConfiguracion(configuracion);
                movimiento.setX(x);
                movimiento.setY(y);
                movimiento.setEstado("REVELADA");
                movimientoDao.registrarMovimiento(movimiento);
            }
        }
        response.sendRedirect("buscaminas");
    }
}