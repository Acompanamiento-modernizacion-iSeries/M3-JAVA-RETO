package cuentas;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {
    public CuentaPremium(String numeroCuenta, BigDecimal saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public void depositar(BigDecimal monto, String tipoDeposito) {
        saldo = saldo.add(monto);
        registrarTransaccion("Depósito desde " + tipoDeposito, monto);
    }

    @Override
    public void retirar(BigDecimal monto) {
        if (saldo.compareTo(monto) >= 0) {
            saldo = saldo.subtract(monto);
            registrarTransaccion("Retiro", monto);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    @Override
    public void comprar(BigDecimal monto, String tipoCompra) {
        BigDecimal costo = BigDecimal.ZERO;
        if (tipoCompra.equals("web")) {
            costo = new BigDecimal("5");
        }
        if (saldo.compareTo(monto.add(costo)) >= 0) {
            saldo = saldo.subtract(monto).subtract(costo);
            registrarTransaccion("Compra " + tipoCompra, monto);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
}