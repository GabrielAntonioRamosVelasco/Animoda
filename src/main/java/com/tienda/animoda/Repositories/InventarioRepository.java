package com.tienda.animoda.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.animoda.Entities.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}
