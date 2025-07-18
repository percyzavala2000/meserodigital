package com.meserodigital.presentation.admin;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
  @GetMapping("/login")
public String login(Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        return "redirect:/admin/pedidos"; // Esto puede causar bucle si "/" requiere login
    }
    return "login";
}
}
