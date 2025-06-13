package com.meserodigital.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    public enum Estado {
        PENDIENTE, EN_PREPARACION, LISTO, RECOGIDO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedidoEntity> detalles;

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

    public ClienteEntity getCliente() {
      return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
      this.cliente = cliente;
    }

    public List<DetallePedidoEntity> getDetalles() {
      return detalles;
    }

    public void setDetalles(List<DetallePedidoEntity> detalles) {
      this.detalles = detalles;
    }

    // Getters y setters
}
