import cuentas.Cuenta;
import cuentas.CuentaBasica;
import cuentas.CuentaPremium;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco{

    //private static String nombre;
    //private BigDecimal saldo;

    private static boolean control ;

    public static int opcion = 0;
    public static String numCuenta;
    public static Scanner scanner = new Scanner(System.in);
    List<Cuenta> cuentas = new ArrayList<>();

    public void iniciar(){
        control = true ;
        menuPrincipal();
        do {
            switch (opcionMenu()) {
                case 1: // GESTION DE CUENTAS
                    menuGestionCuentas();
                    switch (opcionMenu()){
                        case 1: // ADICIONAR CUENTA
                            addCuenta();
                            break;
                        case 2: // LISTAR CUENTAS
                            listarCuentas();
                            break;
                        case 3: //MENU ANTERIOR
                            break;
                        default:
                            System.out.println("La opción seleccionada es invalida.");
                            break;
                    }
                    break;
                case 2: //TRANSACCIONES
                    menuTransacciones();
                    switch (opcionMenu()){
                        case 1: // REALIZAR DEPOSITO DESDE SUCURSAL
                            solicitarCuenta(numCuenta);
                            depositarPorSucursal(numCuenta);
                            break;
                        case 2: //REALIZAR DEPOSITO DESDE CAJERO AUTOMATICO
                            solicitarCuenta(numCuenta);
                            depositarPorCajero(numCuenta);
                            break;
                        case 3: //DEPOSITO DESDE OTRA CUENTA
                            System.out.println("Cuenta origen :");
                            solicitarCuenta(numCuenta);
                            restirar();
                            System.out.println("Cuenta destino :");
                            solicitarCuenta(numCuenta);
                            depositarPorSucursal(numCuenta);
                            break;
                        case 4: //COMPRA EN ESTABLECIMIENTO FISICO
                            solicitarCuenta(numCuenta);
                            compraTiendaFisica(numCuenta);
                            break;
                        case 5: //COMPRA EN PAGINA WEB
                            solicitarCuenta(numCuenta);
                            compraPaginaWeb(numCuenta);
                            break;
                        case 6: //RETIRO EN CAJERO
                            solicitarCuenta(numCuenta);
                            restirar();

                            break;
                        case 7: //MENU ANTERIOR
                            menuPrincipal();
                            break;
                        default:
                            System.out.println("La opción seleccionada es invalida.");
                            break;
                    }
                    break;
                case 3:  // CONSULTAS
                    menuConsultas();
                    switch (opcionMenu()){
                        case 1: //CONSULTAR SALDO
                            solicitarCuenta(numCuenta);
                            consultarSaldo(numCuenta);
                            break;
                        case 2: //HISTORIAL
                            Cuenta.getUltimasTransacciones(5).forEach(System.out::println);
                            break;
                        case 3: //MENU ANTERIOR
                      //      menuPrincipal();
                            break;
                        default:
                            System.out.println("La opción seleccionada es invalida.");
                            break;
                    }
                case 4: //SALIR
                    break;
                default:
                    System.out.print("\nLa opción seleccionada es invalida.");
                    break;
            }
            System.out.print("\nDesea realizar otra operación (S/N) ?");
            String finPrograma = scanner.next();
            switch (finPrograma.toUpperCase()) {
                case "S":
                    menuPrincipal();
                    control = true;
                    break;
                case "N":
                    System.out.println("GRACIAS POR USAR NUESTROS SERVICIOS !!!");
                    control = false;
                    break;
                default:
                    System.out.println("Opcion Invalida. Debe seleccions S o N");
                    break;
            }
        }while (control == true);
    }
    private void depositarOtraCuenta(String numCuenta ) {
        boolean cuentaEncontrada = false;
        if (cuentas.stream().count() == 0){
            System.out.println("No existen cuentas.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal deposito = scanner.nextBigDecimal();
                    cuenta.depositarOtraCuenta(deposito);
                }
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            System.out.println("La cuenta: " + numCuenta + ", no existe");
        }
    }
    private void compraPaginaWeb(String numCuenta) {
        boolean cuentaEncontrada = false;
        if (cuentas.stream().count() == 0){
            System.out.println("No existen cuentas.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                System.out.print("Valor compra: ");
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal valor = scanner.nextBigDecimal();
                    cuenta.comprarWeb(valor);
                }
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            System.out.println("La cuenta: " + numCuenta + ", no existe");
        }
    }
    private void restirar() {
        if(cuentas.size() == 0){
            System.out.println("No existen cuentas.");
        }
        System.out.print("Valor retiro: ");
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal retiro = scanner.nextBigDecimal();
                    cuenta.retiroCajero(retiro);
                } else {
                    System.out.println("El valor ingresado no es valido");
                    scanner.next();
                }
            }
        }
    }
    private void depositarPorCajero(String numCuenta) {
        boolean cuentaEncontrada = false;
        if (cuentas.stream().count() == 0){
            System.out.println("No existen cuentas.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                System.out.print("Valor deposito: ");
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal deposito = scanner.nextBigDecimal();
                    cuenta.depositarCajero(deposito);
                }
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            System.out.println("La cuenta: " + numCuenta + ", no existe");
        }
    }
    public void compraTiendaFisica(String numCuenta){
        boolean cuentaEncontrada = false;
        if (cuentas.stream().count() == 0){
            System.out.println("No existen cuentas.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                System.out.print("Valor compra: ");
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal valor = scanner.nextBigDecimal();
                    cuenta.compraFisico(valor);
                }
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            System.out.println("La cuenta: " + numCuenta + ", no existe");
        }
    }
    private void depositarPorSucursal(String numCuenta) {
        boolean cuentaEncontrada = false;
        if (cuentas.stream().count() == 0){
            System.out.println("No existen cuentas.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                System.out.print("Valor deposito: ");
                if (scanner.hasNextBigDecimal()) {
                    BigDecimal deposito = scanner.nextBigDecimal();
                    cuenta.depositarSucursal(deposito);
                }
                cuentaEncontrada = true;
                break;
            }
        }
        if (!cuentaEncontrada) {
            System.out.println("La cuenta: " + numCuenta + ", no existe");
        }
    }
    public String solicitarCuenta(String numeroCuenta) {
        scanner.nextLine();
        System.out.print("Numero de cuenta     : ");
        numCuenta = scanner.nextLine();
        return numCuenta;
    }
    public static int opcionMenu(){
        System.out.print("\nSeleccione Opcion : ");
        if (scanner.hasNextBigDecimal()) {
            opcion = scanner.nextBigDecimal().intValue();
        } else {
            scanner.next();
        }
        return opcion;
    }
    private static void menuPrincipal(){
        System.out.print("\n BANCOLOMBIA \n");
        System.out.print("1. Gestion de cuentas\n2. Transacciones\n3. Consultas\n4. Salir");
    }
    private static void menuGestionCuentas(){
        System.out.print("\n GESTION DE CUENTAS \n");
        System.out.print("1. Crear cuenta\n2. Listar cuentas \n3. Salir del Menú");
    }
    private static void menuTransacciones(){
        System.out.print("\n TRANSACCIONES \n");
        System.out.print("1. Depósito desde sucursal\n2. Depósito desde cajero automático\n3. Depósito desde otra cuenta\n4. Compra en establecimiento físico\n5. Compra en página web\n6. Retiro en cajero\n");
    }
    private static void menuConsultas(){
        System.out.print("\n CONSULTAS \n");
        System.out.print("1. Consultar saldo\n2. Consultar historial\n3. Menú anterior");
    }
    public void addCuenta(){
        scanner.nextLine();
        System.out.print("Numero de cuenta     : ");
        numCuenta = scanner.nextLine();
        if(numCuenta == null){
            numCuenta = scanner.nextLine();
        }
        System.out.print("Titular de la cuenta : ");
        String nombre = scanner.nextLine();

        if(nombre.isEmpty()){
            nombre = scanner.nextLine();
        }
        System.out.print("Saldo inicial        : ");
        if (scanner.hasNextBigDecimal()) {
            BigDecimal saldoInicial = scanner.nextBigDecimal();
            System.out.println("Tipo de cuenta       : \n1. Cuenta básica\n2. Cuenta premium");
            if (scanner.hasNextBigDecimal()) {
                int tipoCuenta = scanner.nextInt();
                if (tipoCuenta == 1) {
                    CuentaBasica cuenta = new CuentaBasica(numCuenta, nombre, saldoInicial, "Básica");
                    cuentas.add(cuenta);
                }else {
                    CuentaPremium cuenta = new CuentaPremium(numCuenta, nombre, saldoInicial, "Premium");
                    cuentas.add(cuenta);
                }
                System.out.println("CUENTA CREADA !!!");
            }
        }else{
            System.out.println("Valor no valido.");
            scanner.next();
        }
    }
    public void listarCuentas(){
        if (cuentas.stream().count() != 0){
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    public void consultarSaldo(String numCuenta) {
        if(numCuenta == null){
            System.out.println("La cuenta no puede estar en blanco."   );
            numCuenta = scanner.nextLine();
        }else{
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numCuenta)) {
                    System.out.println("Saldo de la cuenta " + numCuenta + ": $" + cuenta.mostrarSaldo());
                    return;
                }
            }
            System.out.println("Cuenta no encontrada.");
        }
    scanner.close();
    }
}
