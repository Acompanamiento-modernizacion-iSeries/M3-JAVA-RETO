package transacciones;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private static int contadorSecuencial = 0;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String codigoUnico;

    public Transaccion(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.codigoUnico = generarCodigoUnico();
    }

    private String generarCodigoUnico() {
        String fechaFormateada = fechaHora.format(FORMATO_FECHA);
        contadorSecuencial++;
        return fechaFormateada + String.format("%06d", contadorSecuencial);
    }

    @Override
    public String toString() {
        return String.format("Transacción: %s, Monto: %.2f, Fecha: %s, Código: %s",
                tipo, monto, fechaHora, codigoUnico);
    }
}