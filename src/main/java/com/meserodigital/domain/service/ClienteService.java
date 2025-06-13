 package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente registrarCliente(Cliente cliente);
    List<Cliente> listarClientes();
}
