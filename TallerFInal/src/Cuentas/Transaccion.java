package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion
{
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String codigo;

    public Transaccion(String tipo, BigDecimal monto, LocalDateTime fechaHora, String codigo) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.format("Código: %s, Tipo: %s, Monto: %s, Fecha y Hora: %s",
                codigo, tipo, monto, fechaHora);
    }

}
