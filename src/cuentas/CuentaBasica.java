package cuentas;

import java.math.BigDecimal;

import transacciones.Transaccion;

public class CuentaBasica extends Cuenta {
    private static final BigDecimal COSTO_DEPOSITO_CAJERO = new BigDecimal("2.00");

    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoDesdeCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(COSTO_DEPOSITO_CAJERO);
        saldo = saldo.add(montoFinal);
        registrarTransaccion(new Transaccion("Depósito Cajero", montoFinal));
    }
}