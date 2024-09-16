package Cuentas;

import Banco.Transaccion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected BigDecimal saldo;
    protected String numeroCuenta;
    protected List<Transaccion> historialTrx;

    public Cuenta(BigDecimal saldo, String numeroCuenta) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.historialTrx = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }
    public abstract void depositoSucursal(BigDecimal monto);
    public abstract void depositoCajeroAut(BigDecimal monto);
    public abstract void depositoOtraCta(BigDecimal monto);
    public abstract void compraFisica(BigDecimal monto);
    public abstract void compraWeb(BigDecimal monto);
    public abstract void retiroCajero(BigDecimal monto);

    protected void registrarTrx(String tipo, BigDecimal monto) {
        historialTrx.add(new Transaccion(tipo, monto));
    }



    public void historialTrx() {
        System.out.println("Historial de las últimas 5 transacciones: ");
        int trx= Math.min(historialTrx.size(), 5);
        for (int i = historialTrx.size() - trx; i < historialTrx.size(); i++) {
            System.out.println(historialTrx.get(i));
        }
    }

}