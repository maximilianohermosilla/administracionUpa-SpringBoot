package com.upa.administracion.Model;

import com.upa.administracion.enums.PerfilNombre;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Perfil {   
    
    @Id
    @GeneratedValue (strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private PerfilNombre perfilNombre;

    public Perfil() {
    }

    public Perfil(PerfilNombre perfilNombre) {
        this.perfilNombre = perfilNombre;
    }
        
    
    public Perfil(Long id, PerfilNombre perfilNombre) {
        this.id = id;
        this.perfilNombre = perfilNombre;
    }
    
    
    
    
}
