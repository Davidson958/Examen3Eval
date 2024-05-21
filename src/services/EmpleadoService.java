
/*package services;

import modelos.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {
    private final List<Empleado> empleados;

    public EmpleadoService() {
        empleados = new ArrayList<>();
    }

    public boolean añadirEmpleado(Empleado empleado) {
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
            return true;
        }
        return false;
    }

    public Empleado buscarEmpleadoPorUsuario(String usuario) {
        for (Empleado empleado : empleados) {
            if (empleado.getUsuario().equals(usuario)) {
                return empleado;
            }
        }
        return null;
    }
}

 */
package services;

import modelos.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {
    private final List<Empleado> empleados;

    public EmpleadoService() {
        empleados = new ArrayList<>();
    }

    public boolean añadirEmpleado(Empleado empleado) {
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
            return true;
        }
        return false;
    }

    public boolean autenticar(String usuario, String contrasena) {
        for (Empleado empleado : empleados) {
            if (empleado.getUsuario().equals(usuario) && empleado.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Empleado buscarEmpleadoPorUsuario(String usuario) {
        for (Empleado empleado : empleados) {
            if (empleado.getUsuario().equals(usuario)) {
                return empleado;
            }
        }
        return null;
    }
}




