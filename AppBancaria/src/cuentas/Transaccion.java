package cuentas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Transaccion {

    private String tipo;
    private BigDecimal monto;
    private Date fecha;
    private String codigoTransaccion;

    public Transaccion(String tipo, BigDecimal monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = new Date();
        this.codigoTransaccion = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Código: " + codigoTransaccion + ", Tipo: " + tipo + ", Monto: " + monto + ", Fecha: " + fecha;
    }

}
