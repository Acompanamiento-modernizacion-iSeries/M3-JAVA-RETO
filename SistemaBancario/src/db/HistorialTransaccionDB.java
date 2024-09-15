package db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HistorialTransaccionDB {

    String tipoTransaccion;
    BigDecimal monto;
    Date fechaHora;
    UUID codigoTransaccion;

    public HistorialTransaccionDB(){
    }

    public HistorialTransaccionDB(String tipoTransaccion, BigDecimal monto, Date fechaHora, UUID codigoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.codigoTransaccion = codigoTransaccion;
    }
    private static List<HistorialTransaccionDB> historialList = new ArrayList<HistorialTransaccionDB>();

    public void agregarTransaccion(String tipoTransaccion, BigDecimal monto, Date fechaHora, UUID codigoTransaccion){
        historialList.add(new HistorialTransaccionDB(tipoTransaccion, monto, fechaHora, codigoTransaccion));
        System.out.println("Historial de transaccion agregado exitosamente");
    }

    public void consultarHistorial() {
        int totalElementos = historialList.size();
        int inicio = Math.max(totalElementos - 5, 0);
        System.out.println("Últimos 5 elementos o menos si hay menos de 5:");
        for (int i = inicio; i < totalElementos; i++) {
            System.out.println("Transaccion # "+i);
            System.out.println("Tipo de transaccion: " + historialList.get(i).tipoTransaccion);
            System.out.println("Monto: " + historialList.get(i).monto);
            System.out.println("Fecha y hora: " + historialList.get(i).fechaHora);
            System.out.println("Codigo de transaccion: " + historialList.get(i).codigoTransaccion);
        }
    }

}
