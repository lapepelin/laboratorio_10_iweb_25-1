package com.example.webapphr2.Beans;

public class Configuracion {

    private int idMina;
    private int dimMinaX;
    private int dimMinaY;
    private int cantBombas;
    private int cantIntentos;
    private int cantIntentosActual;

    public Configuracion() {
    }

    public Configuracion(int idMina) {
        this.idMina = idMina;
    }

    public int getIdMina() {
        return idMina;
    }

    public void setIdMina(int idMina) {
        this.idMina = idMina;
    }

    public int getDimMinaX() {
        return dimMinaX;
    }

    public void setDimMinaX(int dimMinaX) {
        this.dimMinaX = dimMinaX;
    }

    public int getDimMinaY() {
        return dimMinaY;
    }

    public void setDimMinaY(int dimMinaY) {
        this.dimMinaY = dimMinaY;
    }

    public int getCantBombas() {
        return cantBombas;
    }

    public void setCantBombas(int cantBombas) {
        this.cantBombas = cantBombas;
    }

    public int getCantIntentos() {
        return cantIntentos;
    }

    public void setCantIntentos(int cantIntentos) {
        this.cantIntentos = cantIntentos;
    }

    public int getCantIntentosActual() {
        return cantIntentosActual;
    }

    public void setCantIntentosActual(int cantIntentosActual) {
        this.cantIntentosActual = cantIntentosActual;
    }
}