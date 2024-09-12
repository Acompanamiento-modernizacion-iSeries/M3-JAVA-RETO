package RetoFinal;
import java.math.BigDecimal;
import java.math.BigInteger;

public  class CuentaBásica extends Cuenta {

    boolean beneficios;

    public CuentaBásica(BigDecimal saldo, BigInteger numeroCuenta ) {
       super(saldo, numeroCuenta);
        setBeneficios(false);

    }
    public CuentaBásica(){
        super(new BigInteger("0"));
        setBeneficios(false);
    }

    public boolean isBeneficios() {
        return beneficios;
    }

    public void setBeneficios(boolean beneficios) {
        this.beneficios = beneficios;
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Cuenta Basica");
        System.out.println("Numero de cuenta:  "+ getnumeroCuenta());
        System.out.println("saldo: " + getSaldo());
        System.out.println("beneficios: " + isBeneficios());
    }


}
