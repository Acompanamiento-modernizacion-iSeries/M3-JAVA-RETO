import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoriaTransacciones {
    private static int cuenta;
    private static int totalReg;
    private static int vlConsulta;
    private String tipoTransaccion;
    private BigDecimal montoOperacion;
    private Date fecha;
    private Integer codTransaccion;

    private static List<HistoriaTransacciones> HistorialCb = new ArrayList<>();

    public HistoriaTransacciones(String tipoTransaccion, BigDecimal montoOperacion, Date fecha, Integer codTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
        this.montoOperacion = montoOperacion;
        this.fecha = fecha;
        this.codTransaccion = codTransaccion;
    }

    public static void CreaHistorico(String tipoTransaccion, BigDecimal montoOperacion, Date fecha, Integer codTransaccion){
        HistorialCb.add(new HistoriaTransacciones(tipoTransaccion,montoOperacion,fecha,codTransaccion));
    }

    public static List<HistoriaTransacciones> getHistorial() {
        return HistorialCb;
    }
    @Override
    public String toString() {
        return "Transacción: " + tipoTransaccion + ", Monto: " + montoOperacion + ", Fecha: " + fecha + ", Código: " + codTransaccion;
    }
    public static void ConsultarHistorico(){

        cuenta = 0;
        totalReg = 0;
        vlConsulta = 0;
        totalReg = HistorialCb.size();
        vlConsulta = totalReg-5;

        for (HistoriaTransacciones transaccion : getHistorial()) {
            cuenta++;
                if(cuenta > vlConsulta) {
                    System.out.println(transaccion);
                }
            }
    }

}
