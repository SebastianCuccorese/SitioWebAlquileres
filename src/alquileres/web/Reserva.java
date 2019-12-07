package alquileres.web;

import java.time.LocalDate;

public class Reserva {
    private Usuario inquilino;
    private Propiedad propiedad;
    private boolean aceptada = false;
    private LocalDate fechaDeIngreso;
    private LocalDate fechaDeSalida;

    public Reserva(Usuario inquilino, Propiedad propiedad, LocalDate ingreso, LocalDate salida){
        this.setInquilino(inquilino);
        this.setPropiedad(propiedad);
        this.setFechaDeIngreso(ingreso);
        this.setFechaDeSalida(salida);
    }

    public void aceptar() {
        this.aceptada = true;
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

    public Usuario getInquilino() {
        return inquilino;
    }

    private void setInquilino(Usuario inquilino) {
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
}
