package alquileres.web;

public interface ServicioCorreo {
    public void enviarMail(Usuario receptor, String mensaje);
}
