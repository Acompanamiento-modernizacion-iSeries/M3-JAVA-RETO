package cuentas;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {

    private BigDecimal costo;

    public CuentaPremium(BigDecimal saldo, String numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        //este tipo de depósito no tiene costo adicional.
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        //para las cuentas premium el deposito desde cajeros es gratis, no se cobra costo adicional.
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public void depositoOtraCuenta(BigDecimal monto, String numeroCuentaDestino) {
        //costo adicional definido para las cuentas premium, depósito a otra cuenta.
        //se cobrará el 50% de 1.5 por ser premium.
        costo = new BigDecimal(0.75);
        //Validar si el monto del deposito es mayor al costo de la transacción.
        if(monto.compareTo(costo) <= 0){
            throw new UnsupportedOperationException("Depósito a cuenta Número: "+numeroCuentaDestino+ " No realizado. \nEl valor a enviar: $" +monto+
                    " debe ser mayor al costo de la transacción: $"+costo);
        }else if (monto.add(costo).compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("Depósito a cuenta Número: "+numeroCuentaDestino+ " No realizado. \nEl valor a enviar: $" +monto+
                    "más costo Transacción: $" +costo+ " \nSupera el saldo disponible en la cuenta: $"+this.saldo);
        }else{
            this.saldo = this.saldo.subtract(monto).subtract(costo);
        }
    }

    @Override
    public void compraFisico(BigDecimal monto) {
        //validar si el valor de la compra lo cubre el saldo de la cuenta.
        if(monto.compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("¡Compra no realizada! \nEl monto de la compra: $" +monto+ " supera el saldo " +
                    "disponible en la cuenta: $"+this.saldo);
        }else {
            this.saldo = this.saldo.subtract(monto);
        }
    }

    @Override
    public void compraWeb(BigDecimal monto) {
        //costo adicional definido para las cuentas premium compras WEB.
        //se cobrará el 50% de 5.00 por ser premium.
        costo = new BigDecimal(2.5);
        //Validar si el valor de la compra + costo lo cubre el saldo de la cuenta.
        if(monto.add(costo).compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("¡Compra no realizada! \nEl monto de la compra: $" +monto+ " más costo Transacción: $" +costo+
                    " \nSupera el saldo disponible en la cuenta: $"+this.saldo);
        }else{
            this.saldo = this.saldo.subtract(monto).subtract(costo);
        }
    }

    @Override
    public void retiroCajero(BigDecimal monto) {
        //costo adicional definido para las cuentas premium retiros desde cajero.
        //se cobrará el 50% de 1.00 por ser premium.
        costo = new BigDecimal(0.5);
        //Validar si el valor del retiro + costo lo cubre el saldo de la cuenta.
        if(monto.add(costo).compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("¡Retiro no realizado! \nEl monto del retiro: $"+monto+  " más costo Transacción: $" +costo+
                    " \nSupera el saldo disponible en la cuenta: $"+this.saldo);
        }else{
            this.saldo = this.saldo.subtract(monto).subtract(costo);
        }
    }
}