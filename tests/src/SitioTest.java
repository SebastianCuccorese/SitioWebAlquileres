import alquileres.web.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.cglib.core.Local;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SitioTest {
    Sitio sitio;
    Inquilino tomas;
    Propietario juan;
    Propiedad depto;
    Propiedad casa;
    Propietario roberto;
    Reserva reserva;
    Inquilino pepito;
    @BeforeEach
    void setUp() {
        sitio = new Sitio();
        pepito = mock(Inquilino.class);
        tomas = new Inquilino("Tomas", "Tomas@Hotmail.com", 30980092);
        juan = new Propietario("juan", "Juan.@outlook.com", 312231);
        roberto = new Propietario("Roberto", "Roberto@Outlook.com", 4450012);
        casa = new Propiedad("Casa", "Ciudad del cabo", "Agua, Luz, Gas, Internet", 6, LocalTime.of(7,20), LocalTime.of(22,30), 2000, roberto);
        depto = new Propiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        reserva = new Reserva(pepito, depto, LocalDate.of(2019, 10, 20), LocalDate.of(2019, 10, 25));
        sitio.ponerPropiedadEnAlquiler(casa);
        sitio.ponerPropiedadEnAlquiler(depto);
        sitio.registrarse(tomas);
        sitio.registrarse(pepito);
        sitio.getListaDeReservas().add(reserva);
    }

    @Test
    void aceptarYCrearReserva() throws Exception {
        //Hay disponibilidad
        sitio.aceptarYCrearReserva(tomas, casa ,LocalDate.of(2009, 12, 8), LocalDate.of(2009, 12, 12));
        assertEquals(sitio.getListaDeReservas().get(1).getInquilino(), tomas);
    }

    @Test()
    void aceptarYCrearReservaRechazada() throws Exception {
        // No hay disponibilidad

        Assertions.assertThrows(java.lang.Exception.class,
                () -> sitio.aceptarYCrearReserva(tomas, depto, LocalDate.of(2019, 10, 24), LocalDate.of(2019, 10, 29)));


    }

  //  @Test No corresponden al hito, y todavia esta en desarrollo
  //  void buscarPropiedad() {
  //  }

    @Test
    void getListaPropiedades() {
        assertEquals(sitio.getListaPropiedades().size(), 2);
    }

    @Test
    void getListaDeReservas() {

        assertEquals(sitio.getListaDeReservas().size(), 1);
    }

    @Test
    void getListaInquilinos() {
        assertEquals(sitio.getListaInquilinos().size(), 2);
    }
}