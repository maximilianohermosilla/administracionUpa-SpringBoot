package com.upa.administracion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import javax.persistence.Column;
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
public class Evento {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String start;
    private String end;
    private String description;
    private String color;
    private String backgroundColor;
    private String borderColor;
    private Boolean editable;
    private Boolean enabled;
    private Boolean newEvent;
    private Long horas;
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_tipo_Evento", nullable=false)
    //@JsonIgnore
    private TipoEvento tipoEvento;
    
    @ManyToOne (fetch=FetchType.EAGER)    
    @JoinColumn (name="id_usuario", nullable=false)
    //@JsonIgnore
    private Usuario usuario;    

    public Evento() {
    }

    public Evento(Long id, String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, TipoEvento tipoEvento, Usuario usuario, Boolean newEvent) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
        this.newEvent = newEvent;
    }

    public Evento(String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, TipoEvento tipoEvento, Usuario usuario, Boolean newEvent) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
        this.newEvent = newEvent;
    }
    
    public Evento(String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, TipoEvento tipoEvento, Usuario usuario, Boolean newEvent, Long horas) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
        this.newEvent = newEvent;
        this.horas = horas;
    }

   
    
    
    
}
