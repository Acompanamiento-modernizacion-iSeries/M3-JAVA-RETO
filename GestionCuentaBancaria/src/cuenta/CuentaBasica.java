package cuenta;

import db.Transacciones;
import java.math.BigDecimal;

public class CuentaBasica extends Cuenta {
    public CuentaBasica(Integer numeroCuenta, BigDecimal saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositar(BigDecimal monto, int tipoDeposito) {
        switch (tipoDeposito) {
            case 1:
                transacciones.agregarTransaccion(new Transacciones.Transaccion("Deposito", "sucursal", monto, BigDecimal.ZERO));
                addSaldo(monto);
                break;
            case 2:
                transacciones.agregarTransaccion(new Transacciones.Transaccion("Deposito", "cajero", monto, BigDecimal.valueOf(2)));
                addSaldo(monto.subtract(BigDecimal.valueOf(2)));
                break;
            case 3:
                transacciones.agregarTransaccion(new Transacciones.Transaccion("Deposito", "otraCuenta", monto, BigDecimal.valueOf(1.5)));
                addSaldo(monto.subtract(BigDecimal.valueOf(1.5)));
                break;
            default:
                System.out.println("Tipo de depósito no válido.");
        }
    }

    @Override
    public void retirar(BigDecimal monto) {
        transacciones.agregarTransaccion(new Transacciones.Transaccion("Retiro", null, monto, BigDecimal.ONE));
        subtractSaldo(monto.add(BigDecimal.ONE));
    }

    @Override
    public void comprar(BigDecimal monto, int tipoCompra) {
        switch (tipoCompra) {
            case 1:
                transacciones.agregarTransaccion(new Transacciones.Transaccion("Compra", "fisico", monto, BigDecimal.ZERO));
                subtractSaldo(monto);
                break;
            case 2:
                transacciones.agregarTransaccion(new Transacciones.Transaccion("Compra", "web", monto, BigDecimal.valueOf(5)));
                subtractSaldo(monto.add(BigDecimal.valueOf(5)));
                break;
            default:
                System.out.println("Tipo de compra no válida.");
        }
    }
}
