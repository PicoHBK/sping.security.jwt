package spring.bp.biblioteca.Service;

import java.util.List;
import java.util.Optional;

import spring.bp.biblioteca.Model.UsuarioModel;

public interface UsuarioService {
    
    public List<UsuarioModel> obtenerUsuarios();
    public UsuarioModel guardarUsuario(UsuarioModel usuario);
    public UsuarioModel obtenerUsuario(long id);
}
