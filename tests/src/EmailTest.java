import alquileres.web.Email;
import alquileres.web.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTest {
    Email servicoDeCorreo;
    Usuario tomas;
    String msj;

    @BeforeEach
    public void setUp() {
        servicoDeCorreo = new Email();
        tomas = new Usuario("Tomas", "Tomas@Hotmail.com", 30980092);
        msj = "Su Reserva ha sido realizada con Exito";
    }

    @Test
    public void sendEmail() {
        String msjEsperado = "Tomas: Su Reserva ha sido realizada con Exito";
        String msjObtenido = servicoDeCorreo.enviarMail(tomas, msj);
        assertEquals(msjEsperado, msjObtenido);

    }
}