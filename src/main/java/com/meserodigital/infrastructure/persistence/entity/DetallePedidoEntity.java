package com.meserodigital.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")

    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    private int cantidad;

    private BigDecimal precioUnitario;

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

    public ProductoEntity getProducto() {
      return producto;
    }

    public void setProducto(ProductoEntity producto) {
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

    // Getters y setters
}
