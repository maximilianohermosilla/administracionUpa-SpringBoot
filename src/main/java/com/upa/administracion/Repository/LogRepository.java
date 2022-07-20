package com.upa.administracion.Repository;

import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.Log;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{ 
    
     @Query(value = "SELECT * FROM log e WHERE e.id_usuario = :idUsuario", nativeQuery=true)
        List<Log> findByUsuario(@Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT * FROM log e WHERE e.id_tipo_log = :idTipoLog", nativeQuery=true)
        List<Log> findByTipoLog(@Param("idTipoLog") Long idTipoLog);
        
    //@Query(value = "SELECT * FROM evento e WHERE e.id_usuario = :idUsuario and e.id_tipo_log = :idTipoLog", nativeQuery=true)
    @Query(value = "SELECT * FROM log e WHERE (e.id_usuario = :idUsuario or 1=:idUsuario) and (e.id_tipo_log = :idTipoLog or 0=:idTipoLog)", nativeQuery=true)             
        List<Log> findByUsuarioAndTipoLog(@Param("idTipoLog") Long idTipoLog, @Param("idUsuario") Long idUsuario);
}
