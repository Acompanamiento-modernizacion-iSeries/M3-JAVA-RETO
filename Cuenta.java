import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historialTransacciones;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void agregarTransaccion(String tipo, BigDecimal monto) {
        Transaccion transaccion = new Transaccion(tipo, monto);
        historialTransacciones.add(transaccion);
    }

    public void mostrarHistorialTransacciones() {
        if (historialTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            System.out.println("Historial de Transacciones:");
            for (Transaccion t : historialTransacciones) {
                System.out.println(t);
            }
        }
    }

    public abstract void depositoSucursal(BigDecimal cantidad);
    public abstract void depositoCajeroAutomatico(BigDecimal cantidad);
    public abstract void retiroCajero(BigDecimal cantidad);
    public abstract void depositoDesdeOtraCuenta(BigDecimal cantidad);
    public abstract void compraEstablecimiento(BigDecimal cantidad);
    public abstract void compraPaginaWeb(BigDecimal cantidad);

}
