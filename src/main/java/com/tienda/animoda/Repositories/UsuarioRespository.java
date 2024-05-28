package com.tienda.animoda.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.animoda.Entities.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long>{

    
}
