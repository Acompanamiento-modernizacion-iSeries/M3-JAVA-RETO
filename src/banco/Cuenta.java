package banco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected Cliente cliente;
    protected List<Transaccion> historialTransacciones;

    public Cuenta(String numeroCuenta, BigDecimal saldoInicial, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.cliente = cliente;
        this.historialTransacciones = new ArrayList<>();
    }

    public abstract void depositoDesdeSuccursal(BigDecimal monto);
    public abstract void depositoDesdeCajero(BigDecimal monto);
    public abstract void depositoDesdeOtraCuenta(BigDecimal monto);
    public abstract void compraEnEstablecimiento(BigDecimal monto);
    public abstract void compraEnWeb(BigDecimal monto);
    public abstract void retiroEnCajero(BigDecimal monto);

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Transaccion> getUltimasTransacciones(int n) {
        int size = historialTransacciones.size();
        return historialTransacciones.subList(Math.max(0, size - n), size);
    }

    public void registrarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }
}
