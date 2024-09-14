public abstract class Cuenta {
    protected double saldo;
    protected String numeroCuenta;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public abstract void depositar(double monto, String tipoDeposito);
    public abstract void retirar(double monto);
    public abstract void comprar(double monto, String tipoCompra);
}