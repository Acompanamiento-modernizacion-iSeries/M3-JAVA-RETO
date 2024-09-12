package usuarios;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private String codigoUnico;
    private String tipo;
    private BigDecimal monto;
    private BigDecimal costo;
    private LocalDateTime fechaHora;

    public Transaccion(String tipo, BigDecimal monto, BigDecimal costo) {
        //Generar randomicamente un código único de 10 caracteres.
        this.codigoUnico = UUID.randomUUID().toString().substring(0, 10);
        this.tipo = tipo;
        this.monto = monto;
        this.costo = costo;
        //obtener la fecha y hora actual.
        this.fechaHora = LocalDateTime.now();
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}