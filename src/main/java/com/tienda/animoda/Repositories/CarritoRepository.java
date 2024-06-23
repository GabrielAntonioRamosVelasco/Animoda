package com.tienda.animoda.Repositories;

import com.tienda.animoda.Entities.Carrito;
import com.tienda.animoda.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUser(User user);
}
