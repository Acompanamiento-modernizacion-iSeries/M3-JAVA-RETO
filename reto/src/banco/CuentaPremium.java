package banco;

public class CuentaPremium extends Cuenta {

    public CuentaPremium(String numeroCuenta) {
        super(numeroCuenta);
    }

    @Override
    public void depositar(double monto, String tipoOperacion) {
        double costo = 0;
        if (tipoOperacion.equals("cuenta")) {
            costo = 1.5;
        }
        saldo += monto - costo;
        agregarTransaccion("Depósito", monto, "fechaHora", "codigo");
    }

    @Override
    public void retirar(double monto) {
        saldo -= monto; // Sin costo adicional
        agregarTransaccion("Retiro", monto, "fechaHora", "codigo");
    }

    @Override
    public void comprar(String tipoCompra, double monto) {
        if (tipoCompra.equals("web")) {
            saldo -= monto + 5.0;
        } else {
            saldo -= monto;
        }
        agregarTransaccion("Compra", monto, "fechaHora", "codigo");
    }
}

