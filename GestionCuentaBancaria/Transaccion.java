import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class Transaccion {
    public String idTransaccion;
    public String tipoTransaccion;
    public BigDecimal montoTransaccion;
    public String fechaHoraTransaccion;

    public Transaccion(String tipoTransaccion, BigDecimal montoTransaccion) {
        this.idTransaccion = UUID.randomUUID().toString();
        this.tipoTransaccion = tipoTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.fechaHoraTransaccion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public void MostrarInformacion() {
        System.out.println("Transaccion " + idTransaccion + ", Tipo de transaccion: " + tipoTransaccion + ", Valor de la transaccion: " + montoTransaccion + ", realizada el: " + fechaHoraTransaccion);
    }
}






