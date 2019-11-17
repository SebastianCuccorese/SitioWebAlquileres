import alquileres.web.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.cglib.core.Local;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SuppressWarnings("unused")
class SitioTest {
    Sitio sitio;
    Usuario tomas;
    Usuario juan;
    Propiedad depto;
    Propiedad casa;
    Usuario roberto;
    Reserva reserva;
    Usuario pepito;
    Usuario admin;
    Administrador administrador;
    Servicio agua;
    Servicio luz;
    Servicio gas;
    Servicio internet;
    List<Servicio> servicios1;
    List<Servicio> servicios2;
    @BeforeEach
    void setUp() throws Exception {
        admin = new Usuario("admin", "Juan@outlook.com", 312231);
        sitio = new Sitio();
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
        administrador = new Administrador(admin, sitio);
        pepito = mock(Usuario.class);
        tomas = new Usuario("Tomas", "Tomas@Hotmail.com", 30980092);
        juan = new Usuario("juan", "Juan@outlook.com", 312231);
        roberto = new Usuario("Roberto", "Roberto@Outlook.com", 4450012);
        sitio.registrarse(roberto);
        sitio.registrarse(juan);
        casa = new Propiedad("Casa", "Ciudad del Cabo", servicios1, 6, LocalTime.of(7,20), LocalTime.of(22,30), 2000, roberto);
        depto = new Propiedad("Departamento", "Mar del Plata", servicios2, 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        sitio.crearPropiedad(casa);
        sitio.crearPropiedad(depto);
        sitio.registrarse(tomas);
        sitio.registrarse(pepito);
        casa = sitio.getListaPropiedades().get(0);
        sitio.crearReserva(pepito, casa, LocalDate.of(2019,10,20), LocalDate.of(2019,10, 25));
        reserva = new Reserva(tomas, casa, LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 15));
        sitio.getListaDeReservas().add(reserva);
        administrador.agregarServicio(agua);
        administrador.agregarServicio(internet);
        administrador.agregarServicio(gas);
        administrador.agregarServicio(luz);
    }
    @Test
    void propietarioNoAceptaReserva() throws Exception {
    	roberto.noAceptaReservas();
    	Assertions.assertFalse(roberto.getAceptacion());
    	int cant = sitio.getListaDeReservas().size();
    	Assertions.assertThrows(java.lang.Exception.class,
                () ->  sitio.aceptarYCrearReserva(sitio.getListaDeReservas().get(cant - 1)), "Su reserva ha sido rechazada");
    }
    @Test
    void CreoReservaConDisponibilidad() throws Exception {
        int cant = sitio.getListaDeReservas().size();
        sitio.crearReserva(tomas, casa ,LocalDate.of(2019, 11, 12), LocalDate.of(2019, 11, 14));
        assertEquals(sitio.getListaDeReservas().size(), cant + 1);
    }
    @Test
    void getListaPropiedades() {
        assertEquals(sitio.getListaPropiedades().size(), 2);
    }
    @Test
    void getListaDeReserva() {
        assertEquals(sitio.getListaDeReservas().size(), 2);
    }
    @Test
    void setPropietario() {
        int cant = sitio.getListaUsuarios().size();
        Usuario seba = mock(Usuario.class);
        sitio.registrarse(seba);
        assertEquals(sitio.getListaUsuarios().size(), cant + 1);
    }
    @Test
    void setInquilino() {
        int cant = sitio.getListaUsuarios().size();
        Usuario pepe = mock(Usuario.class);
        sitio.registrarse(pepe);
        assertEquals(sitio.getListaUsuarios().size(), cant + 1);
    }
    @Test
    void crearPropiedadCorrectamente() throws Exception {
        int cantProp = sitio.getListaPropiedades().size();
        Propiedad casa2 = new Propiedad("Casa", "Quilmes", servicios1, 4, LocalTime.of(7,20), LocalTime.of(22,30), 5000, roberto);
        sitio.crearPropiedad(casa2);
        assertEquals(sitio.getListaPropiedades().size(), cantProp + 1);
    }
    @Test
    void falloCrearUnaPropiedadQueNoRegistraPropietario() throws Exception {
        Usuario pedro = new Usuario("Pedro", "Pedro@Outlook.com", 11452445);
        int cantProp = sitio.getListaPropiedades().size();
        Propiedad casa3 = new Propiedad("Casa", "Quilmes", servicios1, 4, LocalTime.of(7,20), LocalTime.of(22,30), 5000, pedro);
        Assertions.assertThrows(java.lang.Exception.class,
                () -> sitio.crearPropiedad(casa3), "El Propietario no se encuentra registrado");
    }
    @Test
    void busquedaDePropiedadesEnMarDelPlata() throws Exception {
        Propiedad casa4 = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.crearPropiedad(casa4);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Mar del Plata").size(), 2);
    }
    @Test
    void busquedaDePropiedadesEnCiudadDelCabo() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.crearPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Ciudad del Cabo").size(), 1);
    }
    @Test
    void busquedaDePropiedadesEnCDCParaOctubre() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.crearPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Ciudad del Cabo").size(), 0);
    }
    @Test
    void busquedaDePropiedadesEnMDQParaOctubre() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.crearPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Mar del Plata").size(), 2);
    }
    @Test
    void geterDeListaDeReservas() {
        assertEquals(sitio.getListaDeReservas().size(), 2);
    }
    @Test
    void geterDegetListaUsuarios() {
        assertEquals(sitio.getListaUsuarios().size(), 4);
    }
    /*@Test
    void hayDisponibilidadPorNoSolaparfechas() {
        assertFalse(sitio.hayDisponibilidad(casa, LocalDate.of(2019, 10, 11), LocalDate.of(2019, 10, 14)));
    }*/
    @Test
    void hayDisponibilidadPorTenerLaFechaDeIngresoNoEsPosterior() {
        assertTrue(sitio.hayDisponibilidad(casa, LocalDate.of(2019, 10, 14), LocalDate.of(2019, 10, 19)));
    }
    @Test
    void AdminAgregaServicios() {
        administrador.crearServicio("Calefaccion");
        assertEquals(sitio.getListaDeServicios().size(), 5);
    }
    @Test
    void aceptarReservas() {
        roberto.aceptaReservas();
        assertTrue(roberto.getAceptacion());
    }
}