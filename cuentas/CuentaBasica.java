package cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaBasica extends Cuenta {

    private Integer indTrx;
    public CuentaBasica(BigInteger numCuenta, BigDecimal saldo, Integer indTrx) {
        super(numCuenta, saldo);
        this.indTrx=indTrx;
    }
    BigDecimal costoTrx = new BigDecimal(0);

    @Override
    public void depositar(BigDecimal cantidad){
        if (indTrx == 1){ //Indicador de cajero Automatico 2 USD
            costoTrx = BigDecimal.valueOf(10000);
            cantidad.subtract(costoTrx);
        }
        if (indTrx == 2){ //Indicador desde otra cuenta 1.5 USD
            costoTrx = BigDecimal.valueOf(7500);
            cantidad.subtract(costoTrx);
        }
    }
    @Override
    public void compra(BigDecimal cantidad){
       if (indTrx == 3){ //Indicador de compra por pagina Web 5 USD
           costoTrx = BigDecimal.valueOf(25000);
           cantidad.subtract(costoTrx);
       }
    }
    @Override
    public void retirar(BigDecimal cantidad){
        if (indTrx == 4){ //Indicador de retiro 1 USD
            costoTrx = BigDecimal.valueOf(5000);
            cantidad.subtract(costoTrx);
        }
    }



}
