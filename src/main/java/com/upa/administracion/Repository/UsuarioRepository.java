package com.upa.administracion.Repository;

import com.upa.administracion.Model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByUser(String user);
    boolean existsByUser(String user);
    boolean existsByName(String name);
}