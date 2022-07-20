package com.upa.administracion.Repository;

import com.upa.administracion.Model.TipoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLogRepository extends JpaRepository<TipoLog, Long>{
    
}
