package db;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transacciones {
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String codigoUnico;

    public Transacciones(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now(); // Captura la fecha y hora actuales
        this.codigoUnico = UUID.randomUUID().toString(); // Genera un código único
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    @Override
    public String toString() {
        return "Transacción [" +
                "Tipo: " + tipo +
                ", Valor: " + monto +
                ", Fecha y Hora: " + fechaHora +
                ", Código Único: " + codigoUnico +
                "]";
    }
}
