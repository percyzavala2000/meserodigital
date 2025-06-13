package com.meserodigital.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orden_cocina")
public class OrdenCocinaEntity {

    public enum Estado {
        PREPARANDO, LISTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    private String tiempoEstimado;

    private LocalDateTime horaInicio;

    private LocalDateTime horaEntrega;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public PedidoEntity getPedido() {
      return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
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

    // Getters y setters
}
