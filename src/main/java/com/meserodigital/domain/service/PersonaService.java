package com.meserodigital.domain.service;

import java.util.List;

import com.meserodigital.domain.model.Persona;

public interface PersonaService {
  public List<Persona> listarPersonas(); // lista de personas
  public void guardar(Persona persona);// guardar persona
  public void eliminar(Persona persona); // eliminar persona
  public Persona encontrarPersona(Persona persona); // encontrar persona


}