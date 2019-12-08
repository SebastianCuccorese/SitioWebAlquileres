import alquileres.web.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        sitio.addPropiedad(casa);
        sitio.addPropiedad(depto);
        sitio.registrarse(tomas);
        sitio.registrarse(pepito);
        casa = sitio.getListaPropiedades().get(0);
        Reserva reserva1 = new Reserva(pepito, casa, LocalDate.of(2019,10,20), LocalDate.of(2019,10, 25));
        sitio.addReserva(reserva1);
        reserva = new Reserva(tomas, casa, LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 15));
        sitio.getListaDeReservas().add(reserva);
        administrador.agregarServicio(agua);
        administrador.agregarServicio(internet);
        administrador.agregarServicio(gas);
        administrador.agregarServicio(luz);
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
        sitio.addPropiedad(casa2);
        assertEquals(sitio.getListaPropiedades().size(), cantProp + 1);
    }
    @Test
    void falloCrearUnaPropiedadQueNoRegistraPropietario() throws Exception {
        Usuario pedro = new Usuario("Pedro", "Pedro@Outlook.com", 11452445);
        int cantProp = sitio.getListaPropiedades().size();
        Propiedad casa3 = new Propiedad("Casa", "Quilmes", servicios1, 4, LocalTime.of(7,20), LocalTime.of(22,30), 5000, pedro);
        Assertions.assertThrows(java.lang.Exception.class,
                () -> sitio.addPropiedad(casa3), "El Propietario no se encuentra registrado");
    }
    @Test
    void busquedaDePropiedadesEnMarDelPlata() throws Exception {
        Propiedad casa4 = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.addPropiedad(casa4);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Mar del Plata").size(), 2);
    }
    @Test
    void busquedaDePropiedadesEnCiudadDelCabo() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.addPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Ciudad del Cabo").size(), 1);
    }
    @Test
    void busquedaDePropiedadesEnCDCParaOctubre() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.addPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Ciudad del Cabo").size(), 1);
    }
    @Test
    void busquedaDePropiedadesEnMDQParaOctubre() throws Exception {
        Propiedad casaAux = new Propiedad("Casa", "Mar del Plata", servicios1, 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        sitio.addPropiedad(casaAux);
        assertEquals(administrador.BuscarPropiedad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Mar del Plata").size(), 2);
    }
    @Test
    void getterDeListaDeReservas() {
        assertEquals(sitio.getListaDeReservas().size(), 2);
    }
    @Test
    void getterDeListaUsuarios() {
        assertEquals(sitio.getListaUsuarios().size(), 4);
    }

    @Test
    void hayDisponibilidadPorTenerLaFechaDeIngresoPosterior() {
        assertTrue(sitio.hayDisponibilidad(casa, LocalDate.of(2019, 10, 16), LocalDate.of(2019, 10, 20)));
    }
    @Test
    void hayDisponibilidadPorTenerLaFechaDeIngresoPrevio(){
        assertTrue(sitio.hayDisponibilidad(casa, LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 9)));
    }

    @Test
    void noHayDisponibilidadPorFechaOcupada(){
        assertFalse(sitio.hayDisponibilidad(casa, LocalDate.of(2019, 10, 11), LocalDate.of(2019, 10, 15)));
    }

    @Test
    void AdminAgregaServicios() {
        administrador.crearServicio("Calefaccion");
        assertEquals(sitio.getListaDeServicios().size(), 5);
    }
    @Test
    void AgergoReservaSinPoder() throws WebSiteException{
        tomas.setSitio(sitio);
        Assertions.assertThrows(WebSiteException.class,()-> tomas.crearReserva(casa,LocalDate.of(2019,10,10) ,LocalDate.of(2019,10,14)),"No hay disponibilidad para realizar la reserva");
    }
}