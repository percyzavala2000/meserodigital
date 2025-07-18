package com.meserodigital.application.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meserodigital.domain.model.Persona;
import com.meserodigital.domain.service.PersonaService;
import com.meserodigital.infrastructure.persistence.repository.PersonaJpaRepository;





@Service // Indica que esta clase es un servicio
public class PersonaServiceImpl implements PersonaService {
  @Autowired   // Inyecta la dependencia de PersonaJpaRepository
    // Esta anotación permite que Spring gestione la instancia de esta clase
    // y la inyecte donde sea necesario.
    // Esto es útil para acceder a la base de datos a través de JPA.
    // La inyección de dependencias es una técnica que permite a una clase
    // recibir sus dependencias desde el exterior en lugar de crearlas internamente.
    // Esto facilita la prueba y el mantenimiento del código.
    // En este caso, se inyecta el objeto personaJpaRepository que es una instancia de PersonaJpaRepository.
    // Esto permite que la clase PersonaServiceImple use los métodos de PersonaJpaRepository
    // para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
  private  PersonaJpaRepository personaJpaRepository; // Interfaz para acceder a la base de datos
    // Esta interfaz extiende de JpaRepository, lo que significa que hereda
    // métodos para realizar operaciones CRUD en la base de datos.
    // JpaRepository es una interfaz de Spring Data JPA que proporciona

    @Override // Indica que este método sobrescribe un método de la interfaz PersonaService
    // Esta anotación es opcional, pero es una buena práctica usarla
    // para que el compilador verifique que realmente estamos sobrescribiendo un método.
    // Si el método no existe en la interfaz, se generará un error de compilación.
    // Esto ayuda a evitar errores y a mantener el código más claro.
    // En este caso, el método listarPersonas() sobrescribe el método
    // listarPersonas() de la interfaz PersonaService.
    // Esto significa que este método implementa la lógica para listar personas
    // que se definió en la interfaz.
    // JpaRepository proporciona métodos para realizar operaciones CRUD
    // (Crear, Leer, Actualizar, Eliminar) en la base de datos.
    @Transactional(readOnly = true) // Indica que este método es transaccional
    // y solo se leerán datos de la base de datos.
    // Esto significa que no se realizarán cambios en la base de datos
    // durante la ejecución de este método.
    public List<Persona> listarPersonas() {

        // Implementación del método para listar personas
        // Aquí puedes usar personaJpaRepository para acceder a la base de datos
        // y devolver una lista de personas.
        List<Persona> personas = (List<Persona>) personaJpaRepository.findAll(); 
        return personas;
      
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        // Implementación del método para guardar una persona
        // Aquí puedes usar personaJpaRepository para guardar la persona en la base de datos.
        personaJpaRepository.save(persona);
        // Puedes agregar lógica adicional si es necesario
        // por ejemplo, manejar excepciones o validar datos.
        // personaJpaRepository.save(persona);
        try {
       //si existe una persona con el mismo id
            if (personaJpaRepository.existsById(persona.getId())) {
                // Lógica para actualizar la persona existente
                personaJpaRepository.save(persona);
            } else {
                // Lógica para guardar la nueva persona
                personaJpaRepository.save(persona);
            }
        } catch (Exception e ) {
            // Manejo de excepciones
            // Aquí podrías registrar el error o lanzar una excepción personalizada.
        }
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        // Implementación del método para eliminar una persona
        // Aquí puedes usar personaJpaRepository para eliminar la persona de la base de datos.
        personaJpaRepository.delete(persona);
        // Puedes agregar lógica adicional si es necesario
        // por ejemplo, manejar excepciones o validar datos.
        // personaJpaRepository.delete(persona);
     
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        // Implementación del método para encontrar una persona
       return  personaJpaRepository.findById(persona.getId()).orElse(null);

    }

   
}