package com.upa.administracion.Model;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class TipoLog {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descripcion;

    public TipoLog() {
    }

    public TipoLog(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoLog(String descripcion) {
        this.descripcion = descripcion;
    }
}
