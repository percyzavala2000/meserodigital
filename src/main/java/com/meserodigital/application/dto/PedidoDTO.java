package com.meserodigital.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private Long idCliente;
    private List<DetallePedidoDTO> detalles;
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
    public String getEstado() {
      return estado;
    }
    public void setEstado(String estado) {
      this.estado = estado;
    }
    public Long getIdCliente() {
      return idCliente;
    }
    public void setIdCliente(Long idCliente) {
      this.idCliente = idCliente;
    }
    public List<DetallePedidoDTO> getDetalles() {
      return detalles;
    }
    public void setDetalles(List<DetallePedidoDTO> detalles) {
      this.detalles = detalles;
    }

    // Getters y setters
}
