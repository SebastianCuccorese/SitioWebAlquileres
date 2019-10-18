package alquileres.web;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {


    public Propietario(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }

    public boolean aceptarReserva(Reserva reserva) {
      // if (reserva.getInquilino().getRanking() > 3) {
           return true;
      // }
        //Por ahora solo retorna true, pero la idea seria darle alguna condicion por la cual acepte. Es lo que se me ocurrio.
    }
}
