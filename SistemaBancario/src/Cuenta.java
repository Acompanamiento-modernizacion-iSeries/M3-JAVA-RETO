import java.math.BigDecimal;
import java.util.ArrayList;
public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected ArrayList<Transaccion> historial;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public abstract void depositoSucursal(BigDecimal monto);
    public abstract void depositoCajero(BigDecimal monto);
    public abstract void depositoDesdeOtraCuenta(BigDecimal monto);
    public abstract void compraFisica(BigDecimal monto);
    public abstract void compraWeb(BigDecimal monto);
    public abstract void retiroCajero(BigDecimal monto);

    protected void registrarTransaccion(String tipo, BigDecimal monto) {
        Transaccion transaccion = new Transaccion(tipo, monto);
        historial.add(transaccion);
    }

    public void mostrarHistorial() {
        System.out.println("Historial de transacciones para la cuenta: " + numeroCuenta);
        int transaccionesAMostrar = Math.min(5, historial.size());
        for (int i = historial.size() - transaccionesAMostrar; i < historial.size(); i++) {
            System.out.println(historial.get(i));
        }
    }
}
