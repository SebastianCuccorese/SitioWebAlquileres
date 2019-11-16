package alquileres.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sitio {
    private List<Propiedad> listaPropiedades = new ArrayList<>();
    private List<Usuario>  listaUsuarios = new ArrayList<>();
    private List<Reserva> listaDeReservas = new ArrayList<>();

    public boolean hayDisponibilidad(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
        return !this.existeReservaDePropiedad(propiedad) || this.getListaDeReservasConfirmadas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isAfter(fechaDeSalida) || reserva.getFechaDeSalida().isBefore(fechaDeIngreso)));
    }

    private boolean existeReservaDePropiedad(Propiedad unaPropiedad) {
        return this.getListaDeReservasConfirmadas().stream().anyMatch(r-> r.getPropiedad().equals(unaPropiedad));
    }

    public void aceptarYCrearReserva(Reserva reserva) throws Exception {
            if (reserva.getPropiedad().getPropietario().aceptarReserva(reserva)) {
                reserva.aceptar();
            }
            else {
                    throw new Exception("Su reserva ha sido rechazada");
            }
    }
    public void crearReserva(Usuario inquilino, Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida) throws Exception {
        Reserva newReserva = new Reserva(inquilino, propiedad, fechaDeIngreso, fechaDeSalida); // aun no fue aceptada por propietario
        if (hayDisponibilidad(newReserva.getPropiedad(), newReserva.getFechaDeIngreso(), newReserva.getFechaDeSalida())){
            listaDeReservas.add(newReserva);
        }else{
            throw new Exception("No hay disponibilidad para realizar la reserva");
        }
    }
    public List<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public List<Reserva> getListaDeReservasConfirmadas() {
        return listaDeReservas;
    }

    public List<Reserva> getListaDeReservasAConfirmar() {
        return listaDeReservas;
    }

    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }

    public void registrarse(Usuario user){
        listaUsuarios.add(user);
    }
}

