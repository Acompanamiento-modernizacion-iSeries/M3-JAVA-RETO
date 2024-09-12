package models;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta {

    private BigDecimal costo;
    private String resutadoTransaccion;


    public CuentaPremium(String numeroCuenta, BigDecimal sado) {
        super(numeroCuenta, sado);
    }

    @Override
    public ResutadoTransaccion DepositoDesdeSucursal(BigDecimal valordeposito) {
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        this.saldo = this.saldo.add(valordeposito);
        resultado.asignarResultado("Transacción realizada con exito");
        return resultado;
    }

    @Override
    public ResutadoTransaccion DepositoDesdecajeroAutomatico(BigDecimal valordeposito) {
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        this.saldo = this.saldo.add(valordeposito);
        resultado.asignarResultado("Transacción realizada con exito");
        return resultado;
    }

    @Override
    public ResutadoTransaccion DepositoDesdeOtrasCuentas(BigDecimal valordeposito) {
        this.costo = new BigDecimal(1.5 * 4716.3011);
        BigDecimal valortotalmenosCosto = new BigDecimal(0);
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        resultado.asignarCostoTransaccion(this.costo);
        valortotalmenosCosto = valortotalmenosCosto.add(valordeposito).subtract(this.costo);
        valortotalmenosCosto = valortotalmenosCosto.abs();
        if (this.saldo.compareTo(valortotalmenosCosto) == 1) {
            this.saldo = this.saldo.subtract(this.costo).add(valordeposito);
            resultado.asignarResultado("Transacción realizada con exito");
        }else {
            resultado.asignarResultado("El saldo actual no cubre el valor del costo de");
            System.out.println(resultado.consultarResultado());
        }
        return resultado;
    }

    @Override
    public ResutadoTransaccion CompraEnEstablecimientoFisico(BigDecimal valorCompra) {
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        if (this.saldo.compareTo(valorCompra) == 1) {
            this.saldo = this.saldo.subtract(valorCompra);
            resultado.asignarResultado("Transacción realizada con exito");
        }else {
            resultado.asignarResultado("Saldo insuficiente");
            System.out.println(resultado.consultarResultado());
        }
        return resultado;
    }

    @Override
    public ResutadoTransaccion CompraEnpaginaWeb(BigDecimal valorCompra) {
        this.costo = new BigDecimal(5 * 4716.3011);
        BigDecimal compraMasCosto = new BigDecimal(0);
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        resultado.asignarCostoTransaccion(this.costo);
        compraMasCosto = compraMasCosto.add(valorCompra).add(this.costo);
        compraMasCosto = compraMasCosto.abs();
        if(this.saldo.compareTo(compraMasCosto) == 1) {
            this.saldo = this.saldo.subtract(this.costo).subtract(valorCompra);
            resultado.asignarResultado("Transacción realizada con exito");
        }else {
            resultado.asignarResultado("El saldo actual no cubre el valor de la compra más el costo");
            System.out.println(resultado.consultarResultado());
        }
        return resultado;

    }

    @Override
    public ResutadoTransaccion RetiroEnCajero(BigDecimal valorRetiro) {
        this.costo = new BigDecimal(1 * 4716.3011);
        ResutadoTransaccion resultado = new ResutadoTransaccion();
        resultado.asignarCostoTransaccion(this.costo);
        BigDecimal valorretiromasCosto = new BigDecimal(0);
        valorretiromasCosto = valorretiromasCosto.add(valorRetiro).add(this.costo);
        valorretiromasCosto = valorretiromasCosto.abs();
        if (this.saldo.compareTo(valorretiromasCosto) == 1) {
            this.saldo = this.saldo.subtract(this.costo).subtract(valorRetiro);
            resultado.asignarResultado("Transacción realizada con exito");
        }else {
            resultado.asignarResultado("Saldo insificiente para cubrir el costo de la transacción");
            System.out.println(resultado.consultarResultado());
        }
        return resultado;

    }

}
