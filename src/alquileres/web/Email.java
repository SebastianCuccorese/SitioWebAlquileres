package alquileres.web;

public class Email implements ServicioCorreo{
    public void enviarMail(Usuario receptor, String mensaje) {
        System.out.println(receptor + ": " + mensaje);
    }
}
