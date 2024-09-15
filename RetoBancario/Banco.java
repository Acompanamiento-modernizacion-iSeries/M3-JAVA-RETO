import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import cuentas.*;


public class Banco {
    private static Scanner sc = new Scanner(System.in);
    private static List<Cuenta> cuentas = new ArrayList<>();
    public static void main(String[] args) {
        int numero;

        while (true) {
            switch (showMenu()) {
                case 1:
                    //Agregar cuenta
                    agregarCuenta();
                    break;
                case 2:
                    //Consulta de saldo
                    consultarSaldo();
                    break;
                case 3:
                    //Hacer depósito
                    depositos();
                    break;
                case 4:
                    //Hacer retiro
                    retiros();
                    break;
                case 5:
                    //Hacer compras
                    compras();
                    break;
                case 6:
                    //Consultar últimas cinco transacciones
                    System.out.println("ingrese el número de cuenta a consultar");
                    numero = sc.nextInt();
                    consultarUltimasTransacciones(numero);
                    break;
                case 7:
                    //Salir
                    System.out.println("Gracias por usar nuestros servicios");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Seleccione una opción valida.");
                    break;
            }
        }

    }
    public static int showMenu() {
        System.out.println("\n=================================");
        System.out.println("***Cuenta Bancaria***");
        System.out.println("=================================");
        System.out.println("Por favor seleccione una opción: ");
        System.out.println("1. Agregar cuenta");
        System.out.println("2. Consultar saldo");
        System.out.println("3. Hacer deposito");
        System.out.println("4. Hacer retiro");
        System.out.println("5. Hacer compra");
        System.out.println("6. Mostrar últimas cinco transacciones");
        System.out.println("7. Salir");
        System.out.println("=================================\n");
        return sc.nextInt();
    }

    public static void agregarCuenta() {
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Básica");
        System.out.println("2. Cuenta Premium");
        int tipoCuenta = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el número de cuenta: ");
        Integer numeroCuenta = sc.nextInt();
        System.out.print("Ingrese el saldo inicial: ");
        BigDecimal saldoInicial = sc.nextBigDecimal();

        if (tipoCuenta == 1) {
            cuentas.add(new CuentaBasica(numeroCuenta, saldoInicial));
        } else if (tipoCuenta == 2) {
            cuentas.add(new CuentaPremium(numeroCuenta, saldoInicial));
        } else {
            System.out.println("Tipo de cuenta inválido.");
        }
    }

    public static void consultarSaldo(){
        System.out.print("Ingrese el número de cuenta: ");
        int numeroCuenta = sc.nextInt();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual: " + cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static Cuenta obtenerCuentaPorNumero(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.consultarNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public static void depositos() {
        System.out.print("Ingrese el número de cuenta: ");
        int numeroCuenta = sc.nextInt();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto del depósito: ");
            BigDecimal monto = sc.nextBigDecimal();
            System.out.println("Seleccione el canal para el deposito :");
            System.out.println("1. Sucursal");
            System.out.println("2. Cajero automático");
            System.out.println("3. Transferencia");
            int tipoDeposito = sc.nextInt();

            switch (tipoDeposito) {
                case 1:
                    cuenta.depositoSucursal(monto);
                    break;
                case 2:
                    cuenta.depositoCajeroAutomatico(monto);
                    break;
                case 3:
                    cuenta.depositoDesdeOtraCuenta(monto);
                    break;
                default:
                    System.out.println("Canal invalido para deposito.");
                    break;
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void retiros() {
        System.out.print("Ingrese el número de cuenta: ");
        int numeroCuenta = sc.nextInt();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto del retiro: ");
            BigDecimal monto = sc.nextBigDecimal();
            cuenta.retiroCajero(monto);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void compras() {
        System.out.print("Ingrese el número de cuenta: ");
        int numeroCuenta = sc.nextInt();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto de la compra: ");
            BigDecimal monto = sc.nextBigDecimal();
            System.out.println("Seleccione el canal de compra:");
            System.out.println("1. Establecimiento físico");
            System.out.println("2. Página web");
            int tipoCompra = sc.nextInt();

            switch (tipoCompra) {
                case 1:
                    cuenta.compraEstablecimiento(monto);
                    break;
                case 2:
                    cuenta.compraPaginaWeb(monto);
                    break;
                default:
                    System.out.println("Canal de compra inválido.");
                    break;
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public static void consultarUltimasTransacciones(int numeroCuenta) {
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        int j = 0;
        if (cuenta != null) {
            int totalTransacciones = cuenta.historialTransacciones.size();

            if (totalTransacciones == 0) {
                System.out.println("No hay transacciones registradas.");
            } else {

                if (totalTransacciones > 5 ) {
                    System.out.println("***Últimas 5 transacciones: ***");
                    j = Math.max(0, totalTransacciones - 5);
                }
                else
                {
                    System.out.println("***Últimas " + totalTransacciones + " transacciones: ***");
                    j = 0;
                }

                for (int i = j; i < totalTransacciones; i++) {
                    System.out.println(cuenta.historialTransacciones.get(i));
                }
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}
