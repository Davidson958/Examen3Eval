package cli;

import modelos.Cliente;
import modelos.Empleado;
import modelos.Persona;
import modelos.Vehiculo;
import util.Marcas;
import services.EmpleadoService;
import services.PersonaService;
import services.VehiculoService;

import java.util.Scanner;

public class Renting {
    private static final VehiculoService servicioVehiculos = new VehiculoService();
    private static final PersonaService servicioPersonas = new PersonaService();
    private static final EmpleadoService empleadoService = new EmpleadoService();

    public static void main(String[] args) {
        cargarDatosIniciales();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce una opción: " +
                    "\n 1. Entrar al menú como empleado" +
                    "\n 2. Entrar al menú como cliente" +
                    "\n 3. Salir");
            int opcion1 = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            if (opcion1 == 3) {
                System.out.println("Saliendo del programa...");
                break;
            }

            if (opcion1 == 1) {
                boolean autenticado = false;
                while (!autenticado) {
                    System.out.println("Introduce tu usuario: ");
                    String usuario = sc.nextLine();
                    System.out.println("Introduce tu contraseña: ");
                    String password = sc.nextLine();

                    autenticado = empleadoService.autenticar(usuario, password);
                    if (!autenticado) {
                        System.out.println("Usuario o contraseña incorrectos. Vuelva a intentarlo.");
                    }
                }

                while (true) {
                    mostrarMenu();
                    int opcion2 = Integer.parseInt(sc.nextLine());

                    switch (opcion2) {
                        case 1:
                            listarVehiculos();
                            break;
                        case 2:
                            agregarVehiculo(sc);
                            break;
                        case 3:
                            eliminarVehiculo(sc);
                            break;
                        case 4:
                            listarPersonas();
                            break;
                        case 5:
                            agregarCliente(sc);
                            break;
                        case 6:
                            agregarEmpleado(sc);
                            break;
                        case 7:
                            realizarAlquiler(sc);
                            break;
                        case 8:
                            devolverVehiculo(sc);
                            break;
                        case 9:
                            System.out.println("Saliendo del sistema...");
                            return;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
            } else if (opcion1 == 2) {
                boolean autenticado = false;
                Cliente cliente = null;
                while (!autenticado) {
                    System.out.println("Introduce tu DNI: ");
                    String dni = sc.nextLine();
                    System.out.println("Introduce tu nombre: ");
                    String nombre = sc.nextLine();

                    cliente = (Cliente) servicioPersonas.getPersonaPorDni(dni);
                    if (cliente != null && cliente.getNombre().equalsIgnoreCase(nombre)) {
                        autenticado = true;
                    } else {
                        System.out.println("DNI o nombre incorrectos. Vuelva a intentarlo.");
                    }
                }

                while (true) {
                    mostrarMenuCliente();
                    int opcion2 = Integer.parseInt(sc.nextLine());

                    switch (opcion2) {
                        case 1:
                            listarVehiculos();
                            break;
                        case 2:
                            realizarAlquilerCliente(sc, cliente);
                            break;
                        case 3:
                            devolverVehiculoCliente(sc, cliente);
                            break;
                        case 4:
                            System.out.println("Saliendo del sistema...");
                            return;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("---- Menú Principal ----");
        System.out.println("1. Listar vehículos");
        System.out.println("2. Agregar vehículo");
        System.out.println("3. Eliminar vehículo");
        System.out.println("4. Listar personas");
        System.out.println("5. Agregar cliente");
        System.out.println("6. Agregar empleado");
        System.out.println("7. Realizar alquiler");
        System.out.println("8. Devolver vehículo");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarMenuCliente() {
        System.out.println("---- Menú Principal Cliente ----");
        System.out.println("1. Listar vehículos");
        System.out.println("2. Realizar alquiler");
        System.out.println("3. Devolver vehículo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarDatosIniciales() {
        servicioVehiculos.addVehiculo(new Vehiculo("1234ABC", Marcas.Seat, "Corolla", "Blanco", 30.0));
        servicioVehiculos.addVehiculo(new Vehiculo("5678DEF", Marcas.Seat, "Civic", "Negro", 35.0));

        servicioPersonas.addPersona(new Cliente("12345678A", "Juan Pérez", "Madrid"));
        servicioPersonas.addPersona(new Empleado("87654321B", "Ana García", "agarcia", "1234"));
        empleadoService.añadirEmpleado(new Empleado("87654321B", "Ana García", "agarcia", "1234"));
    }

    private static void listarVehiculos() {
        for (Vehiculo vehiculo : servicioVehiculos.getVehiculos()) {
            System.out.println(vehiculo);
        }
    }

    private static void agregarVehiculo(Scanner sc) {
        System.out.print("Ingrese matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Ingrese marca: ");
        String marca = sc.nextLine();
        System.out.print("Ingrese modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Ingrese color: ");
        String color = sc.nextLine();
        System.out.print("Ingrese precio por día: ");
        double precioPorDia = Double.parseDouble(sc.nextLine());

        Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo, color, precioPorDia);
        if (servicioVehiculos.addVehiculo(vehiculo)) {
            System.out.println("Vehículo agregado exitosamente.");
        } else {
            System.out.println("El vehículo ya existe.");
        }
    }

    private static void eliminarVehiculo(Scanner sc) {
        System.out.print("Ingrese matrícula del vehículo a eliminar: ");
        String matricula = sc.nextLine();
        if (servicioVehiculos.removeVehiculo(matricula)) {
            System.out.println("Vehículo eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el vehículo. Asegúrese de que la matrícula es correcta y que el vehículo no esté alquilado.");
        }
    }

    private static void listarPersonas() {
        for (Persona persona : servicioPersonas.getPersonas()) {
            System.out.println(persona);
        }
    }

    private static void agregarCliente(Scanner sc) {
        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese ciudad: ");
        String ciudad = sc.nextLine();

        Cliente cliente = new Cliente(dni, nombre, ciudad);
        if (servicioPersonas.addPersona(cliente)) {
            System.out.println("Cliente agregado exitosamente.");
        } else {
            System.out.println("El cliente ya existe.");
        }
    }

    private static void agregarEmpleado(Scanner sc) {
        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = sc.nextLine();

        Empleado empleado = new Empleado(dni, nombre, usuario, contrasena);
        if (servicioPersonas.addPersona(empleado)) {
            empleadoService.añadirEmpleado(empleado);
            System.out.println("Empleado agregado exitosamente.");
        } else {
            System.out.println("El empleado ya existe.");
        }
    }

    private static void realizarAlquiler(Scanner sc) {
        System.out.print("Ingrese matrícula del vehículo: ");
        String matricula = sc.nextLine();
        Vehiculo vehiculo = servicioVehiculos.getVehiculoPorMatricula(matricula);
        if (vehiculo == null || vehiculo.isAlquilado()) {
            System.out.println("El vehículo no existe o ya está alquilado.");
            return;
        }

        System.out.print("Ingrese DNI del cliente: ");
        String dni = sc.nextLine();
        Cliente cliente = (Cliente) servicioPersonas.getPersonaPorDni(dni);
        if (cliente == null) {
            System.out.println("El cliente no existe.");
            return;
        }

        System.out.print("Ingrese días de alquiler: ");
        int dias = Integer.parseInt(sc.nextLine());
        double total = vehiculo.alquilar(cliente, dias);
        cliente.addVehiculoAlquilado(vehiculo);
        System.out.printf("Alquiler realizado. Total con IVA: %.2f%n", total);
    }

    private static void realizarAlquilerCliente(Scanner sc, Cliente cliente) {
        System.out.print("Ingrese matrícula del vehículo: ");
        String matricula = sc.nextLine();
        Vehiculo vehiculo = servicioVehiculos.getVehiculoPorMatricula(matricula);
        if (vehiculo == null || vehiculo.isAlquilado()) {
            System.out.println("El vehículo no existe o ya está alquilado.");
            return;
        }

        System.out.print("Ingrese días de alquiler: ");
        int dias = Integer.parseInt(sc.nextLine());
        double total = vehiculo.alquilar(cliente, dias);
        cliente.addVehiculoAlquilado(vehiculo);
        System.out.printf("Alquiler realizado. Total con IVA: %.2f%n", total);
    }

    private static void devolverVehiculo(Scanner sc) {
        System.out.print("Ingrese matrícula del vehículo a devolver: ");
        String matricula = sc.nextLine();
        Vehiculo vehiculo = servicioVehiculos.getVehiculoPorMatricula(matricula);
        if (vehiculo != null && vehiculo.isAlquilado()) {
            vehiculo.desalquilar();
            System.out.println("Vehículo devuelto exitosamente.");
        } else {
            System.out.println("El vehículo no está alquilado o no existe.");
        }
    }

    private static void devolverVehiculoCliente(Scanner sc, Cliente cliente) {
        System.out.print("Ingrese matrícula del vehículo a devolver: ");
        String matricula = sc.nextLine();
        Vehiculo vehiculo = servicioVehiculos.getVehiculoPorMatricula(matricula);
        if (vehiculo != null && vehiculo.isAlquilado() && cliente.getVehiculosAlquilados().contains(vehiculo)) {
            vehiculo.desalquilar();
            System.out.println("Vehículo devuelto exitosamente.");
        } else {
            System.out.println("El vehículo no está alquilado o no existe.");
        }
    }
}



