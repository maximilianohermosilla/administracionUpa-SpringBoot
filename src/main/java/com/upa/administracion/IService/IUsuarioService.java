package com.upa.administracion.IService;

import com.upa.administracion.Model.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IUsuarioService {
    public List<Usuario> getUsuario();
    public Usuario saveUsuario(Usuario usuario);
    public void deleteUsuario(Long id);
    public Usuario findUsuario(Long id);
}