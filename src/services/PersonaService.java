package services;

import modelos.Empleado;
import modelos.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonaService {
    private final List<Persona> personas;

    public PersonaService() {
        this.personas = new ArrayList<>();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public boolean addPersona(Persona persona) {
        if (!personas.contains(persona)) {
            return personas.add(persona);
        }
        return false;
    }

    public Persona getPersonaPorDni(String dni) {
        for (Persona persona : personas) {
            if (persona.getDni().equals(dni)) {
                return persona;
            }
        }
        return null;
    }

    public Empleado getEmpleadoPorUsuario(String usuario) {
        for (Persona persona : personas) {
            if (persona instanceof Empleado && ((Empleado) persona).getUsuario().equals(usuario)) {
                return (Empleado) persona;
            }
        }
        return null;
    }
}
