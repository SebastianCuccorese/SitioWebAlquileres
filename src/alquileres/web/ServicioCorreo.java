package alquileres.web;

public interface ServicioCorreo {
    public String enviarMail(Usuario receptor, String mensaje);
}
