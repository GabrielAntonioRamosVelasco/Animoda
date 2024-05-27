package com.tienda.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.Repositories.UsuarioRespository;


import com.tienda.Entities.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRespository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getPUsuarioById(@PathVariable Long id){
        return usuarioRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRespository.save(usuario);
    }

    @PutMapping
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioNuevo){
            Usuario usuario= usuarioRespository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));

            usuario.setNombre(usuarioNuevo.getNombre());
            usuario.setCorreo(usuarioNuevo.getCorreo());
            return usuarioRespository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Long id){
        Usuario usuario= usuarioRespository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
        usuarioRespository.delete(usuario);
        return "Usuario eliminado correctamente";
    }

}
