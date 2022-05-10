package com.upa.administracion.DTO;

import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventoDTO {
    
    private Long id;
    private String title;
    private String start;
    private String end;
    private String description;
    private String color;
    private String backgroundColor;
    private String borderColor;
    private Boolean editable;
    private Boolean enabled;
    private Boolean newEvent;
    private TipoEvento tipoEvento;
    private Usuario usuario;  

    public EventoDTO() {
    }    
    
    public EventoDTO(Long id, String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, Boolean newEvent, TipoEvento tipoEvento, Usuario usuario) {
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
        this.newEvent = newEvent;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
    }

    public EventoDTO(String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, Boolean newEvent, TipoEvento tipoEvento, Usuario usuario) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.newEvent = newEvent;
        this.tipoEvento = tipoEvento;
        this.usuario = usuario;
    }
    
    
    
}
