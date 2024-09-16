package Banco;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String codigoUnico;

    public Transaccion(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.codigoUnico = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Transacción [Tipo: " + tipo + ", Monto: $" + monto + ", Fecha: " + fechaHora + ", Código: " + codigoUnico + "]";
    }
}
