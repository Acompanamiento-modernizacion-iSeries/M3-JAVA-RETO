package cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected List<Transaccion> transacciones = new ArrayList<>();
    protected String numeroCuenta;
    protected BigDecimal saldo;


    public Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public void agregarTransaccion(String tipo, BigDecimal monto) {
        Transaccion transaccion = new Transaccion(tipo, monto);
        transacciones.add(transaccion);
    }

    public void mostrarTransacciones() {
        int limite = Math.min(transacciones.size(), 5);
        for (int i = transacciones.size() - 1; i >= transacciones.size() - limite; i--) {
            System.out.println(transacciones.get(i));
        }
    }

    //Métodos abastractos
    public abstract void depositoSucursal(BigDecimal monto);

    public abstract void depositoCajero(BigDecimal monto);

    public abstract void depositoDesdeOtraCuenta(BigDecimal monto);

    public abstract void compraFisica(BigDecimal monto);

    public abstract void compraWeb(BigDecimal monto);

    public abstract void retiroCajero(BigDecimal monto);

    //Getter and Setter
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}