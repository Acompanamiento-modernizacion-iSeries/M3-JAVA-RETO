package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogTransacciones {
    private static int contId = 0;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String codigoUnico;

    public LogTransacciones(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.codigoUnico = idTransaccion();
    }

    private String idTransaccion() {
        String fechaFormateada = fechaHora.format(FORMATO_FECHA);
        contId++;
        return String.format("%06d", contId);
    }

    @Override
    public String toString() {
        return "Transacción [Transacción: " + tipo + ", Monto: " + monto + ", Fecha: " + fechaHora + ", Tipo Código: " + codigoUnico + "]";
    }
}
