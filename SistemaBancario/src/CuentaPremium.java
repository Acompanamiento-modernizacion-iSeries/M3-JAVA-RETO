import java.math.BigDecimal;
public class CuentaPremium extends Cuenta {
    private static final BigDecimal COSTO_COMPRA_WEB = new BigDecimal("5.00");

    public CuentaPremium(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion("Depósito Sucursal", monto);
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion("Depósito Cajero Automático", monto);
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion("Depósito desde otra cuenta", monto);
    }

    @Override
    public void compraFisica(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion("Compra física", monto);
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto.add(COSTO_COMPRA_WEB));
        registrarTransaccion("Compra web", monto);
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion("Retiro en cajero", monto);
    }
}
