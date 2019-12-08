package alquileres.web;

public class Email implements ServicioCorreo{
    public String enviarMail(Usuario receptor, String mensaje) {
        String msj = (receptor.getNombre() + ": " + mensaje);
        return msj;
    }
}
