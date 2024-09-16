import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta = new CuentaBasica("123456789", 1000.0);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú de Operaciones ---");
            System.out.println("1. Depositar en Sucursal");
            System.out.println("2. Depositar en Cajero Automático");
            System.out.println("3. Depositar desde Otra Cuenta");
            System.out.println("4. Compra en Establecimiento");
            System.out.println("5. Compra Web");
            System.out.println("6. Retiro en Cajero");
            System.out.println("7. Consultar Saldo");
            System.out.println("8. Consultar Historial de Transacciones");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            double monto;

            switch (opcion) {
                case 1:
                    System.out.print("Monto a depositar: ");
                    monto = scanner.nextDouble();
                    cuenta.depositoSucursal(monto);
                    break;
                case 2:
                    System.out.print("Monto a depositar: ");
                    monto = scanner.nextDouble();
                    cuenta.depositoCajero(monto);
                    break;
                case 3:
                    System.out.print("Monto a depositar: ");
                    monto = scanner.nextDouble();
                    cuenta.depositoOtraCuenta(monto);
                    break;
                case 4:
                    System.out.print("Monto de la compra: ");
                    monto = scanner.nextDouble();
                    cuenta.compraFisica(monto);
                    break;
                case 5:
                    System.out.print("Monto de la compra: ");
                    monto = scanner.nextDouble();
                    cuenta.compraWeb(monto);
                    break;
                case 6:
                    System.out.print("Monto a retirar: ");
                    monto = scanner.nextDouble();
                    cuenta.retiroCajero(monto);
                    break;
                case 7:
                    System.out.println("Saldo actual: " + cuenta.consultarSaldo());
                    break;
                case 8:
                    cuenta.consultarHistorial();
                    break;
                case 9:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
