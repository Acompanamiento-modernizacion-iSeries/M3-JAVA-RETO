package cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {

    private BigDecimal saldo;
    private Integer numeroCuenta;

    public Cuenta(BigDecimal saldo, Integer numeroCuenta) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    public void depositoDesdeSucursal(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        saldo = saldo.add(cantidad);
        setSaldo(saldo);
        System.out.println("Su saldo luego del deposito desde sucursal es de: " + saldo);
    }

    public abstract void depositoDesdeCajeroAutomatico(BigDecimal cantidad);

    public void depositoDesdeOtraCuenta(BigDecimal cantidad) {
        BigDecimal saldo = obtenerSaldo();
        saldo = saldo.add(cantidad.subtract(new BigDecimal(1.5)));
        setSaldo(saldo);
        System.out.println("Su saldo luego del deposito desde otra cuenta es de: " + saldo);
    }
    public void compraEnEstablecimientoFisico(BigDecimal cantidad){
        BigDecimal saldo = obtenerSaldo();
        saldo = saldo.subtract(cantidad);
        setSaldo(saldo);
        System.out.println("Su saldo luego de la compra en establecimiento fisico es de: " + saldo);
    };
    public void compraEnPaginaWeb(BigDecimal cantidad){
       BigDecimal saldo = obtenerSaldo();
        saldo = saldo.subtract(cantidad.add(new BigDecimal(5)));
        setSaldo(saldo);
        System.out.println("Su saldo luego de la compra en pagina web es de: " + saldo);
    };

    public void retiroEnCajero(BigDecimal cantidad){
        BigDecimal saldo = obtenerSaldo();
        if (saldo.compareTo(cantidad) < 0){
            System.out.println("No hay fondos suficientes");
        }else {
            saldo = saldo.subtract(cantidad.add(new BigDecimal(1)));
            setSaldo(saldo);
            System.out.println("Su saldo luego del retiro en cajero es de: " + saldo);
        }
    };

    public BigDecimal obtenerSaldo() {
        return saldo;
    };

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    };

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    };

}
