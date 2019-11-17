package alquileres.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {
    private Usuario usuario;
    private Sitio sitio;

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
        this.sitio.listaServicios.add(new Servicio(servicio));
    }
}
