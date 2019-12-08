import alquileres.web.WebSiteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebExceptionTest {
    WebSiteException wet1;
    WebSiteException wet2;

    @BeforeEach
    public void setUp() {
        wet1 = new WebSiteException(01);
        wet2 = new WebSiteException(02);
    }

    @Test
    public void getMessage01() {
        String msjEsperado = "No hay disponibilidad para realizar la reserva";
        String msjObtenido = wet1.getMessage();
        assertEquals(msjEsperado, msjObtenido);
    }

    @Test
    public void getMessage02() {
        String msjEsperado = "El Propietario no se encuentra registrado";
        String msjObtenido = wet2.getMessage();
        assertEquals(msjEsperado, msjObtenido);
    }
}