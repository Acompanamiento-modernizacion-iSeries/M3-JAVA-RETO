package models;

import java.math.BigDecimal;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected BigDecimal saldo;

    public Cuenta(String numeroCuenta, BigDecimal sado) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = sado;
    }

    public String consularNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal consultarSado() {return saldo;}

    abstract public ResutadoTransaccion DepositoDesdeSucursal(BigDecimal valordeposito);
    abstract public ResutadoTransaccion  DepositoDesdecajeroAutomatico(BigDecimal saldo);
    abstract public ResutadoTransaccion DepositoDesdeOtrasCuentas(BigDecimal saldo);
    abstract public ResutadoTransaccion CompraEnEstablecimientoFisico(BigDecimal saldo);
    abstract public ResutadoTransaccion CompraEnpaginaWeb(BigDecimal saldo);
    abstract public ResutadoTransaccion RetiroEnCajero(BigDecimal valorRetiro);


}
