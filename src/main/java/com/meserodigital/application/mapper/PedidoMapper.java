package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.PedidoDTO;
import com.meserodigital.application.dto.DetallePedidoDTO;
import com.meserodigital.domain.model.Cliente;
import com.meserodigital.domain.model.DetallePedido;
import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.Producto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



public class PedidoMapper {

  public static PedidoDTO toDTO(Pedido pedido) {
    PedidoDTO dto = new PedidoDTO();
    dto.setId(pedido.getId());
    dto.setFecha(pedido.getFecha());
    dto.setEstado(pedido.getEstado().name());
    dto.setIdCliente(pedido.getCliente() != null ? pedido.getCliente().getId() : null);

      if (pedido.getDetalles() != null) {
        List<DetallePedidoDTO> detalles = pedido.getDetalles().stream().map(d -> {
            DetallePedidoDTO detalleDTO = new DetallePedidoDTO();
            detalleDTO.setIdProducto(d.getProducto().getId());
            detalleDTO.setCantidad(d.getCantidad());
            detalleDTO.setPrecioUnitario(d.getPrecioUnitario());
            return detalleDTO;
        }).collect(Collectors.toList());

        dto.setDetalles(detalles);
    }

        return dto;
  }

  public static Pedido toEntity(PedidoDTO dto) {
    Pedido p = new Pedido();
    p.setId(dto.getId());
    /*
     * p.setFecha(dto.getFecha());
     * p.setEstado(Pedido.Estado.valueOf(dto.getEstado()));
     */
    p.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now());

    // Si el estado es null, asignamos PENDIENTE por defecto
    if (dto.getEstado() != null) {
      p.setEstado(Pedido.Estado.valueOf(dto.getEstado()));
    } else {
      p.setEstado(Pedido.Estado.PENDIENTE);
    }
    // Cliente y detalles se asignan aparte
     Cliente cliente = new Cliente();
        cliente.setId(dto.getIdCliente());
        p.setCliente(cliente);

        if (dto.getDetalles() != null) {
            List<DetallePedido> detalles = dto.getDetalles().stream().map(d -> {
                DetallePedido detalle = new DetallePedido();
                Producto producto = new Producto();
                producto.setId(d.getIdProducto());
                detalle.setProducto(producto);
                detalle.setCantidad(d.getCantidad());
                detalle.setPrecioUnitario(d.getPrecioUnitario());
                return detalle;
            }).collect(Collectors.toList());
            p.setDetalles(detalles);
        }
     
     
    return p;

  }
}
