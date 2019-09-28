package alquileres.entity;

import alquileres.entity.Persona;
import alquileres.entity.Propiedad;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Persona {
    private List<Propiedad> listaPropiedades = new ArrayList<>();

    public Propietario(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }

    protected void ponerPropiedadEnAlquiler(Propiedad propiedad) {
            listaPropiedades.add(propiedad);
    }
    protected List getListaPropiedades() {
        return listaPropiedades;
    }

    public void aceptarReserva(Reserva unaReserva){
        unaReserva.aceptar();
        //concrertar reserva en el sistema, para mi deberiamos tener una lista de reservas en el sitio
    }
}
