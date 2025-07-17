package com.meserodigital.application.dto;

public class PedidoProgresoDTO {

    private int tiempoEntrega;      // en minutos
    private boolean completado;     // este lo puedes mantener si quieres
    private String estado;          // 👈 nuevo campo para controlar la lógica del frontend

    // Getters y Setters
    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}