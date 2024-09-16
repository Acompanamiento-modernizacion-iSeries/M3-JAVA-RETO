package cuenta;

import db.Transacciones;
import java.math.BigDecimal;
import java.util.List;

public abstract class Cuenta {
    private Integer numero;

    private BigDecimal saldo;

    protected Transacciones transacciones;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void addSaldo(BigDecimal saldo) {
        this.saldo = this.saldo.add(saldo);
    }

    public void subtractSaldo(BigDecimal saldo) {
        this.saldo = this.saldo.subtract(saldo);
    }

    public List<Transacciones.Transaccion> getTransacciones() {
        return transacciones.obtenerUltimasTransacciones(5);
    }

    public Cuenta(Integer numeroCuenta, BigDecimal saldo) {
        this.numero = numeroCuenta;
        this.saldo = saldo;
        this.transacciones = new Transacciones();
    }

    public abstract void depositar(BigDecimal monto, int tipoDeposito);
    public abstract void retirar(BigDecimal monto);
    public abstract void comprar(BigDecimal monto, int tipoCompra);
}
