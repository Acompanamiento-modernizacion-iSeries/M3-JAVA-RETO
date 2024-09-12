package cuentas;

import java.math.BigDecimal;

public class CuentaBasica extends Cuenta {

    private BigDecimal costo;

    public CuentaBasica(BigDecimal saldo, String numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void depositoSucursal(BigDecimal monto) {
        //este tipo de depósito no tiene costo adicional.
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public void depositoCajero(BigDecimal monto) {
        //costo adicional definido para las cuentas basicas depósito desde cajero.
        costo = new BigDecimal(2);
        //Validar si el monto del deposito es mayor al costo de la transacción.
        if(monto.compareTo(costo) <= 0){
            throw new UnsupportedOperationException("¡Depósito no realizado! \nEl valor del depósito :$" +monto+ " debe ser mayor al costo de la " +
                                                    "transacción: $" +costo);
        }else{
            this.saldo = this.saldo.add(monto).subtract(costo);
        }
    }

    @Override
    public void depositoOtraCuenta(BigDecimal monto, String numeroCuentaDestino) {
        //costo adicional definido para las cuentas basicas, depósito a otra cuenta.
        costo = new BigDecimal(1.5);
        //Validar si el monto del deposito es mayor al costo de la transacción.
        if(monto.compareTo(costo) <= 0){
            throw new UnsupportedOperationException("Depósito a cuenta Número: "+numeroCuentaDestino+ " No realizado. \nEl valor a enviar: $" +monto+
                                               " debe ser mayor al costo de la transacción: $" +costo);
        }else if (monto.add(costo).compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("Depósito a cuenta Número: "+numeroCuentaDestino+ " No realizado \nEl valor a enviar: $" +monto+
                                               " más costo transacción: $" +costo+ " \nSupera el saldo disponible en la cuenta: $" +this.saldo);
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
        //costo adicional definido para las cuentas basicas compras WEB.
        costo = new BigDecimal(5);
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
        //costo adicional definido para las cuentas basicas retiros desde cajero.
        costo = new BigDecimal(1);
        //Validar si el valor del retiro + costo lo cubre el saldo de la cuenta.
        if(monto.add(costo).compareTo(this.saldo) > 0){
            throw new UnsupportedOperationException("¡Retiro no realizado! \nEl monto del retiro: $" +monto+ " más costo Transacción: $" +costo+
                                                    " \nSupera el saldo disponible en la cuenta: $"+this.saldo);
        }else{
            this.saldo = this.saldo.subtract(monto).subtract(costo);
        }
    }

}

