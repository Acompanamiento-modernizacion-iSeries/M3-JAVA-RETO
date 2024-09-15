package cuentas;
import db.Transacciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected Integer numeroCuenta;
    public List<Transacciones> historialTransacciones;

    public Cuenta(Integer numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.historialTransacciones = new ArrayList<>();
    }

    public void agregarTransaccion(String tipo, BigDecimal monto) {
        Transacciones transaccion = new Transacciones(tipo, monto);
        historialTransacciones.add(transaccion);
    }

    public abstract void depositoSucursal(BigDecimal cantidad);
    public abstract void depositoCajeroAutomatico(BigDecimal cantidad);
    public abstract void retiroCajero(BigDecimal cantidad);
    public abstract void depositoDesdeOtraCuenta(BigDecimal cantidad);
    public abstract void compraEstablecimiento(BigDecimal cantidad);
    public abstract void compraPaginaWeb(BigDecimal cantidad);

   public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void asignarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer consultarNumeroCuenta() {
        return numeroCuenta;
    }

    public void asignarNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
