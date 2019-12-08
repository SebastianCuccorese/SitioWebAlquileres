import alquileres.web.Propiedad;
import alquileres.web.Reserva;
import alquileres.web.Servicio;
import alquileres.web.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class ReservaTest {
        Usuario tomas;
        Usuario juan;
        Propiedad depto;
        List<Servicio> servicios1;
        List<Servicio> servicios2;
        Servicio tv;
        Servicio agua;
        Servicio luz;
        Servicio gas;
        Servicio internet;

        @BeforeEach
        public void setUp() {
            agua = new Servicio("Agua");
            luz = new Servicio("Luz");
            internet = new Servicio("Internet");
            gas = new Servicio("Gas");
            servicios2 = new ArrayList<>();
            servicios2.add(agua);
            servicios2.add(luz);
            servicios2.add(gas);
            tomas = new Usuario("Tomas", "Tomas@Hotmail.com", 30980092);
            depto = new Propiedad("Departamento", "Mar del Plata", servicios2, 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
            tomas.addReserva(new Reserva(tomas, depto, LocalDate.of(2019, 11, 23), LocalDate.of(2019, 11, 30)));
        }

        @Test
        void getFechaDeIngreso() {
            assertEquals(tomas.getTodasLasReservas().get(0).getFechaDeIngreso(), LocalDate.of(2019, 11, 23));
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }

        @Test
        void getFechaDeSalida() {
            assertEquals(tomas.getTodasLasReservas().get(0).getFechaDeSalida(), LocalDate.of(2019, 11,30));
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }

        @Test
        void getInquilino() {
            assertEquals(tomas.getTodasLasReservas().get(0).getInquilino(), tomas);
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }

        @Test
        void getPropiedad() {
            assertEquals(tomas.getTodasLasReservas().get(0).getPropiedad(), depto);
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }

        @Test
        void isAceptada() {
            assertFalse(tomas.getTodasLasReservas().get(0).isAceptada());
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }

        @Test
        void aceptar() {
            tomas.getTodasLasReservas().get(0).aceptar();
            assertTrue(tomas.getTodasLasReservas().get(0).isAceptada());
            //teardown
            tomas.getTodasLasReservas().remove(0);
        }
    }