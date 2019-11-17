package alquileres.web;

import org.mockito.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sitio {
    private List<Propiedad> listaPropiedades = new ArrayList<>();
    private List<Usuario>  listaUsuarios = new ArrayList<>();
    private List<Reserva> listaDeReservas = new ArrayList<>();

    public boolean hayDisponibilidad(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
        return !this.existeReservaDePropiedad(propiedad) || this.hayDisponibilidadAntesDeEstaFecha(propiedad, fechaDeSalida)
                        || this.hayDisponibilidadDespuesDeEstaFecha(propiedad, fechaDeIngreso);
    }

    public boolean hayDisponibilidadDespuesDeEstaFecha(Propiedad propiedad, LocalDate fechaDeSalida) {
        return this.getListaDeReservas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isAfter(fechaDeSalida)));
    }
    public boolean hayDisponibilidadAntesDeEstaFecha(Propiedad propiedad, LocalDate fechaDeIngreso) {
        return this.getListaDeReservas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isBefore(fechaDeIngreso)));
    }

    private boolean existeReservaDePropiedad(Propiedad unaPropiedad) {
        return this.getListaDeReservas().stream().anyMatch(r-> r.getPropiedad().equals(unaPropiedad));
    }

    public void crearPropiedad(Propiedad propiedad) {
            this.listaPropiedades.add(propiedad);
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

    public List<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }


    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }

    public void registrarse(Usuario user){
        listaUsuarios.add(user);
    }
}

