import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inquilino extends Usuario {
    // Yo diria que estas deberian ser las reservas a ser concretadas por el sitio, pero no estoy seguro. Por ahora lo voy a modificar de tar manera
    private List<Reserva> todasLasReservas = new ArrayList<>(); //reservas concretadas


    public Inquilino(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }

   public void hacerReserva(Propiedad unaPropiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
       Reserva newReserva = new Reserva(this, unaPropiedad, fechaDeIngreso, fechaDeSalida); // aun no fue aceptada por propietario
       todasLasReservas.add(newReserva);
   }

    public List<Reserva> getTodasLasReservas() {
        return todasLasReservas;
    }
}
