package com.upa.administracion.Service;

import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public List<Usuario> getUsuario() {
        List<Usuario> listaUsuarios = usuarioRepo.findAll();
        return listaUsuarios;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public Usuario findUsuario(Long id) {
       Usuario usuarioTemp = usuarioRepo.findById(id).orElse(null);
       return usuarioTemp;
    }
    
    public Optional<Usuario> getByUser(String user){
        return usuarioRepo.findByUser(user);
    }
    
    public boolean existeByUser(String user){
        return usuarioRepo.existsByUser(user);
    }
    
}
