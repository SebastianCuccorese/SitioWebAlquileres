package alquileres.web;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Sitio {
    private List<Propiedad> listaPropiedades = new ArrayList<>();
    private List<Usuario>  listaUsuarios = new ArrayList<>();
    private List<Reserva> listaDeReservas = new ArrayList<>();
    public List<Servicio> listaDeServicios = new ArrayList<>();

    public List<Servicio> getListaDeServicios(){
        return listaDeServicios;
    }


    public boolean hayDisponibilidad(Propiedad propiedad, LocalDate fechaDeIngreso, LocalDate fechaDeSalida){
        return !this.existeReservaDePropiedad(propiedad) || (this.getListaDeReservas().stream().anyMatch(reserva -> reserva.getPropiedad() == propiedad &&(
                reserva.getFechaDeIngreso().isAfter(fechaDeSalida) || reserva.getFechaDeSalida().isBefore(fechaDeIngreso))));
    }
    private boolean existeReservaDePropiedad(Propiedad unaPropiedad) {
        return this.getListaDeReservas().stream().anyMatch(r-> r.getPropiedad().equals(unaPropiedad));
    }

    public void addPropiedad(Propiedad propiedad) throws WebSiteException {
        if(listaUsuarios.contains(propiedad.getPropietario())) {
            this.listaPropiedades.add(propiedad);
        }else{
            throw new WebSiteException(02);
        }
    }

    public void addReserva(Reserva reserva){
        this.listaDeReservas.add(reserva);
    }

    public List<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public List<Reserva> getListaDeReservas() {
        return listaDeReservas;
    }


    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }

    public void registrarse(Usuario user){
        listaUsuarios.add(user);
    }
    public void agregarServicio(Servicio serv){
        listaDeServicios.add(serv);
    }
}

