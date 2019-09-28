package alquileres.entity;

import alquileres.entity.GeoLoc;

import java.time.LocalDateTime;

public class Propiedad {
    private Tipo tipo;
    private GeoLoc geoLoc;
    private String servicios;
    private Integer capacidad;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSalida;
    private Integer precio;

    public Propiedad(Tipo tipo, GeoLoc geoLoc, String servicios, Integer capacidad, LocalDateTime horarioEntrada, LocalDateTime horarioSalida, Integer precio) {
        this.tipo = tipo;
        this.geoLoc = geoLoc;
        this.servicios = servicios;
        this.capacidad = capacidad;
        this.horarioEntrada = horarioEntrada;
        this.horarioSalida = horarioSalida;
        this.precio = precio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public GeoLoc getGeoLoc() {
        return geoLoc;
    }

    public String getServicios() {
        return servicios;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSalida() {
        return horarioSalida;
    }

    public Integer getPrecio() {
        return precio;
    }
}
