package com.upa.administracion.Repository;

import com.upa.administracion.Model.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{  
    
    @Query(value = "SELECT * FROM solicitud e WHERE e.id_usuario = :idUsuario", nativeQuery=true)
        List<Solicitud> findByUsuario(@Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT * FROM solicitud e WHERE e.id_tipo_evento = :idTipoEvento", nativeQuery=true)
        List<Solicitud> findByTipoEvento(@Param("idTipoEvento") Long idTipoEvento);
        
    @Query(value = "SELECT * FROM solicitud e WHERE (e.id_usuario = :idUsuario or 1=:idUsuario) and (e.id_tipo_evento = :idTipoEvento or 0=:idTipoEvento)", nativeQuery=true)             
        List<Solicitud> findByUsuarioAndTipoEvento(@Param("idTipoEvento") Long idTipoEvento, @Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT * FROM solicitud e WHERE (e.id_usuario = :idUsuario or 1=:idUsuario) and (e.id_tipo_evento < 3)", nativeQuery=true)             
        List<Solicitud> findGuardiasUsuarioAndTipoEvento(@Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT * FROM solicitud e WHERE (e.id_usuario = :idUsuario or 1=:idUsuario) and (e.id_tipo_evento > 2)", nativeQuery=true)             
        List<Solicitud> findSolicitudesUsuarioAndTipoEvento(@Param("idUsuario") Long idUsuario);
}