package Cuentas;

import Cuentas.Cuenta;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta {

    private BigDecimal COSTO_DEPOSITO_CAJERO = new BigDecimal("2.00");
    private BigDecimal COSTO_DEPOSITO_OTRA_CUENTA = new BigDecimal("1.50");
    private BigDecimal COSTO_RETIRO_CAJERO = new BigDecimal("1.00");
    private BigDecimal COSTO_COMPRA_WEB = new BigDecimal("5.00");

    public CuentaBasica(BigDecimal saldo, String numeroCuenta) {
        super(saldo, numeroCuenta);
    }
    @Override
    public void depositoSucursal(BigDecimal monto){
        saldo = saldo.add(monto);
        registrarTrx("Depósito en Sucursal", monto);
    };
    @Override
    public void depositoCajeroAut(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(COSTO_DEPOSITO_CAJERO);
        registrarTrx("Depósito en Cajero Automático", monto);
    }

    @Override
    public void depositoOtraCta(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(COSTO_DEPOSITO_OTRA_CUENTA);
        registrarTrx("Depósito desde otra cuenta", monto);
    }

    @Override
    public void compraFisica(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTrx("Compra en Establecimnto físico", monto);
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto).subtract(COSTO_COMPRA_WEB);
        registrarTrx("Compra por uns página Web", monto);
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto).subtract(COSTO_RETIRO_CAJERO);
        registrarTrx("Retiro en un cajero automático", monto);
    }
}
