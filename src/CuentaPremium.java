import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaPremium extends Cuenta {
    boolean beneficios;
    public CuentaPremium() {
        super(new BigInteger("0"));
        setBeneficios(true);
    }
    public CuentaPremium(BigDecimal saldo, BigInteger numeroCuenta ) {
        super(saldo, numeroCuenta);
        setBeneficios(true);
    }

    public boolean isBeneficios() {
        return beneficios;
    }

    public void setBeneficios(boolean beneficios) {
        this.beneficios = beneficios;
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta Premium");
        System.out.println("Numero de cuenta"+ getnumeroCuenta());
        System.out.println("saldo" + getSaldo());
        System.out.println("beneficios" + isBeneficios());
    }

}

