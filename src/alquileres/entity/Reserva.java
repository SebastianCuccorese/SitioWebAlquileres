package alquileres.entity;

import alquileres.entity.Inquilino;
import alquileres.entity.Propiedad;

import java.time.LocalDate;

public class Reserva {
    private Inquilino inquilino;
    private Propiedad propiedad;
    private boolean aceptada = false;
    private LocalDate fechaDeIngreso;
    private LocalDate fechaDeSalida;

    public Reserva(Inquilino inquilino, Propiedad propiedad, LocalDate ingreso, LocalDate salida){
        this.setInquilino(inquilino);
        this.setPropiedad(propiedad);
        this.setFechaDeIngreso(ingreso);
        this.setFechaDeSalida(salida);
    }

    private void setFechaDeSalida(LocalDate fechaDeSalida) {
        this.fechaDeSalida = fechaDeSalida;
    }

    private void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public LocalDate getFechaDeSalida() {
        return fechaDeSalida;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    private void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    private void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public boolean isAceptada(){
        return aceptada;
    }

    public void aceptar() {
        this.aceptada = true;
    }
}
