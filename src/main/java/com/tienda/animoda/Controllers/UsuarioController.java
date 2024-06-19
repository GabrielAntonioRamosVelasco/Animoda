package com.tienda.animoda.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.tienda.animoda.Entities.Usuario;
import com.tienda.animoda.Repositories.UsuarioRespository;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRespository usuarioRepository;
/* 
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Usuario usuario) {
        usuario.setContra(bCryptPasswordEncoder.encode(usuario.getContra()));
        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioOpt.isPresent() && bCryptPasswordEncoder.matches(usuario.getContra(), usuarioOpt.get().getContra())) {
            return "Login exitoso";
        } else {
            return "Correo o contraseña incorrectos";
        }
    }
*/
    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getPUsuarioById(@PathVariable Long id){
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioNuevo){
            Usuario usuario= usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));

            usuario.setNombre(usuarioNuevo.getNombre());
            usuario.setContra(usuarioNuevo.getContra());
            usuario.setCorreo(usuarioNuevo.getCorreo());
            return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Long id){
        Usuario usuario= usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
        usuarioRepository.delete(usuario);
        return "Usuario eliminado correctamente";
    }

}
