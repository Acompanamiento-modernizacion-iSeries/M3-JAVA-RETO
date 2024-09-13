package Cuentas;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {
     private BigDecimal costo;
     public CuentaPremium (BigDecimal saldo, String nrocuenta) {
     super (saldo, nrocuenta);

     }

    @Override
     public void depositoSucursal (BigDecimal monto){
        BigDecimal saldo = this.consultarSaldo();
        BigDecimal total = saldo.add(monto);
        super.colocarSaldo(total);

    }
    @Override
    public void depositoCajero (BigDecimal monto) {
    System.out.println("Transaccion no permitida en Cuenta Premium");

    }
    @Override
    public void depositoOtraCuenta (BigDecimal monto) {
        this.costo = new BigDecimal("1.5");
        if (monto.compareTo(costo) <= 0   ) {
            System.out.println("Deposito a otra cuenta no realizado; monto debe ser mayor al costo de la Transaccion");
        }
        else {
            /*cuenta origen*/
            BigDecimal saldo = this.consultarSaldo();
            BigDecimal total = saldo.subtract(monto).subtract(costo);
            super.colocarSaldo(total);

        }

    }
    @Override
    public void compraEstableFisico (BigDecimal monto) {
        BigDecimal saldo = this.consultarSaldo();
        if (saldo.compareTo(monto) < 0) {
            System.out.println("Saldo insuficiente para la compra Web");
        }
        else {
            super.colocarSaldo(saldo.subtract(monto));
            }

    }
    @Override
    public void compraPagWeb (BigDecimal monto) {
        this.costo = new BigDecimal("5");
        BigDecimal saldo = this.consultarSaldo();
        BigDecimal total = monto.add(costo);
        if (total.compareTo(saldo) < 0   ) {
            System.out.println("Compra  Web no realizado; no hay saldo suficiente");
        }
        else {
            super.colocarSaldo(saldo.subtract(total));
        }

    }
    @Override
    public void retiroCajero (BigDecimal monto) {
        this.costo = new BigDecimal("1");
        BigDecimal saldo = this.consultarSaldo();
        BigDecimal total = monto.add(costo);
        if (total.compareTo(saldo) <= 0   ) {
            System.out.println("Retiro no realizado; no hay saldo suficiente");
        }
        else {
            super.colocarSaldo(saldo.subtract(total));
        }

    }


}
