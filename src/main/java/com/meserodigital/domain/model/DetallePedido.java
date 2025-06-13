package com.meserodigital.domain.model;

import java.math.BigDecimal;

public class DetallePedido {
    private Long id;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
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
    public Producto getProducto() {
      return producto;
    }
    public void setProducto(Producto producto) {
      this.producto = producto;
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
