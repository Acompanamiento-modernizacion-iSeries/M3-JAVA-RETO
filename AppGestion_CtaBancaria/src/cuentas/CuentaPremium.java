package cuentas;

public class CuentaPremium extends Cuenta {

    private static final Double COMISION_DEPOSITO_SUC = 0.0;
    private static final Double COMISION_DEPOSITO_CAJERO = 0.0;
    private static final Double COMISION_DEPOSITO_OTRA_CTA = 1.0;
    private static final Double COMISION_COMPRA_FISICA = 0.0;
    private static final Double COMISION_COMPRA_VIRTUAL = 3.0;
    private static final Double COMISION_RETIRO_CAJERO = 0.0;

    public CuentaPremium(String titular, String numeroCuenta, double saldo) {
        super(titular, numeroCuenta, saldo);
    }

    public static Double comisionDepositoSuc() {
        return COMISION_DEPOSITO_SUC;
    }

    public static Double comisionDepositoCajAuto() {
        return COMISION_DEPOSITO_CAJERO;
    }

    public static Double comisionDepositoOtraCta() {
        return COMISION_DEPOSITO_OTRA_CTA;
    }

    public static Double comisionCompraFisica() {
        return COMISION_COMPRA_FISICA;
    }

    public static Double comisionCompraVirtual() {
        return COMISION_COMPRA_VIRTUAL;
    }

    public static Double comisionRetiroCajero() {
        return COMISION_RETIRO_CAJERO;
    }
}
