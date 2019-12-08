import alquileres.web.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    Usuario tomas;
    Usuario juan;
    Propiedad depto;
    Propiedad casa;
    Servicio agua;
    Servicio luz;
    Servicio gas;
    Servicio internet;
    List<Servicio> servicios1;
    List<Servicio> servicios2;
    Reserva reserva;
    @BeforeEach
    void setUp() {
        agua = new Servicio("Agua");
        luz = new Servicio("Luz");
        internet = new Servicio("Internet");
        gas = new Servicio("Gas");
        servicios1 = new ArrayList<>();
        servicios1.add(agua);
        servicios1.add(luz);
        servicios1.add(internet);
        servicios1.add(gas);
        servicios2 = new ArrayList<>();
        servicios2.add(agua);
        servicios2.add(luz);
        servicios2.add(gas);
        tomas = new Usuario("Tomas", "Tomas@Hotmail.com", 30980092);
        juan = Mockito.mock(Usuario.class);
        depto =new Propiedad("Departamento", "Mar del Plata", servicios1, 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        casa = Mockito.mock(Propiedad.class);
        reserva = new Reserva(tomas, depto, LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 15));
    }

    @Test
    void getTodasLasReservas() {
        tomas.addReserva(new Reserva(tomas, depto, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25)));
        tomas.addReserva(new Reserva(tomas, casa, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25)));
        Assertions.assertEquals(tomas.getTodasLasReservas().size(), 3);
    }

    @Test
    void agendarReserva() {
       tomas.addReserva(new Reserva(tomas, depto, LocalDate.of(2009, 10, 23), LocalDate.of(2009, 10, 25)));
        Assertions.assertEquals(tomas.getTodasLasReservas().get(0).getPropiedad(), depto);
    }

    @Test
    void getNombre(){
        assertEquals(tomas.getNombre(), "Tomas");
    }

    @Test
    void getTelefono(){
    	int telefono = tomas.getTelefono();
        assertEquals(telefono,30980092);
    }
    @Test
    void Email(){
        assertEquals(tomas.getMail(), "Tomas@Hotmail.com");
    }

    @Test
    void aceptarReserva(){
        Email m = Mockito.mock(Email.class);
        tomas.setServicioCorredo(m);
        tomas.aceptarReserva(reserva);
        assertTrue(reserva.isAceptada());
    }
    @Test
    void getPropiedades(){
        assertEquals(0, tomas.getPropiedades().size());
    }
    @Test
    void usuarioSitios(){
        Sitio s = Mockito.mock(Sitio.class);
        tomas.setSitio(s);
        assertEquals(s, tomas.getSitio());
    }
    @Test
    void CrearReserva(){
        Sitio s = Mockito.mock(Sitio.class);
        tomas.setSitio(s);
        assertEquals(s, tomas.getSitio());
    }
}