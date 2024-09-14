import java.util.Scanner;


/*Solución reto curso java Camilo Andres Garcia Cruz
* */
public class BancoApp {
    private static HistorialTransacciones historial = new HistorialTransacciones();
    private static Cuenta cuenta;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBienvenido al sistema de gestión de cuentas bancarias.");
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Seleccione el tipo de cuenta (1: Básica, 2: Premium): ");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        if (tipoCuenta == 1) {
            cuenta = new CuentaBasica(numeroCuenta, saldoInicial);
        } else if (tipoCuenta == 2) {
            cuenta = new CuentaPremium(numeroCuenta, saldoInicial);
        } else {
            System.out.println("Tipo de cuenta no válido.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Depósito");
            System.out.println("2. Retiro");
            System.out.println("3. Compra");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Consultar historial de transacciones");
            System.out.println("6. Salir\n");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarDeposito(scanner);
                    break;
                case 2:
                    realizarRetiro(scanner);
                    break;
                case 3:
                    realizarCompra(scanner);
                    break;
                case 4:
                    consultarSaldo();
                    break;
                case 5:
                    consultarHistorial();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void realizarDeposito(Scanner scanner) {
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de depósito (sucursal, cajero, otraCuenta): ");
        String tipoDeposito = scanner.nextLine();
        cuenta.depositar(monto, tipoDeposito);
        historial.agregarTransaccion(new Transaccion("Depósito " + tipoDeposito, monto));
        System.out.println("Depósito realizado con éxito.");
    }

    private static void realizarRetiro(Scanner scanner) {
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        cuenta.retirar(monto);
        historial.agregarTransaccion(new Transaccion("Retiro", monto));
        System.out.println("Retiro realizado con éxito.");
    }

    private static void realizarCompra(Scanner scanner) {
        System.out.print("Ingrese el monto de la compra: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de compra (fisico, web): ");
        String tipoCompra = scanner.nextLine();
        cuenta.comprar(monto, tipoCompra);
        historial.agregarTransaccion(new Transaccion("Compra " + tipoCompra, monto));
        System.out.println("Compra realizada con éxito.");
    }

    private static void consultarSaldo() {
        System.out.println("Saldo actual: " + cuenta.getSaldo());
    }

    private static void consultarHistorial() {
        System.out.println("Últimas 5 transacciones:");
        for (Transaccion transaccion : historial.obtenerUltimasTransacciones(5)) {
            System.out.println(transaccion);
        }
    }
}