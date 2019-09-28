package alquileres.entity;

import alquileres.entity.Inquilino;
import alquileres.entity.Propietario;

import java.util.ArrayList;
import java.util.List;

public class Sitio {
    private List<Propietario> listaPropietarios = new ArrayList();
    private List<Inquilino>  listaInquilinos = new ArrayList<>();

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public List<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }
   /*
   Bueno, tenemos que ver ciertas cosas del diseño, yo por hoy lo voy a dejar aca. Pero estaba pensando, crear una interfaz, llamada FiltroDeBusqueda, y hacer que todas las nuevas clases
   La extiendan, asi simplificar los filtrados a un bucle de tipo for.
   
   public List<Propiedades> buscarPropiedad(alquileres.entity.GeoLoc ciudad, LocalDateTime horarioCheckIn, LocalDateTime horarioCheckOut, Integer cantHuespedes, Integer precioMin, Integer precioMax) {
       List<Propiedades>  listaPropiedadesAdecuadas = new ArrayList<>();
        for(Propietarios propietarios : listaPropietarios) {

        }
        return listaPropiedadesAdecuadas;
   }
   private List<Propiedades> filtrarLista(List<Propiedades> propiedadesList, alquileres.entity.GeoLoc ciudad, LocalDateTime horarioCheckIn, LocalDateTime horarioCheckOut, Integer cantHuespedes, Integer precioMin, Integer precioMax) {
        List<Propiedades> newList = propiedadesList;

   }*/
}

