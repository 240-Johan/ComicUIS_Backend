package com.example.comicUis.services;

import java.util.List;
import java.util.Optional;

import com.example.comicUis.entity.Usuario;
import com.example.comicUis.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/buscar")
    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/buscar/{id}")
    public Usuario findById(@PathVariable final Long id) {
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == id) {
                usuario = usuarios.get(i);
                return usuarios.get(i);
            }

        }
        return usuario;
    }



    @PostMapping("/guardar")
    public Usuario saveUsuario(@RequestBody Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @PutMapping("/actualizar")
    public void actualizarUsuario(@RequestBody Usuario usuario /**,@PathVariable final Long id)*/ ){
      //  List<Usuario> usuarios = usuarioRepository.findAll();
      //  for (int i = 0; i < usuarios.size(); i++) {
       //     if (usuarios.get(i).getIdUsuario() == id) {
                actualizar(usuario);
       //     }
       // }

    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteUsuario(@PathVariable("id") int id) {
        Optional<Usuario> usuario;
        usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios.get(0), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);        }
    }

}
