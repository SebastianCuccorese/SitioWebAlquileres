package alquileres.entity;


import java.time.LocalDateTime;

public class Propiedad {
    private String tipo;
    private String ciudad;
    private String servicios;
    private Integer capacidad;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSalida;
    private Integer precio;

    public Propiedad(String tipo, String ciudad, String servicios, Integer capacidad, LocalDateTime horarioEntrada, LocalDateTime horarioSalida, Integer precio) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.servicios = servicios;
        this.capacidad = capacidad;
        this.horarioEntrada = horarioEntrada;
        this.horarioSalida = horarioSalida;
        this.precio = precio;
    }

    public String  getTipo() {
        return tipo;
    }

    public String getCiudad() {
        return ciudad;
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
