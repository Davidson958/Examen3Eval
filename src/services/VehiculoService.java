package services;

import modelos.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class VehiculoService {
    private final List<Vehiculo> vehiculos;

    public VehiculoService() {
        this.vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public boolean addVehiculo(Vehiculo vehiculo) {
        if (!vehiculos.contains(vehiculo)) {
            return vehiculos.add(vehiculo);
        }
        return false;
    }

    public boolean removeVehiculo(String matricula) {
        Vehiculo vehiculo = getVehiculoPorMatricula(matricula);
        if (vehiculo != null && !vehiculo.isAlquilado()) {
            return vehiculos.remove(vehiculo);
        }
        return false;
    }

    public Vehiculo getVehiculoPorMatricula(String matricula) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public List<Vehiculo> getVehiculosAlquilados() {
        List<Vehiculo> alquilados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.isAlquilado()) {
                alquilados.add(vehiculo);
            }
        }
        return alquilados;
    }

    public List<Vehiculo> getVehiculosNoAlquilados() {
        List<Vehiculo> noAlquilados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (!vehiculo.isAlquilado()) {
                noAlquilados.add(vehiculo);
            }
        }
        return noAlquilados;
    }
}
