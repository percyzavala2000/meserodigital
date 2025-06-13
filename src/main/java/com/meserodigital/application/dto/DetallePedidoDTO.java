package com.meserodigital.application.dto;

import java.math.BigDecimal;

public class DetallePedidoDTO {
    private Long id;
    private Long idProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public Long getIdProducto() {
      return idProducto;
    }
    public void setIdProducto(Long idProducto) {
      this.idProducto = idProducto;
    }
    public int getCantidad() {
      return cantidad;
    }
    public void setCantidad(int cantidad) {
      this.cantidad = cantidad;
    }
    public BigDecimal getPrecioUnitario() {
      return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
      this.precioUnitario = precioUnitario;
    }

   
}


