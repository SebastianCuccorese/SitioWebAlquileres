package alquileres.web;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String mail;
    private Integer telefono;
    private List<Reserva> todasLasReservas = new ArrayList<>(); //reservas concretadas
    private boolean aceptaReserva;

    public Usuario(String nombre, String mail, Integer telefono) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.aceptaReserva = false;
    }

    public void agendarReserva(Reserva reserva) {
        todasLasReservas.add(reserva);
    }

    public List<Reserva> getTodasLasReservas() {
        return todasLasReservas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public boolean aceptarReserva(Reserva reserva) { return aceptaReserva; }

    public void aceptaReservas() {
        aceptaReserva = true;
    }

    public void noAceptaReservas() {
        aceptaReserva = false;
    }

    public boolean getAceptacion() {
        return aceptaReserva;
    }
}
