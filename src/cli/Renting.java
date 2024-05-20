package cli;

import modelos.Cliente;
import modelos.Empleado;
import modelos.Persona;
import modelos.Vehiculo;
import services.PersonaService;
import services.VehiculoService;

import java.util.Scanner;

        public class Renting {
            private static final VehiculoService servicioVehiculos = new VehiculoService();
            private static final PersonaService servicioPersonas = new PersonaService();

            public static void main(String[] args) {
                cargarDatosIniciales();

                Scanner sc = new Scanner(System.in);
                while (true) {
                    mostrarMenu();
                    int opcion = Integer.parseInt(sc.nextLine());

                    switch (opcion) {
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

            private static void cargarDatosIniciales() {
                servicioVehiculos.addVehiculo(new Vehiculo("1234ABC", "Toyota", "Corolla", "Blanco", 30.0));
                servicioVehiculos.addVehiculo(new Vehiculo("5678DEF", "Honda", "Civic", "Negro", 35.0));

                servicioPersonas.addPersona(new Cliente("12345678A", "Juan Pérez", "Madrid"));
                servicioPersonas.addPersona(new Empleado("87654321B", "Ana García", "agarcia"));
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

                Empleado empleado = new Empleado(dni, nombre, usuario);
                if (servicioPersonas.addPersona(empleado)) {
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
        }
