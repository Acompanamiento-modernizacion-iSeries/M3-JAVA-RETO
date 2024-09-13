package cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import transacciones.Transaccion;

public abstract class Cuenta {

    //Cobros comunes
    protected static final BigDecimal COSTO_DEPOSITO_OTRA_CUENTA = new BigDecimal("1.50");
    protected static final BigDecimal COSTO_COMPRA_WEB = new BigDecimal("5.00");
    protected static final BigDecimal COSTO_RETIRO_CAJERO = new BigDecimal("1.00");
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historialTransacciones;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
    }

    public void depositoDesdeSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion(new Transaccion("Depósito Sucursal", monto));
    }

    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(COSTO_DEPOSITO_OTRA_CUENTA);
        saldo = saldo.add(montoFinal);
        registrarTransaccion(new Transaccion("Depósito Otra Cuenta", montoFinal));
    }

    public void compraEstablecimientoFisico(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion(new Transaccion("Compra Física", monto.negate()));
    }

    public void compraPaginaWeb(BigDecimal monto) {
        BigDecimal montoFinal = monto.add(COSTO_COMPRA_WEB);
        saldo = saldo.subtract(montoFinal);
        registrarTransaccion(new Transaccion("Compra Web", montoFinal.negate()));
    }

    public void retiroCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.add(COSTO_RETIRO_CAJERO);
        saldo = saldo.subtract(montoFinal);
        registrarTransaccion(new Transaccion("Retiro Cajero", montoFinal.negate()));
    }
    public abstract void depositoDesdeCajero(BigDecimal monto);

    public BigDecimal getSaldo() {
        return saldo;
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