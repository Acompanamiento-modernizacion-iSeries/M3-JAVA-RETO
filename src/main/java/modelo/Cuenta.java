package modelo;

import java.math.BigDecimal;

public abstract class Cuenta {

    private BigDecimal saldo;
    private Integer numeroCuenta;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void depositoSucursal(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    public void depositoCajeroAuto(BigDecimal valor){
        this.saldo = this.saldo.add(valor).subtract(new BigDecimal("2.0"));
    }

    public void depositoOtraCuenta(BigDecimal valor){
        this.saldo = this.saldo.add(valor).subtract(new BigDecimal("1.5"));
    }

    public void compraFisico(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }

    public void compraWeb(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor).subtract(new BigDecimal("5.0"));
    }

    public void retiroCajero(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor).subtract(new BigDecimal("1.0"));
    }
}
