package transacciones;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private String id;
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;

    public Transaccion(String tipo, BigDecimal monto) {
        this.id = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tipo: " + tipo + ", Monto: " + monto + ", Fecha: " + fechaHora;
    }
}