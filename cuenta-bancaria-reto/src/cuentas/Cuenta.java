package cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import transacciones.Transaccion;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historial;

    public Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.historial = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public abstract void depositar(BigDecimal monto, String tipoDeposito);

    public abstract void retirar(BigDecimal monto);

    public abstract void comprar(BigDecimal monto, String tipoCompra);

    protected void registrarTransaccion(String tipo, BigDecimal monto) {
        Transaccion transaccion = new Transaccion(tipo, monto);
        historial.add(transaccion);
        if (historial.size() > 5) {
            historial.remove(0);
        }
    }

    public void mostrarHistorial() {
        for (Transaccion t : historial) {
            System.out.println(t);
        }
    }
}