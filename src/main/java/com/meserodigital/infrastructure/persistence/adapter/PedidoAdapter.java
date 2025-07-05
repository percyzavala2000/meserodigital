package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Cliente;
import com.meserodigital.domain.model.DetallePedido;
import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.PedidoRepository;
import com.meserodigital.infrastructure.persistence.entity.ClienteEntity;
import com.meserodigital.infrastructure.persistence.entity.DetallePedidoEntity;
import com.meserodigital.infrastructure.persistence.entity.PedidoEntity;
import com.meserodigital.infrastructure.persistence.entity.ProductoEntity;
import com.meserodigital.infrastructure.persistence.repository.ClienteJpaRepository;
import com.meserodigital.infrastructure.persistence.repository.OrdenCocinaJpaRepository;
import com.meserodigital.infrastructure.persistence.repository.PedidoJpaRepository;
import com.meserodigital.infrastructure.persistence.repository.ProductoJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PedidoAdapter implements PedidoRepository {
  @Autowired
  private ClienteJpaRepository clienteJpaRepository;

  @Autowired
  private ProductoJpaRepository productoJpaRepository;

  @Autowired
  private PedidoJpaRepository jpaRepository;

  @Autowired
  private OrdenCocinaJpaRepository ordenCocinaJpaRepository;

@Override
public Pedido save(Pedido pedido) {
    PedidoEntity entity = new PedidoEntity();

    if (pedido.getId() != null) {
        entity = jpaRepository.findById(pedido.getId()).orElse(new PedidoEntity());
    }

    entity.setFecha(pedido.getFecha());
    entity.setEstado(PedidoEntity.Estado.valueOf(pedido.getEstado().name()));

    // Asignar cliente si viene con ID
    if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(pedido.getCliente().getId());
        entity.setCliente(clienteEntity);
    }

    // Guardar primero el pedido base para tener el ID
    PedidoEntity savedEntity = jpaRepository.save(entity);

    // Ahora agregar los detalles
    if (pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()) {
        List<DetallePedidoEntity> detalles = new ArrayList<>();

        for (DetallePedido detalle : pedido.getDetalles()) {
            DetallePedidoEntity dEntity = new DetallePedidoEntity();
            dEntity.setCantidad(detalle.getCantidad());
            dEntity.setPrecioUnitario(detalle.getPrecioUnitario());

            // Asociar producto si tiene ID
            if (detalle.getProducto() != null && detalle.getProducto().getId() != null) {
                ProductoEntity productoEntity = new ProductoEntity();
                productoEntity.setId(detalle.getProducto().getId());
                dEntity.setProducto(productoEntity);
            }

            dEntity.setPedido(savedEntity); // Asociar el detalle al pedido
            detalles.add(dEntity);
        }

        savedEntity.setDetalles(detalles);
        savedEntity = jpaRepository.save(savedEntity); // volver a guardar
    }

    // Devolver dominio
    Pedido saved = new Pedido();
    saved.setId(savedEntity.getId());
    saved.setFecha(savedEntity.getFecha());
    saved.setEstado(Pedido.Estado.valueOf(savedEntity.getEstado().name()));
    return saved;
}
 /*  public Pedido save(Pedido pedido) {
    PedidoEntity entity;

    if (pedido.getId() != null) {
      entity = jpaRepository.findById(pedido.getId()).orElse(new PedidoEntity());
    } else {
      entity = new PedidoEntity();
    }

    entity.setId(pedido.getId()); // puede ser null
    entity.setFecha(pedido.getFecha());
    entity.setEstado(PedidoEntity.Estado.valueOf(pedido.getEstado().name()));
    // NO tocamos cliente ni detalles aquí para evitar sobreescribir cosas no
    // seteadas

    entity = jpaRepository.save(entity);

    Pedido saved = new Pedido();
    saved.setId(entity.getId());
    saved.setFecha(entity.getFecha());
    saved.setEstado(Pedido.Estado.valueOf(entity.getEstado().name()));
    return saved;
  } */

  private Pedido mapToDomain(PedidoEntity e) {
    Pedido pedido = new Pedido();
    pedido.setId(e.getId());
    pedido.setFecha(e.getFecha());
    pedido.setEstado(Pedido.Estado.valueOf(e.getEstado().name()));

    // Cliente
    if (e.getCliente() != null) {
      Cliente cliente = new Cliente();
      cliente.setId(e.getCliente().getId());
      cliente.setNombre(e.getCliente().getNombre());
      cliente.setNumMesa(e.getCliente().getNumMesa());
      pedido.setCliente(cliente);
    }

    // Detalles
    if (e.getDetalles() != null) {
      List<DetallePedido> detalles = e.getDetalles().stream().map(d -> {
        DetallePedido detalle = new DetallePedido();
        detalle.setId(d.getId());
        detalle.setCantidad(d.getCantidad());
        detalle.setPrecioUnitario(d.getPrecioUnitario());

        if (d.getProducto() != null) {
          Producto producto = new Producto();
          producto.setId(d.getProducto().getId());
          producto.setNombre(d.getProducto().getNombre());
          detalle.setProducto(producto);
        }

        return detalle;
      }).collect(Collectors.toList());

      pedido.setDetalles(detalles);
    }

    // OrdenCocina
    ordenCocinaJpaRepository.findByPedidoId(e.getId())
        .stream()
        .findFirst()
        .ifPresent(ordenEntity -> {
          OrdenCocina orden = new OrdenCocina();
          orden.setId(ordenEntity.getId());
          orden.setHoraInicio(ordenEntity.getHoraInicio());
          orden.setHoraEntrega(ordenEntity.getHoraEntrega());
          orden.setTiempoEstimado(ordenEntity.getTiempoEstimado());
          orden.setEstado(OrdenCocina.Estado.valueOf(ordenEntity.getEstado().name()));
          orden.setIdPedido(e.getId()); // 👈 se asegura que esté vinculado
          pedido.setOrdenCocina(orden);
        });

    return pedido;
  }

  /*
   * @Override
   * public List<Pedido> findAll() {
   * return jpaRepository.findAll()
   * .stream()
   * .map(this::mapToDomain)
   * .collect(Collectors.toList());
   * }
   */
  @Override
  public List<Pedido> findAll() {
    List<PedidoEntity> entidades = Optional.ofNullable(jpaRepository.findAll()).orElse(List.of());
    return entidades.stream()
        .map(this::mapToDomain)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Pedido> findById(Long id) {
    return jpaRepository.findById(id)
        .map(this::mapToDomain);
  }
}
