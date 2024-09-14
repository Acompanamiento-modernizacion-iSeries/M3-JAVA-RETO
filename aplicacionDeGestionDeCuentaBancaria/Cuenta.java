import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class Cuenta {
    protected double saldo;
    protected String numeroCuenta;
    protected List<Transaccion> transacciones;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.transacciones = new ArrayList<>();
    }

    public abstract void depositoSucursal(double monto);
    public abstract void depositoCajero(double monto);
    public abstract void depositoCuenta(double monto);
    public abstract void compraFisico(double monto);
    public abstract void compraWeb(double monto);
    public abstract void retiroCajero(double monto);

    public double consultarSaldo() {
        return this.saldo;
    }

    public List<Transaccion> consultarHistorial() {
        int size = transacciones.size();
        return transacciones.subList(Math.max(size - 5, 0), size);
    }

    protected void registrarTransaccion(String tipo, double monto) {
        transacciones.add(new Transaccion(tipo, monto));
    }
}