package Cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historialTransacciones;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.saldo = saldoInicial;
        this.numeroCuenta = numeroCuenta;
        this.historialTransacciones = new ArrayList<>();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public abstract void depositar(BigDecimal monto, String tipoDeposito);

    public abstract void retirar(BigDecimal monto);

    public abstract void comprar(BigDecimal monto, String tipoCompra);

    public void registrarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }

    public List<Transaccion> obtenerUltimasTransacciones(int cantidad) {
        int inicio = Math.max(0, historialTransacciones.size() - cantidad);
        return historialTransacciones.subList(inicio, historialTransacciones.size());
    }
}
