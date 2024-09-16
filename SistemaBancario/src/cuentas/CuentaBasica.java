package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaBasica extends Cuenta {

    private static final BigDecimal valorDepositoCajero = new BigDecimal("2.00");

    public CuentaBasica(String numCuenta, String titular, BigDecimal saldo, String tipoCuenta) {
        super(numCuenta, titular, saldo, tipoCuenta);
    }

    @Override
    public void depositarCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(valorDepositoCajero);
        saldo = saldo.add(montoFinal);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Deposito cajero", montoFinal ));
    }
}

