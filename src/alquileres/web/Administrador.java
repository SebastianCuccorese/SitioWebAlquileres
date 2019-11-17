package alquileres.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private Usuario usuario;
    private Sitio sitio;

    public Administrador(Usuario admin, Sitio sitio) {
        this.usuario = admin;
        this.sitio = sitio;
    }

    public List<Propiedad> BuscarPropiedad(LocalDate entrada, LocalDate salida, String ciudad){
        List<Propiedad>  listaPropiedadesAdecuadas = new ArrayList<>();

        for(Propiedad propiedad : this.sitio.getListaPropiedades()) {
            if(propiedad.getCiudad().equals(ciudad) && this.sitio.hayDisponibilidad(propiedad, entrada, salida)){
                listaPropiedadesAdecuadas.add(propiedad);
            }
        }
        return listaPropiedadesAdecuadas;
    }
    public void crearServicio(String servicio) {
        this.sitio.agregarServicio(new Servicio(servicio));
    }
    public void agregarServicio(Servicio serv) {
        sitio.agregarServicio(serv);
    }
    public void informarReservaAceptada(Usuario user, Reserva reserva) throws Exception {

    }
}
