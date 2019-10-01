import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sitio {
    private List<Propietario> listaPropietarios = new ArrayList<>();
    private List<Inquilino>  listaInquilinos = new ArrayList<>();
    private List<Reserva> listaDeReservas = new ArrayList<>();

    private boolean hayDisponibilidad(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
        return !this.existeReservaDePropiedad(propiedad) || this.getListaDeReservas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isAfter(fechaDeSalida) || reserva.getFechaDeSalida().isBefore(fechaDeIngreso)));
    }

    private boolean existeReservaDePropiedad(Propiedad unaPropiedad) {
        return this.getListaDeReservas().stream().anyMatch(r-> r.getPropiedad() == unaPropiedad);
    }

    //valores default para parametros de busqueda no obligatorios
    private Integer defaultcantHuespedes(Integer cant){
        if (cant != null){
            return cant;
        }
        else return 1;
    }

    private Integer defaultPrecioMin(Integer precioMin){
        if (precioMin != null){
            return precioMin;
        }
        else return 0;
    }

    private Integer defaultPrecioMax(Integer precioMax){
        if (precioMax != null){
            return precioMax;
        }
        else return 2000000000;  //buscar si hay definicion de infinito, esto es medio caca
    }

    /*Bueno con esto creo que no hay problemas de repetidos, no me convence lo de los valores default para los parametros opcionales.
    Agregue una lista de reservas en el sitio para conocer la disponibilidad de una propiedad para la busqueda, despues hay que ver donde
    quedaria mejor esa lista.*/

    public List<Propiedad> buscarPropiedad(String ciudad, LocalDate fechaIngreso, LocalDate fechaSalida ,Integer cantHuespedes, Integer  precioMin,Integer  precioMax) {
        List<Propiedad>  listaPropiedadesAdecuadas = new ArrayList<>();

        for(Propietario propietario : this.getListaPropietarios()) {
            listaPropiedadesAdecuadas.addAll((propietario.getListaPropiedades().stream().filter(propiedad -> propiedad.getCiudad().equals(ciudad) &&
                    hayDisponibilidad(propiedad, fechaIngreso, fechaSalida) && propiedad.getCapacidad() >= this.defaultcantHuespedes(cantHuespedes) &&
                    propiedad.getPrecio()>= this.defaultPrecioMin(precioMin) && propiedad.getPrecio()<= this.defaultPrecioMax(precioMax))).collect(Collectors.toList()));
        }


        return listaPropiedadesAdecuadas;
    }

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public List<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }

    public List<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }

     /* Sospecho que hay algo que va a malir sal en ese filtrado, pero por el momento no se me ocurrio una mejor idea de hacerlo.
        Lo que hace es filtrar de la lista de propiedades de cada propietario y agregar los elementos de la listaPropiedadesAdecuadas.
        Despues lo convierto en un linkedHashSet para eliminar los duplicados, y lo vuelvo castear en un arraylist para respetar los tipos.
         */

        /*teoricamente el stream distinct eliminar los elementos duplicados, pero de acuerdo a https://howtodoinjava.com/java/collections/arraylist/remove-duplicate-elements/
        es mejor castearlo a un Hashset
        igual, es algo que iremos viendo.
         */
    // LinkedHashSet<Integer> listaPropiedadesSinDuplicados = new LinkedHashSet(listaPropiedadesAdecuadas);
    //  ArrayList<Propiedades> listaSinDuplicados = new ArrayList(listaPropiedadesSinDuplicados);

}

