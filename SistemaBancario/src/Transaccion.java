import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaccion {
    private String tipo;
    private BigDecimal monto;
    private String fecha;
    private String idTransaccion;

    public Transaccion(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto.setScale(2, RoundingMode.HALF_UP);
        this.fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.idTransaccion = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "ID: " + idTransaccion + " | Tipo: " + tipo + " | Monto: " + monto + " | Fecha: " + fecha;
    }
}