package com.tienda.animoda.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.animoda.Entities.Inventario;
import com.tienda.animoda.Repositories.InventarioRepository;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping
    public List<Inventario> getAllProducts() {
        return inventarioRepository.findAll();
    }

    @GetMapping("/{id_producto}")
    public Inventario getProductById(@PathVariable Long id_producto) {
        return inventarioRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id_producto));
    }

    @GetMapping("/search")
    public List<Inventario> searchProducts(@RequestParam String keyword) {
        return inventarioRepository.findByKeyword(keyword);
    }


    @PostMapping
    public Inventario createProduct(@RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @PutMapping("/{id_producto}")
    public Inventario updateProduct(@PathVariable Long id_producto, @RequestBody Inventario productoNuevo) {
        Inventario producto = inventarioRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id_producto));

        producto.setNombre(productoNuevo.getNombre());
        producto.setCantidad(productoNuevo.getCantidad());
        producto.setPrecio(productoNuevo.getPrecio());
        producto.setDescripcion(productoNuevo.getDescripcion());
        producto.setImagen(productoNuevo.getImagen());
        return inventarioRepository.save(producto);
    }

    @DeleteMapping("/{id_producto}")
    public String deleteProduct(@PathVariable Long id_producto) {
        Inventario producto = inventarioRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id_producto));
        inventarioRepository.delete(producto);
        return "Producto eliminado correctamente";
    }
    
}
