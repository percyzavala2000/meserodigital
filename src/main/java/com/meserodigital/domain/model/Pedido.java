package com.meserodigital.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDateTime fecha;
    private Estado estado;
    private Cliente cliente;
    private List<DetallePedido> detalles;

    public enum Estado {
        PENDIENTE,
        EN_PREPARACION,
        LISTO,
        RECOGIDO
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public LocalDateTime getFecha() {
      return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
      this.fecha = fecha;
    }

    public Estado getEstado() {
      return estado;
    }

    public void setEstado(Estado estado) {
      this.estado = estado;
    }

    public Cliente getCliente() {
      return cliente;
    }

    public void setCliente(Cliente cliente) {
      this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
      return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
      this.detalles = detalles;
    }

    
}

