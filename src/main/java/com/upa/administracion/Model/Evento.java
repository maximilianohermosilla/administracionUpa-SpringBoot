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
    private String editable;
    
    @ManyToOne (fetch=FetchType.LAZY)    
    @JoinColumn (name="id_tipoEvento", nullable=false)
    @JsonIgnore
    private TipoEvento tipoEvento;
    
    @ManyToOne (fetch=FetchType.LAZY)    
    @JoinColumn (name="id_usuario", nullable=false)
    @JsonIgnore
    private Usuario usuario;    

    public Evento() {
    }

    public Evento(Long id, String title, String start, String end, String description, String color, String backgroundColor, String borderColor, String editable, TipoEvento tipoEvento, Usuario usuario) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
    }

    public Evento(String title, String start, String end, String description, String color, String backgroundColor, String borderColor, String editable, TipoEvento tipoEvento, Usuario usuario) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
    }

    
    
    
}
