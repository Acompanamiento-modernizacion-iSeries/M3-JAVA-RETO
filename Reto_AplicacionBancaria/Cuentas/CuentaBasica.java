package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaBasica extends Cuenta {

    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositar(BigDecimal monto, String tipoDeposito) {
        BigDecimal costo = BigDecimal.ZERO;
        if (tipoDeposito.equals("cajero")) {
            costo = new BigDecimal("2.0");
        } else if (tipoDeposito.equals("otraCuenta")) {
            costo = new BigDecimal("1.5");
        }
        saldo = saldo.add(monto).subtract(costo);

        registrarTransaccion(new Transaccion("Depósito", monto, LocalDateTime.now()));
    }

    @Override
    public void retirar(BigDecimal monto) {
        BigDecimal costo = new BigDecimal("1.0");
        saldo = saldo.subtract(monto.add(costo));

        registrarTransaccion(new Transaccion("Retiro", monto, LocalDateTime.now()));
    }

    @Override
    public void comprar(BigDecimal monto, String tipoCompra) {
        BigDecimal costo = BigDecimal.ZERO;
        if (tipoCompra.equals("web")) {
            costo = new BigDecimal("5.0");
        }
        saldo = saldo.subtract(monto.add(costo));

        registrarTransaccion(new Transaccion("Compra", monto, LocalDateTime.now()));
    }
}
