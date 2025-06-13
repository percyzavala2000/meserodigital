package com.meserodigital.domain.model;

import java.time.LocalDateTime;

public class OrdenCocina {
    private Long id;
    private Pedido pedido;
    private String tiempoEstimado;  // Formato HH:mm:ss
    private LocalDateTime horaInicio;
    private LocalDateTime horaEntrega;
    private Estado estado;

    public enum Estado {
        PREPARANDO,
        LISTO
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Pedido getPedido() {
      return pedido;
    }

    public void setPedido(Pedido pedido) {
      this.pedido = pedido;
    }

    public String getTiempoEstimado() {
      return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
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

    public Estado getEstado() {
      return estado;
    }

    public void setEstado(Estado estado) {
      this.estado = estado;
    }

}
