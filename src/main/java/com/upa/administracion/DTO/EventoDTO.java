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
    private String background_color;
    private String border_color;
    private Boolean editable;
    private Boolean enabled;
    private Boolean new_event;
    private Long id_tipo_evento ;
    private Long id_usuario;  

    public EventoDTO() {
    }    
    
    public EventoDTO(Long id, String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, Boolean newEvent, Long id_tipo_evento, Long id_usuario) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.background_color = backgroundColor;
        this.border_color = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.new_event = newEvent;
        this.id_tipo_evento = id_tipo_evento;
        this.id_usuario = id_usuario;
    }

    public EventoDTO(String title, String start, String end, String description, String color, String backgroundColor, String borderColor, Boolean editable, Boolean enabled, Boolean newEvent, Long id_tipo_evento, Long id_usuario) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.color = color;
        this.background_color = backgroundColor;
        this.border_color = borderColor;
        this.editable = editable;
        this.enabled = enabled;
        this.new_event = newEvent;
        this.id_tipo_evento = id_tipo_evento;
        this.id_usuario = id_usuario;
    }
    
    
    
}
