import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class Cuenta {
    protected double saldo;
    protected String numeroCuenta;
    protected Transaccion[] historialTransacciones;
    protected int transaccionesIndex;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historialTransacciones = new Transaccion[5];
        this.transaccionesIndex = 0;
    }

    public double consultarSaldo() {
        return saldo;
    }

    protected void registrarTransaccion(String tipo, double monto) {
        Transaccion nuevaTransaccion = new Transaccion(tipo, monto, LocalDateTime.now(), generarCodigoTransaccion());
        if (transaccionesIndex < 5) {
            historialTransacciones[transaccionesIndex++] = nuevaTransaccion;
        } else {
            System.arraycopy(historialTransacciones, 1, historialTransacciones, 0, historialTransacciones.length - 1);
            historialTransacciones[historialTransacciones.length - 1] = nuevaTransaccion;
        }
    }

    private String generarCodigoTransaccion() {
        return "TX-" + (int)(Math.random() * 100000);
    }

    public void consultarHistorial() {
        System.out.println("Últimas 5 transacciones:");
        Arrays.stream(historialTransacciones).filter(t -> t != null).forEach(System.out::println);
    }

    public abstract void depositoSucursal(double monto);
    public abstract void depositoCajero(double monto);
    public abstract void depositoOtraCuenta(double monto);
    public abstract void compraFisica(double monto);
    public abstract void compraWeb(double monto);
    public abstract void retiroCajero(double monto);
}
