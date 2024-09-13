import java.math.BigDecimal;

public abstract class Cuenta {
    private BigDecimal saldo;
    private Integer numeroCuenta;
    private BigDecimal vlCompara;
    private int wErr;

    public Cuenta(BigDecimal saldo, Integer numeroCuenta) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal ObtieneSaldo() {
        return saldo;
    }

    public void CreaSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Integer ObtieneNumeroCuenta() {
        return numeroCuenta;
    }

    public void CreaNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void DepositoDesdeSucursal(BigDecimal valor){
        saldo = saldo.add(valor);
    }
    public void DepositoDesdeCajero(BigDecimal valor){
    }
    public void DepositoDesdeCuenta(BigDecimal valor){
        saldo = saldo.add(valor);
        saldo = saldo.subtract(BigDecimal.valueOf(1.5));
    }
    public int CompraFisica(BigDecimal valor){
        wErr = 0;
        if(valor.compareTo(saldo) > 0){
            System.out.println("Fondos insuficientes para compra");
            wErr = 1;
        }else{
            saldo = saldo.subtract(valor);
        }
        return wErr;
    }
    public int CompraWeb(BigDecimal valor){
        wErr = 0;
        vlCompara = BigDecimal.valueOf(0);
        vlCompara = saldo.add(BigDecimal.valueOf(5));
        if(vlCompara.compareTo(saldo) > 0){
            System.out.println("Fondos insuficientes para compra web");
            wErr = 1;
        }else{
            saldo = saldo.subtract(valor);
            saldo = saldo.subtract(BigDecimal.valueOf(5));
        }
        return wErr;
    }
    public int RetiroCajero(BigDecimal valor){
        wErr = 0;
        vlCompara = BigDecimal.valueOf(0);
        vlCompara = saldo.add(BigDecimal.valueOf(1));
        if(vlCompara.compareTo(saldo) > 0){
            System.out.println("Fondos insuficientes para retiro");
            wErr = 1;
        }else{
            saldo = saldo.subtract(valor);
            saldo = saldo.subtract(BigDecimal.valueOf(1));
        }
        return wErr;
    }
    public void consultar(){
        System.out.println(numeroCuenta + " su saldo actual es: " + saldo);
    }
    @Override
    public String toString() {
        return "CuentaBasica: " +
                "cuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo;
    }
}
