package banco;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String codigo;
    private String numeroCuenta;

    public Transaccion(String tipo, BigDecimal monto, String numeroCuenta) {
        this.tipo = tipo;
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
        this.fecha = LocalDateTime.now();
        this.codigo = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Transacción{" +
                "tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", código='" + codigo + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                '}';
    }
}
