import BaseDatos.CuentasDB;
import Cuentas.CuentaBasica;
import Cuentas.CuentaPremium;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        CuentaBasica cuentabasica = new CuentaBasica( new BigDecimal(100), "1234");
        CuentasDB.LlenarCuentas(cuentabasica);

        CuentaBasica cuentabasica1 = new CuentaBasica( new BigDecimal(2000), "4567");
        CuentasDB.LlenarCuentas(cuentabasica1);

        CuentaBasica cuentabasica2 = new CuentaBasica( new BigDecimal(100), "8956");
        CuentasDB.LlenarCuentas(cuentabasica2);

        /* Premium*/
        CuentaPremium cuentapremium = new CuentaPremium( new BigDecimal(100), "9091");
        CuentasDB.LlenarCuentas(cuentapremium);

        CuentaPremium cuentapremium1 = new CuentaPremium( new BigDecimal(100), "9092");
        CuentasDB.LlenarCuentas(cuentapremium1);

        CuentaPremium cuentapremium2 = new CuentaPremium( new BigDecimal(2000), "1011");
        CuentasDB.LlenarCuentas(cuentapremium2);

        CuentaPremium cuentapremium3 = new CuentaPremium( new BigDecimal(100), "1213");
        CuentasDB.LlenarCuentas(cuentapremium3);

       Menu menu = new Menu(); // crear objeto menu de la clase Menu
       menu.Menu(); //invocar menu

    }


}