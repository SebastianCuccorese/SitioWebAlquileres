package alquileres.web;

import java.util.ArrayList;
import java.util.List;

public interface Propietario {
    public List<Propiedad> propiedades = new ArrayList<Propiedad>();
    public void aceptarReserva(Reserva reserva);
    public void rechazarReserva(Reserva reserva);
}
