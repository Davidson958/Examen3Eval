package modelos;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private final String ciudad;
    private final List<Vehiculo> vehiculosAlquilados;

    public Cliente(String dni, String nombre, String ciudad) {
        super(dni, nombre);
        this.ciudad = ciudad;
        this.vehiculosAlquilados = new ArrayList<>();
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Vehiculo> getVehiculosAlquilados() {
        return vehiculosAlquilados;
    }

    public void addVehiculoAlquilado(Vehiculo vehiculo) {
        this.vehiculosAlquilados.add(vehiculo);
    }

    @Override
    public String toString() {
        return super.toString() + ", vive en " + ciudad + ", ha alquilado " + vehiculosAlquilados.size() + " coches";
    }
}

