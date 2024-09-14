public class CuentaPremium extends Cuenta {

    public CuentaPremium(String numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }

    @Override
    public void depositar(double monto, String tipoDeposito) {
        saldo += monto; // No hay costos adicionales para depósitos en CuentaPremium
    }

    @Override
    public void retirar(double monto) {
        saldo -= monto; // No hay costos adicionales para retiros en CuentaPremium
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