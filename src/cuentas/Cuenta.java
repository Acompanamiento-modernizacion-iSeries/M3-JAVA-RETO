package cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;
import transacciones.Transaccion;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historialTransacciones;
    protected Banco banco;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
    }

    public abstract void depositoDesdeSuccursal(BigDecimal monto);

    public abstract void depositoDesdeCajero(BigDecimal monto);

    public abstract void depositoDesdeOtraCuenta(BigDecimal monto);

    public abstract void compraEstablecimientoFisico(BigDecimal monto);

    public abstract void compraPaginaWeb(BigDecimal monto);

    public abstract void retiroCajero(BigDecimal monto);

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public List<Transaccion> getUltimasTransacciones(int cantidad) {
        int size = historialTransacciones.size();
        return historialTransacciones.subList(Math.max(0, size - cantidad), size);
    }

    protected void registrarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }

    public String getTipoCuenta() {
        return this.getClass().getSimpleName();
    }
}