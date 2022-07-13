package com.upa.administracion.Repository;

import com.upa.administracion.Model.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventoRepository extends JpaRepository<Evento, Long>{  
    
    @Query(value = "SELECT * FROM evento e WHERE e.id_usuario = :idUsuario", nativeQuery=true)
        List<Evento> findByUsuario(@Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT * FROM evento e WHERE e.id_tipo_evento = :idTipoEvento", nativeQuery=true)
        List<Evento> findByTipoEvento(@Param("idTipoEvento") Long idTipoEvento);
        
    //@Query(value = "SELECT * FROM evento e WHERE e.id_usuario = :idUsuario and e.id_tipo_evento = :idTipoEvento", nativeQuery=true)
    @Query(value = "SELECT * FROM evento e WHERE (e.id_usuario = :idUsuario or 1=:idUsuario) and (e.id_tipo_evento = :idTipoEvento or 0=:idTipoEvento)", nativeQuery=true)             
        List<Evento> findByUsuarioAndTipoEvento(@Param("idTipoEvento") Long idTipoEvento, @Param("idUsuario") Long idUsuario);
}