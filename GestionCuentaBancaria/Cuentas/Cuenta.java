package Cuentas;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private BigDecimal saldo;
    private String numeroCuenta;



    public Cuenta(String numeroCuenta){
        this.numeroCuenta = numeroCuenta;
        this.saldo = new BigDecimal(0);
    }

    public Cuenta(String numeroCuenta, BigDecimal saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;


    }

    public String numeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void Depositar(BigDecimal cantidad, int tipo){
        if (tipo == 1){
            saldo = saldo.add(cantidad);
            System.out.println("Su nuevo saldo es: " + obtenerSaldo());
        } else if (tipo == 3) {
            BigDecimal costo = new BigDecimal(1.5);
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
        }
            
        }

        public void Comprar(BigDecimal cantidad, boolean esWeb){
        if(esWeb){
            BigDecimal monto = new BigDecimal(5);
            cantidad = cantidad.add(monto);
            Retirar(cantidad);
            System.out.println("Compra realizada exitosamente, su nuevo saldo es:" + obtenerSaldo());
        }
        Retirar(cantidad);
        System.out.println("Compra realizada exitosamente, su nuevo saldo es:" + obtenerSaldo());

    }

    public void Retirar(BigDecimal cantidad){
        BigDecimal saldo = obtenerSaldo();
        BigDecimal comision = new BigDecimal(1);
        saldo = saldo.subtract(comision);
        if (saldo.compareTo(cantidad) >= 0) {
            this.saldo = saldo.subtract(cantidad);
        } else {
            System.out.println("No hay fondos suficientes");
        }
    }


}


