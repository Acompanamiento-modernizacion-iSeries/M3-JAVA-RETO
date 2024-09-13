import java.math.BigDecimal;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        CuentaDB.CreaDatos();
        Banco.menu();

        /*for (CuentaBasica number : CuentaDB.getListaCb()) {
            System.out.println(number);
        }
        for (CuentaPremium number1 : CuentaDB.getListaCp()) {
            System.out.println(number1);
        }
        HistoriaTransacciones.CreaHistorico("Deposito",new BigDecimal(100),new Date(),1);
        HistoriaTransacciones.CreaHistorico("Retiro",new BigDecimal(100),new Date(),2);
        */
    }
}
