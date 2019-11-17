import alquileres.web.Propiedad;
import alquileres.web.Reserva;
import alquileres.web.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    Usuario tomas;
    Usuario juan;
    Propiedad depto;

    @BeforeEach
    void setUp() {
        tomas = new Usuario("Tomas", "Tomas@Hotmail.com", 30980092);
        depto = new Propiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        tomas.agendarReserva(new Reserva(tomas, depto, LocalDate.of(2019, 11, 23), LocalDate.of(2019, 11, 30)));
    }

    @Test
    void getFechaDeIngreso() {
        assertEquals(tomas.getTodasLasReservas().get(0).getFechaDeIngreso(), LocalDate.of(2019, 11, 23));
    }

    @Test
    void getFechaDeSalida() {
        assertEquals(tomas.getTodasLasReservas().get(0).getFechaDeSalida(), LocalDate.of(2019, 11,30));
    }

    @Test
    void getInquilino() {
        assertEquals(tomas.getTodasLasReservas().get(0).getInquilino(), tomas);
    }

    @Test
    void getPropiedad() {
        assertEquals(tomas.getTodasLasReservas().get(0).getPropiedad(), depto);
    }

    @Test
    void isAceptada() {
        assertFalse(tomas.getTodasLasReservas().get(0).isAceptada());
    }

    @Test
    void aceptar() {
        tomas.getTodasLasReservas().get(0).aceptar();
        assertTrue(tomas.getTodasLasReservas().get(0).isAceptada());
    }
}