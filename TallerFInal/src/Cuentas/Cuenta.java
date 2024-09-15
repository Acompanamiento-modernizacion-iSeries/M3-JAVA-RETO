package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Cuenta
{
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historial;

    public Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.historial = new ArrayList<>();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {        return numeroCuenta;
    }

    public void agregarTransaccion(String tipo, BigDecimal monto, LocalDateTime fechaHora) {
        historial.add(new Transaccion(tipo, monto, fechaHora, UUID.randomUUID().toString()));
    }

    public List<Transaccion> obtenerHistorial(int cantidad) {
        int size = historial.size();
        if (size > cantidad) {
            return historial.subList(size - cantidad, size);
        } else {
            return new ArrayList<>(historial);
        }
    }

    public abstract void depositoSucursal(BigDecimal monto);
    public abstract void depositoCajero(BigDecimal monto);
    public abstract void depositoOtraCuenta(BigDecimal monto);
    public abstract void compraEstablecimiento(BigDecimal monto);
    public abstract void compraWeb(BigDecimal monto);
    public abstract void retiroCajero(BigDecimal monto);
}
