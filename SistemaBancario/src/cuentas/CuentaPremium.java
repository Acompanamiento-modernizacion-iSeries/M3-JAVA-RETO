package cuentas;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta{
    public CuentaPremium(BigDecimal saldo, Integer numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void depositoDesdeCajeroAutomatico(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        saldo = saldo.add(cantidad);
        setSaldo(saldo);
        System.out.println("Su saldo luego del deposito desde cajero automatico para su cuenta premium es de: " + saldo);
    }

}
