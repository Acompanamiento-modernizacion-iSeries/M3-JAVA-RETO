package Cuentas;

import Cuentas.Cuenta;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {

    public CuentaPremium(BigDecimal saldo, String numeroCuenta) {
        super(saldo, numeroCuenta);
    }
    @Override
    public void depositoSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTrx("Depósito en Sucursal", monto);
    }

    @Override
    public void depositoCajeroAut(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTrx("Depósito en Cajero Automático", monto);
    }

    @Override
    public void depositoOtraCta(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTrx("Depósito desde otra cuenta", monto);
    }

    @Override
    public void compraFisica(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTrx("Compra en Establecimiento Físico", monto);
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTrx("Compra en Página Web", monto);
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTrx("Retiro en Cajero Automático", monto);
    }
}
