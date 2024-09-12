package modelo;

import java.util.List;

public class HistorialTransaccion {

    private List<Transaccion> listHistorialTransaccion;

    public HistorialTransaccion(List<Transaccion> listTransaccion) {
        this.listHistorialTransaccion = listTransaccion;
    }

    public List<Transaccion> getListHistorialTransaccion() {
        return listHistorialTransaccion;
    }

    public void setListHistorialTransaccion(List<Transaccion> listHistorialTransaccion) {
        this.listHistorialTransaccion = listHistorialTransaccion;
    }
}
