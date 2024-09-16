package Cuentas;

import Logs.LogTrxDB;
import Logs.TipoTransaccion;
import Utils.Util;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta{

    public CuentaPremium(BigDecimal saldo, String numeroCuenta, String titular) {
        super(saldo, numeroCuenta, titular);
    }

    @Override
    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public void setSaldo(BigDecimal saldo) {

    }

    @Override
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public void setNumeroCuenta(String numeroCuenta) {

    }

    @Override
    public String consultarNumeroCuenta() {
        return numeroCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta Premium{" +
                "saldo=" + saldo +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                '}';
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        double costo = 0;
        if (depositar(monto, costo)){
            LogTrxDB.nuevoLog("Deposito en Sucursal:  "+ toString(),TipoTransaccion.DEPOSITO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        double costo = 0;
        if (depositar(monto, costo)){
            LogTrxDB.nuevoLog("Deposito en cajero:  "+ toString(), TipoTransaccion.DEPOSITO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public void depositoOtraCuenta(BigDecimal monto) {
        double costo = 1.5;
        if (depositar(monto, costo)){
            LogTrxDB.nuevoLog("Deposito desde otra Cuenta:  "+ toString(), TipoTransaccion.DEPOSITO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public void compraFisico(BigDecimal monto) {
        double costo = 0;
        if (retirar(monto, costo)){
            LogTrxDB.nuevoLog("Compra en sitio Fisico:  "+ toString(), TipoTransaccion.RETIRO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        double costo = 5;
        if (retirar(monto, costo)){
            LogTrxDB.nuevoLog("Compra en sitio Web:  "+ toString(), TipoTransaccion.RETIRO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        double costo = 1;
        if (retirar(monto, costo)){
            LogTrxDB.nuevoLog("Retiro desde Cajero:  "+ toString(), TipoTransaccion.RETIRO, monto, Util.generaCodigoTrx());
        }
    }

    @Override
    public  boolean depositar(BigDecimal monto, double costo){


        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
            return false;
        }else{
            saldo = saldo.subtract(BigDecimal.valueOf(costo));
            saldo = saldo.add(monto);
            Util.mensaje(" *** DEPOSITO REALIZADO EXITOSAMENTE ***");
            return true;
        }
    }

    @Override
    public  boolean retirar(BigDecimal monto, double costo){


        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
            return false;
        }
        BigDecimal montoTemp = monto.add(BigDecimal.valueOf(costo));
        if (monto.compareTo(montoTemp) > 0){
            Util.mensaje(" *** ALERTA *** \n    - Transacción No exitosa, Saldo Insuficiente.");
            return false;
        }
        dedusirCoste(costo);

        saldo = saldo.subtract(monto);
        Util.mensaje(" *** RETIRO REALIZADO EXITOSAMENTE ***");
        return true;
    }

    @Override
    public void dedusirCoste(double coste) {
        BigDecimal costoTemporal = BigDecimal.ZERO;
        if (coste !=0){
            saldo = saldo.subtract(BigDecimal.valueOf(coste));
        }
    }

}
