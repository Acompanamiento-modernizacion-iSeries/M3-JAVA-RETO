package Cuentas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaBasica extends Cuenta
{
    private static final BigDecimal COSTO_DEP_CAJERO = BigDecimal.valueOf(2.0);
    private static final BigDecimal COSTO_DEP_OTRA_CUENTA = BigDecimal.valueOf(1.5);
    private static final BigDecimal COSTO_COMPRA_WEB = BigDecimal.valueOf(5.0);
    private static final BigDecimal COSTO_RETIRO_CAJERO = BigDecimal.valueOf(1.0);

    public CuentaBasica(String numeroCuenta, BigDecimal saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        agregarTransaccion("Depósito desde sucursal", monto, LocalDateTime.now());
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        saldo = saldo.add(monto.subtract(COSTO_DEP_CAJERO));
        agregarTransaccion("Depósito desde cajero", monto, LocalDateTime.now());
    }

    @Override
    public void depositoOtraCuenta(BigDecimal monto) {
        saldo = saldo.add(monto.subtract(COSTO_DEP_OTRA_CUENTA));
        agregarTransaccion("Depósito desde otra cuenta", monto, LocalDateTime.now());
    }

    @Override
    public void compraEstablecimiento(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        agregarTransaccion("Compra en establecimiento físico", monto, LocalDateTime.now());
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto.add(COSTO_COMPRA_WEB));
        agregarTransaccion("Compra en página web", monto, LocalDateTime.now());
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto.add(COSTO_RETIRO_CAJERO));
        agregarTransaccion("Retiro en cajero", monto, LocalDateTime.now());
    }
}

