package cuentas;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta{

    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        agregarTransaccion("Depósito Sucursal", monto);
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(BigDecimal.valueOf(2));// Costo de 2 USD
        agregarTransaccion("Depósito Cajero", monto);
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(BigDecimal.valueOf(1.5));
        // Costo de 1.5 USD
        agregarTransaccion("Depósito desde Otra Cuenta", monto);
    }

    @Override
    public void compraFisica(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        agregarTransaccion("Compra Física", monto);
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto).add(BigDecimal.valueOf(5));
        // Costo adicional de 5 USD
        agregarTransaccion("Compra Web", monto);
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto).add(BigDecimal.valueOf(1));
        // Costo de 1 USD
        agregarTransaccion("Retiro Cajero", monto);
    }

}
