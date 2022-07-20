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
public class Log {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descripcion;
    private String  date_time;
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_tipo_log", nullable=false)
    private TipoLog tipoLog;
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_usuario", nullable=false)
    private Usuario usuario;   

    public Log() {
    }

    public Log(String descripcion, String date_time) {
        this.descripcion = descripcion;
        this.date_time = date_time;
    }   
    

    public Log(String descripcion, String date_time, TipoLog tipoLog, Usuario usuario) {
        this.descripcion = descripcion;
        this.date_time = date_time;
        this.tipoLog = tipoLog;
        this.usuario = usuario;
    }

    public Log(Long id, String descripcion, String date_time, TipoLog tipoLog, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.date_time = date_time;
        this.tipoLog = tipoLog;
        this.usuario = usuario;
    }
    
    
}
