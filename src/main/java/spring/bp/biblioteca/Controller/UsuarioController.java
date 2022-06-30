package spring.bp.biblioteca.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import spring.bp.biblioteca.Exception.ResourceNotFoundException;
import spring.bp.biblioteca.Model.UsuarioModel;
import spring.bp.biblioteca.Service.UsuarioService;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    

    
    @GetMapping("/noadmin")
    public List<UsuarioModel> obtenerUsuarios() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.obtenerUsuarios();
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<UsuarioModel> obtenerUsuariosPorAdmin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioService.obtenerUsuarios();
    }
    
    @PostMapping("/guardar")
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioModel> obtenerUsuario(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }
}
