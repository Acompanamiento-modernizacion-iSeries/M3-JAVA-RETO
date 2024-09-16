package banco;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta{
    public CuentaBasica(String numeroCuenta, BigDecimal saldoInicial, Cliente cliente) {
        super(numeroCuenta, saldoInicial, cliente);
    }

    @Override
    public void depositoDesdeSuccursal(BigDecimal monto) {
        saldo = saldo.add(monto);
        registrarTransaccion(new Transaccion("Depósito desde sucursal", monto, numeroCuenta));
    }

    @Override
    public void depositoDesdeCajero(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(new BigDecimal("2"));
        registrarTransaccion(new Transaccion("Depósito desde cajero", monto, numeroCuenta));
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal monto) {
        saldo = saldo.add(monto).subtract(new BigDecimal("1.5"));
        registrarTransaccion(new Transaccion("Depósito desde otra cuenta", monto, numeroCuenta));
    }

    @Override
    public void compraEnEstablecimiento(BigDecimal monto) {
        saldo = saldo.subtract(monto);
        registrarTransaccion(new Transaccion("Compra en establecimiento", monto, numeroCuenta));
    }

    @Override
    public void compraEnWeb(BigDecimal monto) {
        saldo = saldo.subtract(monto).subtract(new BigDecimal("5"));
        registrarTransaccion(new Transaccion("Compra en web", monto, numeroCuenta));
    }

    @Override
    public void retiroEnCajero(BigDecimal monto) {
        saldo = saldo.subtract(monto).subtract(new BigDecimal("1"));
        registrarTransaccion(new Transaccion("Retiro en cajero", monto, numeroCuenta));
    }
}
