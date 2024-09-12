package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;

    public Cuenta(BigDecimal saldo, String numeroCuenta) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    //Metodos abstractos
    public abstract void depositoSucursal(BigDecimal monto);

    public abstract void depositoCajero(BigDecimal monto);

    public abstract void depositoOtraCuenta(BigDecimal monto, String numeroCuentaDestino);

    public abstract void compraFisico(BigDecimal monto);

    public abstract void compraWeb(BigDecimal monto);

    public abstract void retiroCajero(BigDecimal monto);
}
