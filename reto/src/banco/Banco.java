package banco;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Banco {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Cuenta cuenta = crearCuenta();
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el monto a depositar:");
                    double montoDeposito = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese el tipo de operación (sucursal, cajero, cuenta):");
                    String tipoDeposito = scanner.nextLine();
                    cuenta.depositar(montoDeposito, tipoDeposito);
                    break;
                case 2:
                    System.out.println("Ingrese el monto a retirar:");
                    double montoRetiro = scanner.nextDouble();
                    scanner.nextLine();
                    cuenta.retirar(montoRetiro);
                    break;
                case 3:
                    System.out.println("Ingrese el monto de la compra:");
                    double montoCompra = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese el tipo de compra (físico, web):");
                    String tipoCompra = scanner.nextLine();
                    cuenta.comprar(tipoCompra, montoCompra);
                    break;
                case 4:
                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;
                case 5:
                    System.out.println("Últimas 5 transacciones:");
                    List<Transaccion> historial = cuenta.getHistorial();
                    int start = Math.max(historial.size() - 5, 0);
                    for (int i = start; i < historial.size(); i++) {
                        System.out.println(historial.get(i));
                    }
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static Cuenta crearCuenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de cuenta (1.básica, 2.premium):");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = scanner.nextLine();

        if (tipo.equalsIgnoreCase("1")) {
            return new CuentaBasica(numeroCuenta);
        } else if (tipo.equalsIgnoreCase("2")) {
            return new CuentaPremium(numeroCuenta);
        } else {
            System.out.println("Tipo de cuenta no válido. Se creará una cuenta básica por defecto.");
            return new CuentaBasica(numeroCuenta);
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Comprar");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Consultar historial de transacciones");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
}

