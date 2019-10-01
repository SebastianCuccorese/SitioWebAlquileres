package alquileres.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sitio {
    private List<Propietario> listaPropietarios = new ArrayList<>();
    private List<Inquilino>  listaInquilinos = new ArrayList<>();

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public List<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }

//   Bueno, tenemos que ver ciertas cosas del diseño, yo por hoy lo voy a dejar aca. Pero estaba pensando, crear una interfaz, llamada FiltroDeBusqueda, y hacer que todas las nuevas clases
    //  La extiendan, asi simplificar los filtrados a un bucle de tipo for.

    public List<Propiedad> buscarPropiedad(String ciudad, LocalDateTime horarioCheckIn, LocalDateTime horarioCheckOut, Integer cantHuespedes, Integer precioMin, Integer precioMax) {
        List<Propiedad>  listaPropiedadesAdecuadas = new ArrayList<>();
        for(Propietario propietario : listaPropietarios) {
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getCiudad().equals(ciudad))).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getHorarioEntrada() == horarioCheckIn)).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getHorarioSalida() == horarioCheckOut)).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getCapacidad() == cantHuespedes)).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getHorarioEntrada() == horarioCheckIn)).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getPrecio() <= precioMax)).collect(Collectors.toList()));
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedades -> propiedades.getPrecio() >= precioMin)).collect(Collectors.toList()));
        }
        /* Sospecho que hay algo que va a malir sal en ese filtrado, pero por el momento no se me ocurrio una mejor idea de hacerlo.
        Lo que hace es filtrar de la lista de propiedades de cada propietario y agregar los elementos de la listaPropiedadesAdecuadas.
        Despues lo convierto en un linkedHashSet para eliminar los duplicados, y lo vuelvo castear en un arraylist para respetar los tipos.
         */
        listaPropiedadesAdecuadas.stream().distinct().collect(Collectors.toList());
        /*teoricamente el stream distinct eliminar los elementos duplicados, pero de acuerdo a https://howtodoinjava.com/java/collections/arraylist/remove-duplicate-elements/
        es mejor castearlo a un Hashset
        igual, es algo que iremos viendo.
         */
        // LinkedHashSet<Integer> listaPropiedadesSinDuplicados = new LinkedHashSet(listaPropiedadesAdecuadas);
        //  ArrayList<Propiedades> listaSinDuplicados = new ArrayList(listaPropiedadesSinDuplicados);
        return listaPropiedadesAdecuadas;
    }
}

