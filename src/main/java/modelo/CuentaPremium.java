package modelo;

import java.math.BigDecimal;

public class CuentaPremium extends Cuenta{

    public void depositoCajeroAuto(BigDecimal valor){
        BigDecimal saldoAux = getSaldo();
        saldoAux = saldoAux.add(valor);
        setSaldo(saldoAux);
    }
}
