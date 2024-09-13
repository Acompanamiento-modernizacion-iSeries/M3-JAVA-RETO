package Cuentas;

import java.math.BigDecimal;
import java.util.Date;

public class Transaccion {
    private String tipoTransaccion;
    private BigDecimal monto;
    private Date fechaTrx;

    private Integer codigotrx;

    // Constructor
    public Transaccion(String tipoTransaccion, BigDecimal monto) {
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fechaTrx = new Date();
        this.codigotrx = (int)Math.random() * 1000;
    }

    public String consultarTipoTransaccion()
       {
        return this.tipoTransaccion;
        }

    public void colocarTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal consultarMonto() {
        return monto;
    }

    public void colocarMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date consultarFechaTrx() {
        return fechaTrx;
    }

    public void colocarFechaTrx(Date fechaTrx) {
        this.fechaTrx = fechaTrx;
    }

    public Integer consultarCodigotrx() {
        return codigotrx;
    }

    public void colocarCodigotrx(Integer codigotrx) {
        this.codigotrx = codigotrx;
    }
}
