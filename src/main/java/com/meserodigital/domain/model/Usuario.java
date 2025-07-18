package com.meserodigital.domain.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity // indica que es una entidad
@Data // genera los getters y setters
@Table(name = "usuario") // nombre de la tabla en la base de datos
public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L; // version de la clase 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto incrementable
    
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty // validacion de no vacio
    private String password;
    @OneToMany // relacion uno a muchos
    @JoinColumn(name = "usuario_id") // columna foranea
    private List<Rol> roles;




}