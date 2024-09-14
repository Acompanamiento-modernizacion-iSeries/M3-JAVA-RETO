public class CuentaBasica extends Cuenta {

    public CuentaBasica(String numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositar(double monto, String tipoDeposito) {
        switch (tipoDeposito) {
            case "sucursal":
                saldo += monto;
                break;
            case "cajero":
                saldo += (monto - 2);
                break;
            case "otraCuenta":
                saldo += (monto - 1.5);
                break;
            default:
                System.out.println("Tipo de depósito no válido.");
        }
    }

    @Override
    public void retirar(double monto) {
        saldo -= (monto + 1);
    }

    @Override
    public void comprar(double monto, String tipoCompra) {
        switch (tipoCompra) {
            case "fisico":
                saldo -= monto;
                break;
            case "web":
                saldo -= (monto + 5);
                break;
            default:
                System.out.println("Tipo de compra no válido.");
        }
    }
}