package RetoFinal;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Cuenta {
    private BigInteger numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(BigInteger numeroCuenta) {
        this.saldo = new BigDecimal(0);
        setnumeroCuenta(numeroCuenta);
    }

    public Cuenta(BigDecimal saldo, BigInteger numeroCuenta) {
        setSaldo(saldo);
        setnumeroCuenta(numeroCuenta);
    }
    public abstract void mostrarInformacion();

    public BigInteger getnumeroCuenta() {
        return numeroCuenta;
    }

    public void setnumeroCuenta(BigInteger numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void depositar(BigDecimal cantidad) {
        saldo = saldo.add(cantidad);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean retirar(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        if (saldo.compareTo(cantidad) >= 0 ){
            this.saldo = saldo.subtract(cantidad);
            return true;
        }
        else {
            System.out.println("No hay fondos suficientes");
            return false;
        }
    }
}
