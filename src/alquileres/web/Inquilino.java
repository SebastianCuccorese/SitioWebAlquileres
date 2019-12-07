package alquileres.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Inquilino {
    public List<Reserva> todasLasReservas = new ArrayList<>();
    public void crearReserva(Propiedad  propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida) throws WebSiteException;
    public void addReserva(Reserva reserva);
}

