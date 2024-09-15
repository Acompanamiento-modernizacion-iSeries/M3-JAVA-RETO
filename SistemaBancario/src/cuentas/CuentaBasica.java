package cuentas;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta{

    public CuentaBasica(BigDecimal saldo, Integer numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void depositoDesdeCajeroAutomatico(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        saldo = saldo.add(cantidad.subtract(new BigDecimal(2)));
        setSaldo(saldo);
        System.out.println("Su saldo luego del deposito desde cajero automatico para su cuenta basica es de: " + saldo);
    }

}
