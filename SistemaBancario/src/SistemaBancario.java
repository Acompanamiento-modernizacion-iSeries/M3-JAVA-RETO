import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class SistemaBancario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta;

        System.out.println("¿Qué tipo de cuenta desea crear? (1) Básica (2) Premium:");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        System.out.print("Ingrese el saldo inicial: ");
        BigDecimal saldoInicial = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);

        if (tipoCuenta == 1) {
            cuenta = new CuentaBasica(numeroCuenta, saldoInicial);
        } else {
            cuenta = new CuentaPremium(numeroCuenta, saldoInicial);
        }

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Depósito Sucursal");
            System.out.println("2. Depósito Cajero");
            System.out.println("3. Depósito desde otra cuenta");
            System.out.println("4. Compra física");
            System.out.println("5. Compra web");
            System.out.println("6. Retiro Cajero");
            System.out.println("7. Consultar saldo");
            System.out.println("8. Consultar historial");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el monto a depositar en sucursal: ");
                    BigDecimal montoDepositoSucursal = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.depositoSucursal(montoDepositoSucursal);
                    break;
                case 2:
                    System.out.print("Ingrese el monto a depositar en cajero: ");
                    BigDecimal montoDepositoCajero = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.depositoCajero(montoDepositoCajero);
                    break;
                case 3:
                    System.out.print("Ingrese el monto a depositar desde otra cuenta: ");
                    BigDecimal montoDepositoOtraCuenta = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.depositoDesdeOtraCuenta(montoDepositoOtraCuenta);
                    break;
                case 4:
                    System.out.print("Ingrese el monto de la compra física: ");
                    BigDecimal montoCompraFisica = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.compraFisica(montoCompraFisica);
                    break;
                case 5:
                    System.out.print("Ingrese el monto de la compra web: ");
                    BigDecimal montoCompraWeb = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.compraWeb(montoCompraWeb);
                    break;
                case 6:
                    System.out.print("Ingrese el monto a retirar en cajero: ");
                    BigDecimal montoRetiro = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
                    cuenta.retiroCajero(montoRetiro);
                    break;
                case 7:
                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;
                case 8:
                    cuenta.mostrarHistorial();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);

        scanner.close();
    }
}
