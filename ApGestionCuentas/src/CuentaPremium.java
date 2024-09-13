import java.math.BigDecimal;
//Cuenta que permite operaciones sin costo adicional
public class CuentaPremium extends Cuenta{
    public CuentaPremium(BigDecimal saldo, Integer numeroCuenta) {
        super(saldo, numeroCuenta);
    }

    @Override
    public void DepositoDesdeCajero(BigDecimal valor) {
        BigDecimal saldo = ObtieneSaldo();
        BigDecimal valorIni = saldo.add(valor);
        CreaSaldo(valorIni);
    }
}
