package cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Cuenta {
    private BigInteger numeroCuenta;
    BigDecimal saldo;

    public Cuenta(BigInteger numeroCuenta, BigDecimal saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        //this.saldo = new BigDecimal(saldo);
    }

    public Cuenta(BigInteger numeroCuenta){
        this.numeroCuenta = numeroCuenta;
        this.saldo = new BigDecimal(0);
    }

    public BigInteger consultarNumCuenta() {
        return numeroCuenta;
    }

    public void asignarNumCuenta(BigInteger numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal obtenerSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal saldo){
        this.saldo = saldo.add(saldo);
    }

    public void retirar(BigDecimal cantidad){
        BigDecimal saldo = obtenerSaldo();
        if(saldo.compareTo(cantidad) >= 0){
            this.saldo = saldo.subtract(cantidad);
        }else{
            System.out.println("No hay fondos suficientes!");
        }
    }

    public void compra(BigDecimal cantidad){
        BigDecimal saldo = obtenerSaldo();
        if(saldo.compareTo(cantidad) >= 0){
            this.saldo = saldo.subtract(cantidad);
        }else{
            System.out.println("No hay fondos suficientes para realizar la trx!");
        }
    }
    public void consultarSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }

}
