package cuentas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaPremium extends Cuenta {

        public CuentaPremium(String numCuenta, String titular, BigDecimal saldo, String tipoCuenta) {
        super(numCuenta, titular, saldo, tipoCuenta);
    }

    @Override
    public void depositarCajero(BigDecimal monto) {
        saldo = saldo.add(monto);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Depósito Cajero", monto));
    }
}
