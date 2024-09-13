package cuentas;

import java.math.BigDecimal;

import transacciones.Transaccion;

public class CuentaPremium extends Cuenta {

    public CuentaPremium(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoDesdeCajero(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion(new Transaccion("Depósito Cajero", monto));
    }
}
