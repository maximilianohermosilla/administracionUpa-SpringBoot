package com.upa.administracion.Model;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Solicitud {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descripcion;
    private String  date_time;
    private Boolean aprobado;
    private String  date_time_aprobado;
    
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_evento", nullable=false)
    //@JsonIgnore
    private Evento evento;
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_usuario", nullable=false)
    //@JsonIgnore
    private Usuario usuario;    
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_tipo_Evento", nullable=false)
    //@JsonIgnore
    private TipoEvento tipoEvento;

    public Solicitud() {
    }

    public Solicitud(String descripcion, String date_time, Boolean aprobado, String date_time_aprobado, Evento evento, Usuario usuario, TipoEvento tipoEvento) {
        this.descripcion = descripcion;
        this.date_time = date_time;
        this.aprobado = aprobado;
        this.date_time_aprobado = date_time_aprobado;
        this.evento = evento;
        this.usuario = usuario;
        this.tipoEvento = tipoEvento;
    }

    public Solicitud(Long id, String descripcion, String date_time, Boolean aprobado, String date_time_aprobado, Evento evento, Usuario usuario, TipoEvento tipoEvento) {
        this.id = id;
        this.descripcion = descripcion;
        this.date_time = date_time;
        this.aprobado = aprobado;
        this.date_time_aprobado = date_time_aprobado;
        this.evento = evento;
        this.usuario = usuario;
        this.tipoEvento = tipoEvento;
    }
    
        
}