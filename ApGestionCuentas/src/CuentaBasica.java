import java.math.BigDecimal;
//Cuenta sin beneficios
public class CuentaBasica extends Cuenta{
    public CuentaBasica(BigDecimal saldo, Integer numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void DepositoDesdeCajero(BigDecimal valor) {
        BigDecimal saldo = ObtieneSaldo();
        saldo = saldo.add(valor);
        saldo = saldo.subtract(BigDecimal.valueOf(2));
    }
}
