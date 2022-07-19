package com.upa.administracion.Repository;

import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.FileDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    @Query(value = "SELECT * FROM files e WHERE e.id_usuario = :idUsuario", nativeQuery=true)
        FileDB findByUsuario(@Param("idUsuario") Long idUsuario);
        
    @Query(value = "SELECT id FROM files e WHERE e.id_usuario = :idUsuario", nativeQuery=true)
    String findFileByUsuario(@Param("idUsuario") Long idUsuario);
}