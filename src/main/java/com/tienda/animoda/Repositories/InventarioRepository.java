package com.tienda.animoda.Repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tienda.animoda.Entities.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    @Query("SELECT i FROM Inventario i WHERE LOWER(i.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Inventario> findByKeyword(@Param("keyword") String keyword);
}
