package alquileres.web;

public class WebSiteException extends Exception {
    private int codigoError;

    public WebSiteException(int codigoError){
        super();
        this.codigoError=codigoError;
    }

    @Override
    public String getMessage(){

        String mensaje="";

        switch(codigoError){
            case 01:
                mensaje="No hay disponibilidad para realizar la reserva";
                break;
            case 02:
                mensaje="El Propietario no se encuentra registrado";
                break;
        }

        return mensaje;

    }
}
