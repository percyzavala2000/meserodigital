package com.meserodigital.presentation.admin;


import com.meserodigital.domain.model.Categoria;
import com.meserodigital.domain.model.Producto;

import com.meserodigital.domain.service.CategoriaService;
import com.meserodigital.domain.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin/productos")
public class AdminViewController {

    @Autowired
    private ProductoService productoService;

     @Autowired
    private CategoriaService categoriaService;

  @GetMapping
public String verProductos(Model model) {
    List<Producto> productos = productoService.listarProductos();
    for (Producto producto : productos) {
        System.out.println("Producto: " + producto.getNombre() + ", Estado: " + producto.getEstado()+ 
                           ", Precio: " + producto.getPrecio() + ", Stock: " + producto.getStock() + producto
                           .getCategoria()  );
    }
    for (Producto producto : productos) {
        if (producto.getCategoria() == null) {
            producto.setCategoria(new Categoria());
            producto.getCategoria().setNombre("Sin categoría");
        }
    }
    model.addAttribute("productos", productos);
    return "index";
}

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarProducto(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "agregar_producto";
    }

    @PostMapping(value = "/agregar", consumes = "multipart/form-data")
    @Transactional
    public String guardarProducto(
            @RequestParam String nombre,
            @RequestParam BigDecimal precio,
            @RequestParam Long categoria,
            @RequestParam String descripcion,
            @RequestParam Integer stock,
            @RequestParam MultipartFile imagen) {

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
       producto.setCategoria(categoriaService.getCategoriaById(categoria));
        producto.setEstado(Producto.Estado.DISPONIBLE);

        if (!imagen.isEmpty()) {
            try {
                String nombreArchivo = imagen.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
                producto.setImagen(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productoService.agregarProducto(producto);
        Categoria cat = categoriaService.getCategoriaById(categoria);
System.out.println("Categoria obtenida: " + cat);
producto.setCategoria(cat);

        
        return "redirect:/admin/productos";
    }

    @Autowired
private ProductoWebSocketController productoWebSocketController;

@PostMapping("/cambiarEstado")
@Transactional
public String cambiarEstadoProducto(@RequestParam Long id, @RequestParam String estado) {
    productoService.cambiarEstado(id, Producto.Estado.valueOf(estado));

    Producto productoActualizado = productoService.buscarPorId(id);
    productoWebSocketController.notificarCambioProducto(productoActualizado);

    return "redirect:/admin/productos";
}

   

    // (Opcional) Método para editar un producto
   /*  @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "editar_producto";
    } */

    // (Opcional) Método para eliminar producto
   /*  @PostMapping("/eliminar")
    @Transactional
    public String eliminarProducto(@RequestParam Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos";
    } */

       @GetMapping("/pedidos")
    public String verPedidos() {
        return "pedidos";  // Renderiza pedidos.html
    }

    @GetMapping("/usuarios")
    public String verUsuarios() {
        return "usuarios";  // Renderiza usuarios.html
    }
  
}
