package com.tienda.animoda.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference // Referencia inversa para evitar recursividad
    @ManyToOne
   @JoinColumn(name = "carrito_id")
    @JsonIgnoreProperties("items")  // Ignora la lista de items en carrito
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("carritoItems")  // Ignora la propiedad 'carritoItems' en product para evitar la recursión
    private Inventario product;

    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoItem that = (CarritoItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
