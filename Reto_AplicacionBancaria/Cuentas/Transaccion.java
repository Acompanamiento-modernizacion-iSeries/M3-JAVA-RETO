package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    private static int contador = 0;
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private int id;

    public Transaccion(String tipo, BigDecimal monto, LocalDateTime fechaHora) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.id = ++contador;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tipo: " + tipo + ", Monto: " + monto + ", Fecha: " + fechaHora;
    }
}
