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
    void setUp() throws Exception {
        sitio = new Sitio();
        pepito = mock(Inquilino.class);
        tomas = new Inquilino("Tomas", "Tomas@Hotmail.com", 30980092);
        juan = new Propietario("juan", "Juan@outlook.com", 312231);
        roberto = new Propietario("Roberto", "Roberto@Outlook.com", 4450012);
        sitio.registrarse(roberto);
        sitio.registrarse(juan);
        sitio.crearPropiedad("Casa", "Ciudad del Cabo", "Agua, Luz, Gas, Internet", 6, LocalTime.of(7,20), LocalTime.of(22,30), 2000, roberto);
        sitio.crearPropiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        sitio.registrarse(tomas);
        sitio.registrarse(pepito);
        casa = sitio.getListaPropiedades().get(0);
        /*Creo reserva sin confirmar*/
        sitio.crearReserva(pepito, casa, LocalDate.of(2019,10,20), LocalDate.of(2019,10, 25));
        /* Hardcodeo reserva ya confirmada para Pruebas*/
        reserva = new Reserva(tomas, casa, LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 15));
        sitio.getListaDeReservasConfirmadas().add(reserva);
    }
    @Test
    void creoReservaSinConfirmarla() throws Exception {
        //No hay disponibilidad
        Assertions.assertThrows(java.lang.Exception.class,
                () -> sitio.crearReserva(tomas, casa ,LocalDate.of(2019, 10, 12), LocalDate.of(2019, 10, 14)));
    }

    @Test()
    void CreoReservaConDisponibilidad() throws Exception {
        int cant = sitio.getListaDeReservasAConfirmar().size();
        sitio.crearReserva(tomas, casa ,LocalDate.of(2019, 11, 12), LocalDate.of(2019, 11, 14));
        assertEquals(sitio.getListaDeReservasAConfirmar().size(), cant + 1);
    }

    @Test()
    void confirmarReserva() throws Exception {
        int cant = sitio.getListaDeReservasAConfirmar().size();
        sitio.crearReserva(tomas, casa ,LocalDate.of(2019, 11, 12), LocalDate.of(2019, 11, 14));
        assertEquals(sitio.getListaDeReservasAConfirmar().size(), cant + 1);
        int cantConfirmadas = sitio.getListaDeReservasConfirmadas().size();
        sitio.aceptarYCrearReserva(sitio.getListaDeReservasAConfirmar().get(cant - 1));
        assertEquals(sitio.getListaDeReservasConfirmadas().size(), cantConfirmadas + 1);
    }
    @Test
    void getListaPropiedades() {
        assertEquals(sitio.getListaPropiedades().size(), 2);
    }

    @Test
    void getListaDeReservasConfirmadas() {

        assertEquals(sitio.getListaDeReservasConfirmadas().size(), 1);
    }

    @Test
    void getListaDeReservasAConfirmar() {

        assertEquals(sitio.getListaDeReservasConfirmadas().size(), 1);
    }
    @Test
    void setPropietario() {
        int cant = sitio.getListaPropietarios().size();
        Propietario seba = mock(Propietario.class);
        sitio.registrarse(seba);
        assertEquals(sitio.getListaPropietarios().size(), cant + 1);
    }

    @Test
    void setInquilino() {
        int cant = sitio.getListaInquilinos().size();
        Inquilino pepe = mock(Inquilino.class);
        sitio.registrarse(pepe);
        assertEquals(sitio.getListaInquilinos().size(), cant + 1);
    }
    @Test
    void getListaInquilinos() {
        assertEquals(sitio.getListaInquilinos().size(), 2);
    }

    @Test
    void crearPropiedadCorrectamente() throws Exception {
        int cantProp = sitio.getListaPropiedades().size();
        sitio.crearPropiedad("Casa", "Quilmes", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7,20), LocalTime.of(22,30), 5000, roberto);
        assertEquals(sitio.getListaPropiedades().size(), cantProp + 1);
    }
    @Test
    void falloCrearUnaPropiedadQueNoRegistraPropietario() throws Exception {
        Propietario pedro = new Propietario("Pedro", "Pedro@Outlook.com", 11452445);
        int cantProp = sitio.getListaPropiedades().size();
        Assertions.assertThrows(java.lang.Exception.class,
                () -> sitio.crearPropiedad("Casa", "Quilmes", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7,20), LocalTime.of(22,30), 5000, pedro));
    }
    @Test
    void busquedaDePropiedadesEnMarDelPlata() throws Exception {
        sitio.crearPropiedad("Casa", "Mar del Plata", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        assertEquals(sitio.buscarPorFechaYCiudad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Mar del Plata").size(), 2);
    }
    @Test
    void busquedaDePropiedadesEnCiudadDelCabo() throws Exception {
        sitio.crearPropiedad("Casa", "Mar del Plata", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        assertEquals(sitio.buscarPorFechaYCiudad(LocalDate.of(2019, 11, 20), LocalDate.of(2019, 11, 25), "Ciudad del Cabo").size(), 1);
    }
    @Test
    void busquedaDePropiedadesEnCDCParaOctubre() throws Exception {
        sitio.crearPropiedad("Casa", "Mar del Plata", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        assertEquals(sitio.buscarPorFechaYCiudad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Ciudad del Cabo").size(), 0);
    }
    @Test
    void busquedaDePropiedadesEnMDQParaOctubre() throws Exception {
        sitio.crearPropiedad("Casa", "Mar del Plata", "Agua, Luz, Gas, Internet", 4, LocalTime.of(7, 20), LocalTime.of(22, 30), 5000, roberto);
        assertEquals(sitio.buscarPorFechaYCiudad(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 11, 15), "Mar del Plata").size(), 2);
    }
    @Test
    void geterDeListaDeReservasAConfirmar() {
        assertEquals(sitio.getListaDeReservasAConfirmar().size(), 1);
    }
    @Test
    void geterDegetListaPropietarios() {
        assertEquals(sitio.getListaPropietarios().size(), 2);
    }
}