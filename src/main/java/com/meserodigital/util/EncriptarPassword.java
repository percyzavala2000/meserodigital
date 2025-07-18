package com.meserodigital.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
  public static void main(String[] args) {
    String password = "cocina";
    String passwordEncriptado = encriptarPassword(password);
    System.out.println("Password encriptado: " + passwordEncriptado);

    
  }
  public static String encriptarPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    return passwordEncoder.encode(password);
  }

}
