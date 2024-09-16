

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class LogTransaccion extends Cuenta {
    private String Tipotransacción ;
    private BigDecimal costo ;
    private Date fechatransacion;
    private String codigotransacion ;

    public LogTransaccion(BigDecimal saldo, BigInteger numeroCuenta, String tipotransacción, BigDecimal costo ) {
        super(saldo, numeroCuenta);
        setTipotransacción(tipotransacción);
        setCosto(costo);
        setFechatransacion(new Date());
        setCodigotransacion(new Date().toString() + numeroCuenta.toString());

    }

    public String getTipotransacción() {
        return Tipotransacción;
    }

    public void setTipotransacción(String tipotransacción) {
        Tipotransacción = tipotransacción;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Date getFechatransacion() {
        return fechatransacion;
    }

    public void setFechatransacion(Date fechatransacion) {
        this.fechatransacion = fechatransacion;
    }

    public String getCodigotransacion() {
        return codigotransacion;
    }

    public void setCodigotransacion(String codigotransacion) {
        this.codigotransacion = codigotransacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Log transación");
        System.out.println("Numero de cuenta:  "+ getnumeroCuenta());
        System.out.println("saldo: " + getSaldo());
        System.out.println("Costo de transaccion: " + getCosto());
        System.out.println("Tipo de transaccion: " + getTipotransacción());
        System.out.println("Fecha de transaccion: " + getFechatransacion());
        System.out.println("Codigo de transaccion: " + getCodigotransacion());
    }

}