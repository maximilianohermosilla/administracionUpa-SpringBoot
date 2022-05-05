package com.upa.administracion.Model;

import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column (unique=true)
    private String legajo;
    @NotNull
    @Column (unique=true)
    private String user;
    private String name;
    private String lastName;
    private String email;
    private String fechaNac;
    private String password;
    private String color;
    private Boolean habilitado;

    /*@ManyToMany (fetch=FetchType.EAGER)    
    @JoinTable(name="perfiles_usuarios", joinColumns= @JoinColumn(name="id_usuario"),
    inverseJoinColumns = @JoinColumn(name="id_perfil"))
    private Set<Perfil> perfiles = new HashSet<>();*/
    
    public Usuario() {
    }

    public Usuario(String legajo, String user, String name, String lastName, String email, String fechaNac, String password, String color, Boolean habilitado) {
        this.legajo = legajo;
        this.user = user;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.fechaNac = fechaNac;
        this.password = password;
        this.color = color;
        this.habilitado = habilitado;
    }

    public Usuario(Long id, String legajo, String user, String name, String lastName, String email, String fechaNac, String password, String color, Boolean habilitado) {
        this.id = id;
        this.legajo = legajo;
        this.user = user;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.fechaNac = fechaNac;
        this.password = password;
        this.color = color;
        this.habilitado = habilitado;
    }
    
}
