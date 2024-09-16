package cuentas;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    //Cobros comunes
    protected static final BigDecimal COSTO_DEPOSITO_OTRA_CUENTA = new BigDecimal("1.50");
    protected static final BigDecimal vlrCompraWeb = new BigDecimal("5.00");
    protected static final BigDecimal vlrRetiroCajero = new BigDecimal("1.00");
    private String numCuenta;
    private String titular;
    static BigDecimal saldo;
    private String tipoCuenta;
    protected static List<LogTransacciones> logTransacciones;

    public Cuenta(String numCuenta, String titular, BigDecimal saldo, String tipoCuenta) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.logTransacciones = new ArrayList<>();
    }

    public void comprarWeb(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(vlrCompraWeb);
        saldo = saldo.subtract(montoFinal);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Copmpra pagina web", montoFinal));
    }

    public void depositarSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Depósito Sucursal", monto));
    }

    public void depositarOtraCuenta(BigDecimal monto) {
        BigDecimal montoFinal = monto.subtract(COSTO_DEPOSITO_OTRA_CUENTA);
        saldo = saldo.add(montoFinal);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Depósito Otra Cuenta", montoFinal));
    }

    public void retiroCajero(BigDecimal monto) {
        BigDecimal montoFinal = monto.add(vlrRetiroCajero);
        saldo = saldo.subtract(montoFinal);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Retiro Cajero", montoFinal.negate()));
    }

    public void compraFisico(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        System.out.println("Nuevo saldo    : " + saldo.setScale(4, RoundingMode.HALF_UP));
        logTransacciones(new LogTransacciones("Compra tienda física", monto.negate()));
    }
    public abstract void depositarCajero(BigDecimal monto);


    public static List<LogTransacciones> getUltimasTransacciones(int cantidad) {
        int size = logTransacciones.size();
        return logTransacciones.subList(Math.max(0, size - cantidad), size);
    }

    protected static void logTransacciones(LogTransacciones transaccion) {

        logTransacciones.add(transaccion);
    }
    public String getNumeroCuenta() {
        return numCuenta;
    }

    public BigDecimal mostrarSaldo() {
        return saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Cuenta [Número: " + numCuenta + ", Titular: " + titular + ", Saldo: " + saldo.setScale(2, RoundingMode.HALF_UP) + ", Tipo cuenta: " + tipoCuenta + "]";
    }


}
