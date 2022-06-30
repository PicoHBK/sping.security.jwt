package spring.bp.biblioteca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.bp.biblioteca.Exception.ResourceNotFoundException;
import spring.bp.biblioteca.Model.UsuarioModel;
import spring.bp.biblioteca.Repository.UsuarioRepository;
@Service
public class UsuarioServiceImp implements UsuarioService {









    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<UsuarioModel> obtenerUsuarios() {
        
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {

        return usuarioRepository.save(usuario);

    }

    @Override
    public UsuarioModel obtenerUsuario( long id) {
        UsuarioModel usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario " + id,"ID ",id));
        return usuario;
    }

        

   
    
}
