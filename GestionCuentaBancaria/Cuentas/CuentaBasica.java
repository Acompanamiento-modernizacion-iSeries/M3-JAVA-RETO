package Cuentas;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta{

    public CuentaBasica(String numeroCuenta, BigDecimal saldo){
        super(numeroCuenta, saldo);
    }

    @Override
    public void Depositar(BigDecimal cantidad, int tipo){
        switch (tipo) {
            case 1:
                super.Depositar(cantidad, 1);
                break;
            case 2:
                BigDecimal costo = new BigDecimal(2);
                BigDecimal saldo = obtenerSaldo();
                saldo = saldo.subtract(costo);
                if (saldo.compareTo(costo) >= 0) {
                    saldo = saldo.add(cantidad);
                    System.out.println("Su nuevo saldo es: " + obtenerSaldo());
                } else if (cantidad.compareTo(costo) >= 0) {
                    cantidad = cantidad.subtract(costo);
                    saldo = saldo.add(cantidad);
                    System.out.println("Su nuevo saldo es: " + obtenerSaldo());
                } else {
                    System.out.println("No hay fondos suficientes para realizar la transaccion");
                }
                System.out.println("No hay fondos suficientes para realizar la transaccion");
                break;
            case 3:
                super.Depositar(cantidad,3);
                break;
            default:
                break;
        }
    }


}