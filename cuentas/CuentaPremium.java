package cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaPremium extends Cuenta {

    Integer indTrx;
    BigDecimal costoTrx = new BigDecimal(0);

    public CuentaPremium(BigInteger numCuenta, BigDecimal saldo, Integer indTrx) {
        super(numCuenta, saldo);
        this.indTrx = indTrx;
    }
    @Override
    public void depositar(BigDecimal cantidad){
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
}
