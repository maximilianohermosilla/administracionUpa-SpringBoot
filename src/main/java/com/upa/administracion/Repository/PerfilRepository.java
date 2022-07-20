package com.upa.administracion.Repository;

import com.upa.administracion.Model.Perfil;
import com.upa.administracion.enums.PerfilNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    Optional<Perfil> findByPerfilNombre(PerfilNombre perfilNombre);
    boolean existsByPerfilNombre(PerfilNombre perfilNombre);    
}
