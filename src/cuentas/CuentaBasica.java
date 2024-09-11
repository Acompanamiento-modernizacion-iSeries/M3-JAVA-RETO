package cuentas;

import java.math.BigDecimal;

import transacciones.Transaccion;

public class CuentaBasica extends Cuenta {
    private static final BigDecimal COSTO_DEPOSITO_CAJERO = new BigDecimal("2.00");
    private static final BigDecimal COSTO_DEPOSITO_OTRA_CUENTA = new BigDecimal("1.50");
    private static final BigDecimal COSTO_COMPRA_WEB = new BigDecimal("5.00");
    private static final BigDecimal COSTO_RETIRO_CAJERO = new BigDecimal("1.00");

    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoDesdeSuccursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion(new Transaccion("Depósito Sucursal", monto));
    }

    @Override
    public void depositoDesdeCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(COSTO_DEPOSITO_CAJERO);
        saldo = saldo.add(montoFinal);
        registrarTransaccion(new Transaccion("Depósito Cajero", montoFinal));
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(COSTO_DEPOSITO_OTRA_CUENTA);
        saldo = saldo.add(montoFinal);
        registrarTransaccion(new Transaccion("Depósito Otra Cuenta", montoFinal));
    }

    @Override
    public void compraEstablecimientoFisico(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion(new Transaccion("Compra Física", monto.negate()));
    }

    @Override
    public void compraPaginaWeb(BigDecimal monto) {
        BigDecimal montoFinal = monto.add(COSTO_COMPRA_WEB);
        saldo = saldo.subtract(montoFinal);
        registrarTransaccion(new Transaccion("Compra Web", montoFinal.negate()));
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.add(COSTO_RETIRO_CAJERO);
        saldo = saldo.subtract(montoFinal);
        registrarTransaccion(new Transaccion("Retiro Cajero", montoFinal.negate()));
    }
}