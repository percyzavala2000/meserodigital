package com.meserodigital.application.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrdenCocinaDTO {
    private Long id;
    private Long idPedido;
    private LocalTime tiempoEstimado;
    private LocalDateTime horaInicio;
    private LocalDateTime horaEntrega;
    private String estado;
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public Long getIdPedido() {
      return idPedido;
    }
    public void setIdPedido(Long idPedido) {
      this.idPedido = idPedido;
    }
    public LocalTime getTiempoEstimado() {
      return tiempoEstimado;
    }
    public void setTiempoEstimado(LocalTime tiempoEstimado) {
      this.tiempoEstimado = tiempoEstimado;
    }
    public LocalDateTime getHoraInicio() {
      return horaInicio;
    }
    public void setHoraInicio(LocalDateTime horaInicio) {
      this.horaInicio = horaInicio;
    }
    public LocalDateTime getHoraEntrega() {
      return horaEntrega;
    }
    public void setHoraEntrega(LocalDateTime horaEntrega) {
      this.horaEntrega = horaEntrega;
    }
    public String getEstado() {
      return estado;
    }
    public void setEstado(String estado) {
      this.estado = estado;
    }

    
}
