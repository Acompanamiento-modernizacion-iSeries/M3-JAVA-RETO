package banco;

import cuentas.Cuenta;
import db.CuentasDB;
import db.HistorialTransaccionDB;

import java.math.BigDecimal;
import java.util.*;

public class Banco {

    Scanner scanner = new Scanner(System.in);
    Boolean continuar = true;

    List<Cuenta> cuentaEncontrada = new ArrayList<>();

    CuentasDB cuentasDB = new CuentasDB();
    static HistorialTransaccionDB historialTransaccionDB = new HistorialTransaccionDB();

    public Banco(){
    }
    public void iniciar() {

        while (continuar) {
            mostrarMenu();
            switch (seleccionarOpcion(scanner)) {
                case 1:
                    cuentaEncontrada.add(seleccionarCuenta(cuentasDB, scanner));
                    break;
                case 2:
                    depositoDesdeSucursal(cuentaEncontrada, scanner);
                    break;
                case 3:
                    depositoDesdeCajero(cuentaEncontrada, scanner);
                    break;
                case 4:
                    depositoDesdeOtraCuenta(cuentaEncontrada, scanner);
                    break;
                case 5:
                    compraEnEstablecimientoFisico(cuentaEncontrada, scanner);
                    break;
                case 6:
                    compraEnPaginaWeb(cuentaEncontrada, scanner);
                    break;
                case 7:
                    retiroEnCajero(cuentaEncontrada, scanner);
                    break;
                case 8:
                    consultarSaldo(cuentaEncontrada);
                    break;
                case 9:
                    historialTransaccionDB.consultarHistorial();
                    break;
                case 10:
                    mostrarMensaje("Gracias por usar nuestro servicio");
                    continuar = false;
                    break;
                case 0:
                    mostrarMensaje("Esta opción no existe en el menú");
                    break;
                default:
                    mostrarMensaje("Opción no valida");
            }
        }

    }


    public static void mostrarMenu(){
        System.out.println("===========================Bienvenido al sistema Bancario BANK===========================");
        System.out.println("=========================================================================================");
        System.out.println("=========================================================================================");
        System.out.println("============Si desea realizar la selección de una cuenta indique la opción 1 ============");
        System.out.println("============Si desea realizar un desposito desde sucursal indique la opción 2 ===========");
        System.out.println("============Si desea realizar un desposito desde un cajero automatico indique la opción 3");
        System.out.println("============Si desea realizar un desposito desde otra cuenta inqique la opción 4 ========");
        System.out.println("============Si desea realizar una compra en establecimiento fisico indique la opcion 5 ==");
        System.out.println("============Si desea comprar en pagina web indique la opción 6 ==========================");
        System.out.println("============Si desea realizar retiro en cajero indique la opción 7 ======================");
        System.out.println("============Si desea consultar su saldo indique la opción 8 =============================");
        System.out.println("============Para consultar el historial de las ultimas 5 transacciones indique la opción 9");
        System.out.println("============Si desea salir del sistema indique la opción 10 ==============================");
        System.out.println("=========================================================================================");
    }

    public static void depositoDesdeSucursal(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor a depositar desde la sucursal: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal depositoSucursal = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).depositoDesdeSucursal(depositoSucursal);
        agregarTransaccion(cuentaEncontrada, "Deposito desde sucursal", depositoSucursal);
    }

    public static void depositoDesdeCajero(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor a depositar desde el cajero: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal depositoCajero = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).depositoDesdeCajeroAutomatico(depositoCajero);
        agregarTransaccion(cuentaEncontrada, "Deposito desde cajero", depositoCajero);
    }

    public static void depositoDesdeOtraCuenta(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor a depositar desde desde otra cuenta: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal depositoOtraCuenta = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).depositoDesdeOtraCuenta(depositoOtraCuenta);
        agregarTransaccion(cuentaEncontrada, "Deposito desde otra cuenta", depositoOtraCuenta);
    }

    public static void compraEnEstablecimientoFisico(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor de la compra: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal valorCompra = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).compraEnEstablecimientoFisico(valorCompra);
        agregarTransaccion(cuentaEncontrada, "Compra en establecimiento fisico", valorCompra);
    }

    public static void compraEnPaginaWeb(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor de la compra por página web: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal valorCompraPaginaWeb = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).compraEnPaginaWeb(valorCompraPaginaWeb);
        agregarTransaccion(cuentaEncontrada, "Compra en pagina web", valorCompraPaginaWeb);
    }

    public static void retiroEnCajero(List<Cuenta> cuentaEncontrada, Scanner scanner){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Ingrese el valor del retiro: ");
        if (!scanner.hasNextBigDecimal()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return;
        }
        BigDecimal cantidadRetiro = scanner.nextBigDecimal();
        cuentaEncontrada.get(cuentaEncontrada.size()-1).retiroEnCajero(cantidadRetiro);
        agregarTransaccion(cuentaEncontrada, "Retiro en cajero", cantidadRetiro);
    }

    public static void consultarSaldo(List<Cuenta> cuentaEncontrada){
        if(cuentaEncontrada.size() == 0){
            mostrarMensaje("Primero debe seleccionar una cuenta");
            return;
        }
        mostrarMensaje("Su saldo es de: " + cuentaEncontrada.get(cuentaEncontrada.size()-1).obtenerSaldo());
    }

    public static int seleccionarOpcion(Scanner scanner){
        System.out.println("Ingrese la opción que desea usar ");
        int opcion = 0;
        if (scanner.hasNextBigDecimal()) {
            opcion = scanner.nextBigDecimal().intValue();
        } else {
            scanner.next();
        }
        return opcion;
    }

    public static Cuenta seleccionarCuenta(CuentasDB cuentasDB, Scanner scanner){
        System.out.println("Ingrese el número de cuenta que desea seleccionar ");
        int numeroCuenta = 0;
        if (!scanner.hasNextInt()) {
            mostrarMensaje("Valor no valido");
            scanner.next();
            return null;
        }
        numeroCuenta = scanner.nextInt();
        System.out.println("Cuenta seleccionada: " + numeroCuenta);
        return cuentasDB.getCuenta(numeroCuenta);
    }

    public static void agregarTransaccion(List<Cuenta> cuenta, String tipoTransaccion, BigDecimal monto){
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        historialTransaccionDB.agregarTransaccion(tipoTransaccion, monto, date, uuid);
    }
    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

}
