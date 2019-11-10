package alquileres.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sitio {
    private List<Propiedad> listaPropiedades = new ArrayList<>();
    private List<Inquilino>  listaInquilinos = new ArrayList<>();
    private List<Propietario>  listaPropietarios = new ArrayList<>();
    private List<Reserva> listaDeReservasConfirmadas = new ArrayList<>();
    private List<Reserva> listaDeReservasAConfirmar = new ArrayList<>();

    public void crearPropiedad (String tipo, String ciudad, String servicios, Integer capacidad, LocalTime horarioEntrada, LocalTime horarioSalida, Integer precio, Propietario propietario) throws Exception {
        Propiedad nuevaPropiedad = new Propiedad(tipo, ciudad, servicios, capacidad, horarioEntrada, horarioSalida, precio, propietario);
        /*Si el propietario es user del sitio, publico la nuevaPropiedad, sino tiro excepcion*/
        if(listaPropietarios.contains(propietario)){
            listaPropiedades.add(nuevaPropiedad);
        }else{
            throw new Exception("No existe el propietario en el Sistema");
        }
    }

    private boolean hayDisponibilidad(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
        return !this.existeReservaDePropiedad(propiedad) || this.getListaDeReservasConfirmadas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isAfter(fechaDeSalida) || reserva.getFechaDeSalida().isBefore(fechaDeIngreso)));
    }

    private boolean existeReservaDePropiedad(Propiedad unaPropiedad) {
        return this.getListaDeReservasConfirmadas().stream().anyMatch(r-> r.getPropiedad().equals(unaPropiedad));
    }

    public void aceptarYCrearReserva(Reserva reserva) throws Exception {
            if (reserva.getPropiedad().getPropietario().aceptarReserva(reserva)) {
                reserva.getInquilino().agendarReserva(reserva); //Seria el paso que envia el mail de confirmacion
                listaDeReservasAConfirmar.remove(reserva);
                listaDeReservasConfirmadas.add(reserva);
            }
            else {
                    throw new Exception("Su reserva ha sido rechazada");
            }
    }
    public void crearReserva(Inquilino inquilino, Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida) throws Exception {
        Reserva newReserva = new Reserva(inquilino, propiedad, fechaDeIngreso, fechaDeSalida); // aun no fue aceptada por propietario
        if (hayDisponibilidad(newReserva.getPropiedad(), newReserva.getFechaDeIngreso(), newReserva.getFechaDeSalida())){
            listaDeReservasAConfirmar.add(newReserva);
        }else{
            throw new Exception("No hay disponibilidad para realizar la reserva");
        }
    }
/*
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

    private double defaultPrecioMax(Integer precioMax){
        double pInfiniteDouble = Double.POSITIVE_INFINITY;
        if (precioMax != null){
            return precioMax;
        }
        else return pInfiniteDouble;  //buscar si hay definicion de infinito, esto es medio caca
        //Listo, ya es infinito. Teoricamente :)

    }

    /*Bueno con esto creo que no hay problemas de repetidos, no me convence lo de los valores default para los parametros opcionales.
    Agregue una lista de reservas en el sitio para conocer la disponibilidad de una propiedad para la busqueda, despues hay que ver donde
    quedaria mejor esa lista.*/
    /*
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
    }*/
    public List<Propiedad> buscarPorFechaYCiudad(LocalDate entrada, LocalDate salida, String ciudad){
        List<Propiedad>  listaPropiedadesAdecuadas = new ArrayList<>();

        for(Propiedad propiedad : this.getListaPropiedades()) {
            if(propiedad.getCiudad().equals(ciudad) && this.hayDisponibilidad(propiedad, entrada, salida)){
                listaPropiedadesAdecuadas.add(propiedad);
            }
        }
        return listaPropiedadesAdecuadas;
    }
    public List<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public List<Reserva> getListaDeReservasConfirmadas() {
        return listaDeReservasConfirmadas;
    }

    public List<Reserva> getListaDeReservasAConfirmar() {
        return listaDeReservasAConfirmar;
    }

    public List<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }
    public void registrarse(Inquilino inquilino) {
        listaInquilinos.add(inquilino);
    }
    public List<Propietario> getListaPropietarios(){
        return listaPropietarios;
    }
    public void registrarse(Propietario prop){
        listaPropietarios.add(prop);
    }
}

