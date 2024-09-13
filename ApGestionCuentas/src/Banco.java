import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class Banco {

    static Scanner scanner = new Scanner(System.in);

    private static Integer cuentaBuscada;
    private static BigDecimal saldoBuscado;
    private static String tipoCuenta;
    private static String tipoTx;
    private static BigDecimal valor;
    private static Integer codigoTx = 0;
    private static int wErr;

    public static void menu() {

        validadatos();
        //instanciar
        CuentaBasica  consulta = new CuentaBasica(saldoBuscado,cuentaBuscada);
        CuentaPremium consulta1 = new CuentaPremium(saldoBuscado,cuentaBuscada);



        while (true){
            System.out.println("\n Seleccione opcion: \n");
            System.out.println("1. Consulta saldo. ");
            System.out.println("2. Consultar historial transacciones. ");
            System.out.println("3. Deposito desde sucursal. ");
            System.out.println("4. Deposito desde cajero. ");
            System.out.println("5. Deposito desde otra cuenta. ");
            System.out.println("6. Compra fisica. ");
            System.out.println("7. Compra virtual. ");
            System.out.println("8. Retiro cajero. ");
            System.out.println("9. Salir. \n");
            System.out.println("Opcion:");
            Integer Op = scanner.nextInt();

            switch (Op) {
                case 1:
                    if(tipoCuenta == "B"){
                        consulta.consultar();
                    }else{
                        consulta1.consultar();
                    }
                    break;
                case 2:
                    HistoriaTransacciones.ConsultarHistorico();
                    break;
                case 3:
                    System.out.println("\n Valor deposito sucursal: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        consulta.DepositoDesdeSucursal(valor);
                    }else{
                        consulta1.DepositoDesdeSucursal(valor);
                    }
                    tipoTx = "Deposito";
                    codigoTx ++;
                    HistoriaTransacciones.CreaHistorico(tipoTx,valor,new Date(),codigoTx);
                    break;
                case 4:
                    System.out.println("\n Valor deposito cajero: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        consulta.DepositoDesdeCajero(valor);
                    }else{
                        consulta1.DepositoDesdeCajero(valor);
                    }
                    tipoTx = "Deposito";
                    codigoTx ++;
                    HistoriaTransacciones.CreaHistorico(tipoTx,valor,new Date(),codigoTx);
                    break;
                case 5:
                    System.out.println("\n Valor deposito otra cuenta: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        consulta.DepositoDesdeCuenta(valor);
                    }else{
                        consulta1.DepositoDesdeCuenta(valor);
                    }
                    tipoTx = "Deposito";
                    codigoTx ++;
                    HistoriaTransacciones.CreaHistorico(tipoTx,valor,new Date(),codigoTx);
                    break;
                case 6:
                    System.out.println("\n Valor compra fisica: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        wErr = consulta.CompraFisica(valor);
                    }else{
                        wErr = consulta1.CompraFisica(valor);
                    }
                    if(wErr == 0) {
                        tipoTx = "Compra";
                        codigoTx++;
                        HistoriaTransacciones.CreaHistorico(tipoTx, valor, new Date(), codigoTx);
                    }
                    break;
                case 7:
                    System.out.println("\n Valor compra virtual: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        wErr = consulta.CompraWeb(valor);
                    }else{
                        wErr = consulta1.CompraWeb(valor);
                    }
                    if(wErr == 0) {
                        tipoTx = "Compra";
                        codigoTx++;
                        HistoriaTransacciones.CreaHistorico(tipoTx, valor, new Date(), codigoTx);
                    }
                    break;
                case 8:
                    System.out.println("\n Valor retiro cajero: ");
                    valor = scanner.nextBigDecimal();
                    if (tipoCuenta == "B") {
                        wErr = consulta.RetiroCajero(valor);
                    }else{
                        wErr = consulta1.RetiroCajero(valor);
                    }
                    if(wErr == 0) {
                        tipoTx = "Retiro";
                        codigoTx++;
                        HistoriaTransacciones.CreaHistorico(tipoTx, valor, new Date(), codigoTx);
                    }
                    break;
                case 9:
                    System.out.println("Fin proceso");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida \n");
            }
        }

    }
    public static boolean validadatos(){
        while (true) {
            System.out.println("\nIngresa el numero de cuenta:");
            cuentaBuscada = scanner.nextInt();
            boolean encontrado = false;
            for (CuentaBasica t : CuentaDB.getListaCb()) {
                if (t.ObtieneNumeroCuenta().equals(cuentaBuscada)) { // Comparar ignorando mayúsculas/minúsculas
                    saldoBuscado = t.ObtieneSaldo();
                    tipoCuenta = "B";
                    encontrado = true;
                    break; // Salir del ciclo una vez encontrado
                }
            }
            if (!encontrado) {
                for (CuentaPremium t : CuentaDB.getListaCp()) {
                    if (t.ObtieneNumeroCuenta().equals(cuentaBuscada)) { // Comparar ignorando mayúsculas/minúsculas
                        saldoBuscado = t.ObtieneSaldo();
                        tipoCuenta = "P";
                        encontrado = true;
                        break; // Salir del ciclo una vez encontrado
                    }
                }
            }
            if (!encontrado){
                System.out.println("Cuenta no encontrada");
            }
            else {
                break;
            }
        }
        return false;
    }
}
