package Cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected String titular;

    public Cuenta(BigDecimal saldo, String numeroCuenta, String titular) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
    }

    public abstract BigDecimal getSaldo() ;

    public abstract void setSaldo(BigDecimal saldo) ;

    public abstract String getNumeroCuenta();

    public abstract void setNumeroCuenta(String numeroCuenta) ;

    //Metodos abstractos
    public abstract void depositoSucursal(BigDecimal monto);

    public abstract void depositoCajero(BigDecimal monto);

    public abstract void depositoOtraCuenta(BigDecimal monto);

    public abstract void compraFisico(BigDecimal monto);

    public abstract void compraWeb(BigDecimal monto);

    public abstract void retiroCajero(BigDecimal monto);

    public abstract boolean depositar(BigDecimal monto, double coste);

    public abstract boolean retirar(BigDecimal monto, double coste);

    public abstract void dedusirCoste(double coste);

    public abstract String consultarNumeroCuenta();

    @Override
    public abstract String toString();

}
