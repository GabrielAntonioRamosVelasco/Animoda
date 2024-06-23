package com.tienda.animoda.Controllers;

import com.tienda.animoda.Entities.Carrito;
import com.tienda.animoda.Entities.CarritoItem;
import com.tienda.animoda.Entities.Inventario;
import com.tienda.animoda.Entities.User;
import com.tienda.animoda.Repositories.CarritoRepository;
import com.tienda.animoda.Repositories.InventarioRepository;
import com.tienda.animoda.Repositories.UserRepository;
import com.tienda.animoda.dto.AddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProductToCarrito(@RequestBody AddProductRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(403).body("User not found");
        }
        User user = userOptional.get();

        Inventario product = inventarioRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        Carrito carrito = carritoRepository.findByUser(user);
        if (carrito == null) {
            carrito = new Carrito();
            carrito.setUser(user);
        }

        // Buscar si el producto ya está en el carrito
        Optional<CarritoItem> existingItemOptional = carrito.getItems().stream()
                .filter(item -> item.getProduct().getId_producto().equals(request.getProductId()))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            // Actualizar la cantidad del producto existente
            CarritoItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {
            // Crear un nuevo CarritoItem y añadirlo al carrito
            CarritoItem item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProduct(product);
            item.setQuantity(request.getQuantity());
            carrito.getItems().add(item);
        }

        carritoRepository.save(carrito);

        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/me")
    public ResponseEntity<Carrito> getMyCarrito() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(403).body(null);
        }
        User user = userOptional.get();

        Carrito carrito = carritoRepository.findByUser(user);

        return ResponseEntity.ok(carrito);
    }

    // Otros endpoints según sea necesario
}
