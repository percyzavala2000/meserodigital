package com.meserodigital.application.dto;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private int numMesa;
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getNombre() {
      return nombre;
    }
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }
    public int getNumMesa() {
      return numMesa;
    }
    public void setNumMesa(int numMesa) {
      this.numMesa = numMesa;
    }

  
}
