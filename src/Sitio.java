import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sitio {
    private List<Propiedad> listaPropiedades = new ArrayList<>();
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

    public List<Propiedad> buscarPropiedad(String ciudad, LocalDate fechaIngreso, LocalDate fechaSalida , Integer cantHuespedes, Integer  precioMin, Integer  precioMax) {
        List<Propiedad>  listaPropiedadesAdecuadas = new ArrayList<>();

        for(Propiedad propiedad : this.getListaPropiedades()) {
           if(propiedad.getCiudad().equals(ciudad) &&
                   hayDisponibilidad(propiedad, fechaIngreso, fechaSalida) && propiedad.getCapacidad() >= this.defaultcantHuespedes(cantHuespedes) &&
                   propiedad.getPrecio() >= this.defaultPrecioMin(precioMin) && propiedad.getPrecio()<= this.defaultPrecioMax(precioMax)){
               listaPropiedadesAdecuadas.add(propiedad);
           }
           return listaPropiedadesAdecuadas;
        }


        return listaPropiedadesAdecuadas;
    }

    public List<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public List<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }

    public List<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }
}

