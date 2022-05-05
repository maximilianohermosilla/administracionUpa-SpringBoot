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
public class TipoEvento {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descripcion;
    private Boolean oneDay;

    public TipoEvento() {
    }

    public TipoEvento(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoEvento(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEvento(String descripcion, Boolean oneDay) {
        this.descripcion = descripcion;
        this.oneDay = oneDay;
    }

    public TipoEvento(Long id, String descripcion, Boolean oneDay) {
        this.id = id;
        this.descripcion = descripcion;
        this.oneDay = oneDay;
    }
    
       
    
}
