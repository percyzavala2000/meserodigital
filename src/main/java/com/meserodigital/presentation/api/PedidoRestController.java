package com.meserodigital.presentation.api;

import com.meserodigital.application.dto.PedidoDTO;
import com.meserodigital.application.mapper.PedidoMapper;
import com.meserodigital.domain.model.Cliente;
import com.meserodigital.domain.model.DetallePedido;
import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.service.PedidoService;
import com.meserodigital.domain.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {

  @Autowired
  private PedidoService pedidoService;
  @Autowired
  private ProductoService productoService;

  @GetMapping
  public List<Producto> listarProductos() {
    // Retorna la lista de productos disponibles
    // Esto es útil para que el cliente pueda seleccionar productos al crear un
    // pedido
    return productoService.listarProductos();
  }

  @PostMapping
  public Pedido crear(@RequestBody PedidoDTO pedidoDTO) {
    Pedido pedido = PedidoMapper.toEntity(pedidoDTO);

    // Asignar cliente
    Cliente cliente = new Cliente();
    cliente.setId(pedidoDTO.getIdCliente());
    pedido.setCliente(cliente);

    // Asignar detalles del pedido
    List<DetallePedido> detalles = pedidoDTO.getDetalles().stream().map(dto -> {
      DetallePedido d = new DetallePedido();
      d.setCantidad(dto.getCantidad());
      d.setPrecioUnitario(dto.getPrecioUnitario());

      Producto p = new Producto();
      p.setId(dto.getIdProducto());
      d.setProducto(p);

      return d;
    }).collect(Collectors.toList());

    pedido.setDetalles(detalles);

    return pedidoService.crearPedido(pedido);
  }

}
