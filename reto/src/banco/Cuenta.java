package banco;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected double saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historial;

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }

    public void agregarTransaccion(String tipo, double monto, String fechaHora, String codigo) {
        historial.add(new Transaccion(tipo, monto, fechaHora, codigo));
    }

    public abstract void depositar(double monto, String tipoOperacion);
    public abstract void retirar(double monto);
    public abstract void comprar(String tipoCompra, double monto);
}
