package alquileres.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Inquilino extends Usuario {

    private List<Reserva> todasLasReservas = new ArrayList<>(); //reservas concretadas

    public Inquilino(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }
   /* protected List<Propiedades> buscarPropiedad(alquileres.entity.GeoLoc ciudad, LocalDateTime horarioCheckIn, LocalDateTime horarioCheckOut, Integer cantHuespedes, Integer precioMin, Integer precioMax) {

    }*/

   public Reserva hacerReserva(Propiedad unaPropiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
       Reserva newReserva = new Reserva(this, unaPropiedad, fechaDeIngreso, fechaDeSalida); // aun no fue aceptada por propietario
       return newReserva;
   }

    public List<Reserva> getTodasLasReservas() {
        return todasLasReservas;
    }
}
