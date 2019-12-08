package alquileres.web;

import java.time.LocalDate;
import java.util.List;

public class Usuario implements Inquilino, Propietario{
    private String nombre;
    private String mail;
    private Integer telefono;
    private ServicioCorreo email;
    private Sitio sitio;

    public Usuario(String nombre, String mail, Integer telefono) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
    }

    @Override
    public void crearReserva(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida) throws WebSiteException {
        Reserva newReserva = new Reserva(this, propiedad, fechaDeIngreso, fechaDeSalida);
        if (sitio.hayDisponibilidad(newReserva.getPropiedad(), newReserva.getFechaDeIngreso(), newReserva.getFechaDeSalida())){
            todasLasReservas.add(newReserva);
        }else{
            throw new WebSiteException(1);
        }
    }

    public void addReserva(Reserva reserva) {
        this.todasLasReservas.add(reserva);
    }

    public List<Reserva> getTodasLasReservas() {
        return todasLasReservas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public  List<Propiedad> getPropiedades(){return propiedades;}

    public void setServicioCorredo(Email m){
        this.email = m;
    }

    public void setSitio(Sitio s){
        this.sitio = s;
    }

    public Sitio getSitio(){
        return this.sitio;
    }

    @Override
    public void aceptarReserva(Reserva reserva) {
        reserva.aceptar();
        email.enviarMail(reserva.getInquilino(), "Su reserva fue aceptada");
    }

    @Override
    public void rechazarReserva(Reserva reserva) {
        email.enviarMail(reserva.getInquilino(), "Lamento informarle que su reserva fue rechazada");
    }
}
