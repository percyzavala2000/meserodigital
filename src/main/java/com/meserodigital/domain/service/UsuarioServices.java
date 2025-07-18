package com.meserodigital.domain.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meserodigital.domain.model.Rol;
import com.meserodigital.domain.model.Usuario;
import com.meserodigital.infrastructure.persistence.repository.UsuarioJpaRepository;

import lombok.extern.slf4j.Slf4j;


@Service("userDetailsService")
@Slf4j
public class UsuarioServices implements UserDetailsService {
  @Autowired
  private UsuarioJpaRepository usuarioJpaRepository;
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   
    Usuario usuario = usuarioJpaRepository.findByUsername(username);
    if (usuario == null) {
  
      throw new UsernameNotFoundException("Usuario no encontrado");
    }

    var roles = new ArrayList<GrantedAuthority>();
    for (Rol rol : usuario.getRoles()) {
      roles.add(new SimpleGrantedAuthority(rol.getNombre()));
    }
    return new User(usuario.getUsername(), usuario.getPassword(), roles);

  }
}