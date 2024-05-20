package modelos;

public class Empleado extends Persona {
    private final String usuario;
    private final String contrasena;

    public Empleado(String dni, String nombre, String usuario) {
        super(dni, nombre);
        this.usuario = usuario;
        this.contrasena = "1234";
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    @Override
    public String toString() {
        return super.toString() + ", usuario: " + usuario;
    }
}
