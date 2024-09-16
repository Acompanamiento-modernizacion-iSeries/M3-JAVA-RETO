package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaPremium extends Cuenta {

    public CuentaPremium(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositar(BigDecimal monto, String tipoDeposito) {
        saldo = saldo.add(monto);
        registrarTransaccion(new Transaccion("Depósito", monto, LocalDateTime.now()));
    }

    @Override
    public void retirar(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion(new Transaccion("Retiro", monto, LocalDateTime.now()));
    }

    @Override
    public void comprar(BigDecimal monto, String tipoCompra) {
        BigDecimal costo = BigDecimal.ZERO;
        if (tipoCompra.equals("web")) {
            costo = new BigDecimal("5.0"); // Costo de seguro de robo.
        }
        saldo = saldo.subtract(monto.add(costo));

        registrarTransaccion(new Transaccion("Compra", monto, LocalDateTime.now()));
    }
}
