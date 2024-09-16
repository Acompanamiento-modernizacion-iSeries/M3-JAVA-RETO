package Logs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private LocalDateTime timestamp; // Almacena fecha y hora
    private TipoTransaccion tipoTransacción;
    private BigDecimal monto;
    private String codigoTransaccion;
    private String description; // Texto descriptivo

    public Log(String description, TipoTransaccion tipoTransaccion, BigDecimal monto, String codigoTransaccion) {
        this.timestamp = LocalDateTime.now();
        this.tipoTransacción = tipoTransaccion;
        this.codigoTransaccion = codigoTransaccion;
        this.description = description;
        this.monto = monto;
    }

    // Método para obtener el log formateado
    public String obtenerDetalleLog() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + timestamp.format(formatter) + "] - " + tipoTransacción
                                                          + " - "     + codigoTransaccion
                                                          + " - "     + description
                                                          + "Monto: " + monto;
    }
}
