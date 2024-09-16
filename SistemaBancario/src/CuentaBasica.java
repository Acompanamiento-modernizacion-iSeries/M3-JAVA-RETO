import java.math.BigDecimal;

class CuentaBasica extends Cuenta {
    private static final BigDecimal COSTO_CAJERO = new BigDecimal("2.00");
    private static final BigDecimal COSTO_OTRA_CUENTA = new BigDecimal("1.50");
    private static final BigDecimal COSTO_RETIRO_CAJERO = new BigDecimal("1.00");

    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion("Depósito Sucursal", monto);
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(COSTO_CAJERO);
        registrarTransaccion("Depósito Cajero Automático", monto);
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(COSTO_OTRA_CUENTA);
        registrarTransaccion("Depósito desde otra cuenta", monto);
    }

    @Override
    public void compraFisica(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion("Compra física", monto);
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto.add(new BigDecimal("5.00")));
        registrarTransaccion("Compra web", monto);
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto.add(COSTO_RETIRO_CAJERO));
        registrarTransaccion("Retiro en cajero", monto);
    }
}



