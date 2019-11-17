package alquileres.web;

import java.time.LocalTime;
import java.util.List;

public class Propiedad {
    private String tipo;
    private String ciudad;
    private List<Servicio> servicios;
    private Integer capacidad;
    private LocalTime horarioEntrada;
    private LocalTime horarioSalida;
    private Integer precio;
    private Usuario propietario;

    public Propiedad(String tipo, String ciudad, List<Servicio> servicios, Integer capacidad, LocalTime horarioEntrada, LocalTime horarioSalida, Integer precio, Usuario propietario) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.servicios = servicios;
        this.capacidad = capacidad;
        this.horarioEntrada = horarioEntrada;
        this.horarioSalida = horarioSalida;
        this.precio = precio;
        this.propietario = propietario;
    }

    public String  getTipo() {
        return tipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalTime getHorarioSalida() {
        return horarioSalida;
    }

    public Integer getPrecio() {
        return precio;
    }

    public Usuario getPropietario() {
        return propietario;
    }
}
