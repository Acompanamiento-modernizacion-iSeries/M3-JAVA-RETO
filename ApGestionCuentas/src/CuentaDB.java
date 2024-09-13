import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentaDB {
    private static List<CuentaBasica> listaCb = new ArrayList<>();
    private static List<CuentaPremium> listaCp = new ArrayList<>();
    public static List<CuentaPremium> getListaCp() {
        return listaCp;
    }
       public static List<CuentaBasica> getListaCb() {
        return listaCb;
    }

    public static void CreaDatos(){
        listaCb.add(new CuentaBasica(new BigDecimal(100), 1));
        listaCb.add(new CuentaBasica(new BigDecimal(200), 2));
        listaCb.add(new CuentaBasica(new BigDecimal(300), 3));
        listaCp.add(new CuentaPremium(new BigDecimal(400), 4));
        listaCp.add(new CuentaPremium(new BigDecimal(500), 5));
        listaCp.add(new CuentaPremium(new BigDecimal(600), 6));
   }

}
