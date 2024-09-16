package banco;

import cuentas.Cuenta;
import db.CuentasDB;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private List<Cuenta> cuentas;

    public Banco() {
        cuentas = CuentasDB.getListaCuentas();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Comprar");
            System.out.println("5. Consultar historial");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el número de cuenta:");
            String numeroCuenta = scanner.nextLine();
            Cuenta cuenta = buscarCuenta(numeroCuenta);

            if (cuenta == null) {
                System.out.println("Cuenta no encontrada.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese el monto a depositar:");
                    BigDecimal montoDeposito = scanner.nextBigDecimal();
                    cuenta.depositar(montoDeposito, "sucursal");
                    break;
                case 3:
                    System.out.println("Ingrese el monto a retirar:");
                    BigDecimal montoRetiro = scanner.nextBigDecimal();
                    cuenta.retirar(montoRetiro);
                    break;
                case 4:
                    System.out.println("Ingrese el monto de la compra:");
                    BigDecimal montoCompra = scanner.nextBigDecimal();
                    cuenta.comprar(montoCompra, "físico");
                    break;
                case 5:
                    cuenta.mostrarHistorial();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}