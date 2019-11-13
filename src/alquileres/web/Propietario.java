package alquileres.web;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
	boolean aceptaReserva = false;

    public Propietario(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }

    public boolean aceptarReserva(Reserva reserva) {
      // if (reserva.getInquilino().getRanking() > 3) {
           return aceptaReserva;
      // }
        //Por ahora solo retorna true, pero la idea seria darle alguna condicion por la cual acepte. Es lo que se me ocurrio.
    }
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
