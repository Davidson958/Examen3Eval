package modelos;

import util.Constantes;

public class Vehiculo {
    private final String matricula;
    private final String marca;
    private final String modelo;
    private String color;
    private final double precioPorDia;
    private boolean alquilado;
    private Cliente cliente;
    private int diasAlquiler;

    public Vehiculo(String matricula, String marca, String modelo, String color, double precioPorDia) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioPorDia = precioPorDia;
        this.alquilado = false;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public double alquilar(Cliente cliente, int dias) {
        this.alquilado = true;
        this.cliente = cliente;
        this.diasAlquiler = dias;
        return this.precioPorDia * dias * (1 + Constantes.IVA);
    }

    public void desalquilar() {
        this.alquilado = false;
        this.cliente = null;
        this.diasAlquiler = 0;
    }

    @Override
    public String toString() {
        return String.format("Vehículo matrícula: %s, marca: %s, modelo: %s, color: %s, precio sin IVA: %.2f, está alquilado: %b, dni y nombre del cliente: %s",
                matricula, marca, modelo, color, precioPorDia, alquilado, (cliente == null ? "Ninguno" : cliente.getDni() + " " + cliente.getNombre()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) obj;
        return matricula.equals(vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
}
