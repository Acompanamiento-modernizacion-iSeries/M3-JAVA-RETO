public class CuentaPremium extends Cuenta {

    public CuentaPremium(String numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
    }


    public void depositoSucursal(double monto) {
        saldo += monto;
        registrarTransaccion("Depósito Sucursal", monto);
    }


    public void depositoCajero(double monto) {
        saldo += monto;
        registrarTransaccion("Depósito Cajero Automático", monto);
    }


    public void depositoOtraCuenta(double monto) {
        saldo += monto;
        registrarTransaccion("Depósito Desde Otra Cuenta", monto);
    }


    public void compraFisica(double monto) {
        saldo -= monto;
        registrarTransaccion("Compra en Establecimiento", monto);
    }


    public void compraWeb(double monto) {
        saldo -= (monto + 5);
        registrarTransaccion("Compra Web (Con Seguro)", monto);
    }


    public void retiroCajero(double monto) {
        saldo -= monto;
        registrarTransaccion("Retiro en Cajero", monto);
    }
}