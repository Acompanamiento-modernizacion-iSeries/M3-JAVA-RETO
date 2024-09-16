package cuentas;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta {
    public CuentaBasica(String numeroCuenta, BigDecimal saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public void depositar(BigDecimal monto, String tipoDeposito) {
        BigDecimal costo = BigDecimal.ZERO;
        if (tipoDeposito.equals("cajero")) {
            costo = new BigDecimal("2");
        } else if (tipoDeposito.equals("otraCuenta")) {
            costo = new BigDecimal("1.5");
        }
        saldo = saldo.add(monto).subtract(costo);
        registrarTransaccion("Depósito desde " + tipoDeposito, monto);
    }

    @Override
    public void retirar(BigDecimal monto) {
        BigDecimal costo = new BigDecimal("1");
        if (saldo.compareTo(monto.add(costo)) >= 0) {
            saldo = saldo.subtract(monto).subtract(costo);
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