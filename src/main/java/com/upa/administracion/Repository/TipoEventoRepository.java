package com.upa.administracion.Repository;

import com.upa.administracion.Model.TipoEvento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long>{
      
    @Query(value = "SELECT * FROM tipo_evento te inner join evento e on e.id_tipo_evento = te.id WHERE e.id_usuario = :idUsuario", nativeQuery=true)
        List<TipoEvento> findByUsuario(@Param("idUsuario") Long idUsuario);
}