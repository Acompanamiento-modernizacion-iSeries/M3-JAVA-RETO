package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected String titular;
    protected String numeroCuenta;
    protected BigDecimal saldo;

    public Cuenta(String titular, String numeroCuenta, double saldo) {
        this.titular = titular.toLowerCase();
        this.numeroCuenta = numeroCuenta;
        this.saldo = BigDecimal.valueOf(saldo);
    }

    public String consultarTitular() {
        return titular;
    }

    public String consultarNumCuenta() {
        return numeroCuenta;
    }

    public BigDecimal consultarSaldoDisponible() {
        return saldo;
    }

    public boolean deposito(double vlrTransacc) {
        if (vlrTransacc >= 0) {
            saldo = saldo.add(BigDecimal.valueOf(vlrTransacc));
            return true;
        }
        return false;
    }

    public boolean retiro(double vlrTransacc) {
        if (vlrTransacc >= 0 && vlrTransacc <= saldo.doubleValue()) {
            saldo = saldo.subtract(BigDecimal.valueOf(vlrTransacc));
            return true;
        }
        return false;
    }
}
