package com.meserodigital.domain.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
  private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String nombre;
  @NotEmpty
  private String apellido;
  @NotEmpty
  @Email
  private String correo;
  @NotEmpty
  private String telefono;
 

}
