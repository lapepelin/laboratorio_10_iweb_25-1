package com.example.webapphr2.Beans;

import java.util.List;

public class Tablero {

    public enum EstadoCelda {
        OCULTA,
        REVELADA,
        MARCADA
    }

    private Configuracion configuracion;
    private EstadoCelda[][] estado;
    private boolean[][] bombas;

    public Tablero(Configuracion configuracion, List<Posicion> posicionesBombas) {
        this.configuracion = configuracion;
        int filas = configuracion.getDimMinaX();
        int columnas = configuracion.getDimMinaY();
        estado = new EstadoCelda[filas][columnas];
        bombas = new boolean[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                estado[i][j] = EstadoCelda.OCULTA;
                bombas[i][j] = false;
            }
        }
        for (Posicion p : posicionesBombas) {
            if (!fueraDeRango(p.getCoordenadaX(), p.getCoordenadaY())) {
                bombas[p.getCoordenadaX()][p.getCoordenadaY()] = true;
            }
        }
    }

    public boolean revelar(int x, int y) {
        if (fueraDeRango(x, y) || estado[x][y] != EstadoCelda.OCULTA) {
            return true;
        }
        if (bombas[x][y]) {
            estado[x][y] = EstadoCelda.REVELADA;
            return false;
        } else {
            estado[x][y] = EstadoCelda.REVELADA;
            return true;
        }
    }

    public void marcar(int x, int y) {
        if (!fueraDeRango(x, y) && estado[x][y] == EstadoCelda.OCULTA) {
            estado[x][y] = EstadoCelda.MARCADA;
        }
    }

    public EstadoCelda obtenerEstado(int x, int y) {
        if (fueraDeRango(x, y)) {
            throw new IndexOutOfBoundsException("Coordenadas fuera de rango");
        }
        return estado[x][y];
    }

    public int contarBombasAlrededor(int x, int y) {
        int conteo = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) continue;
                if (!fueraDeRango(i, j) && bombas[i][j]) {
                    conteo++;
                }
            }
        }
        return conteo;
    }

    public boolean haGanado() {
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado[i].length; j++) {
                if (!bombas[i][j] && estado[i][j] != EstadoCelda.REVELADA) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fueraDeRango(int x, int y) {
        return x < 0 || y < 0 || x >= estado.length || y >= estado[0].length;
    }
}