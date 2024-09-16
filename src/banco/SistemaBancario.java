package banco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class SistemaBancario {
    private static Scanner scanner = new Scanner(System.in);

    public void iniciarSistema() {
        db.Data.inicializarDatos();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar compra");
            System.out.println("4. Realizar retiro");
            System.out.println("5. Consultar últimas 5 transacciones");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarCompra();
                    break;
                case 4:
                    realizarRetiro();
                    break;
                case 5:
                    consultarTransacciones();
                    break;
                case 6:
                    System.out.println("Gracias por usar el Sistema Bancario. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void consultarSaldo() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = db.Data.getCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual: $" + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void realizarDeposito() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = db.Data.getCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a depositar: ");
            BigDecimal monto = new BigDecimal(scanner.nextLine());
            System.out.println("Seleccione el tipo de depósito:");
            System.out.println("1. Desde sucursal");
            System.out.println("2. Desde cajero");
            System.out.println("3. Desde otra cuenta");
            int tipoDeposito = Integer.parseInt(scanner.nextLine());
            switch (tipoDeposito) {
                case 1:
                    cuenta.depositoDesdeSuccursal(monto);
                    break;
                case 2:
                    cuenta.depositoDesdeCajero(monto);
                    break;
                case 3:
                    cuenta.depositoDesdeOtraCuenta(monto);
                    break;
                default:
                    System.out.println("Tipo de depósito no válido.");
                    return;
            }
            System.out.println("Depósito realizado con éxito.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void realizarCompra() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = db.Data.getCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto de la compra: ");
            BigDecimal monto = new BigDecimal(scanner.nextLine());
            System.out.println("Seleccione el tipo de compra:");
            System.out.println("1. En establecimiento");
            System.out.println("2. En página web");
            int tipoCompra = Integer.parseInt(scanner.nextLine());
            switch (tipoCompra) {
                case 1:
                    cuenta.compraEnEstablecimiento(monto);
                    break;
                case 2:
                    cuenta.compraEnWeb(monto);
                    break;
                default:
                    System.out.println("Tipo de compra no válido.");
                    return;
            }
            System.out.println("Compra realizada con éxito.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void realizarRetiro() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = db.Data.getCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto a retirar: ");
            BigDecimal monto = new BigDecimal(scanner.nextLine());
            cuenta.retiroEnCajero(monto);
            System.out.println("Retiro realizado con éxito.");
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private static void consultarTransacciones() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        Cuenta cuenta = db.Data.getCuenta(numeroCuenta);
        if (cuenta != null) {
            List<Transaccion> ultimasTransacciones = cuenta.getUltimasTransacciones(5);
            System.out.println("Últimas 5 transacciones:");
            for (Transaccion t : ultimasTransacciones) {
                System.out.println(t);
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}
