import alquileres.web.Inquilino;
import alquileres.web.Propiedad;
import alquileres.web.Propietario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InquilinoTest {
    Inquilino tomas;
    Propietario juan;
    Propiedad depto;
    Propiedad casa;
    @BeforeEach
    void setUp() {
        tomas = new Inquilino("Tomas", "Tomas@Hotmail.com", 30980092);
        juan = Mockito.mock(Propietario.class);
        depto =new Propiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        casa = Mockito.mock(Propiedad.class);
    }

    @Test
    void hacerReserva() {
        tomas.hacerReserva(depto, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25));
        Assertions.assertEquals(tomas.getTodasLasReservas().get(0).getPropiedad(), depto);
    }

    @Test
    void getTodasLasReservas() {
        tomas.hacerReserva(depto, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25));
        tomas.hacerReserva(casa, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25));
        Assertions.assertEquals(tomas.getTodasLasReservas().size(), 2);
    }
}