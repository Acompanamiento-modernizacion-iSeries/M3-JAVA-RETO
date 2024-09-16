package Banco;

import Cuentas.Cuenta;
import Cuentas.CuentaBasica;
import Cuentas.CuentaPremium;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private List<Cuenta> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú del Banco ---");
            System.out.println("1. Agregar nueva cuenta");
            System.out.println("2. Depósito en Sucursal");
            System.out.println("3. Depósito en Cajero Automático");
            System.out.println("4. Depósito desde Otra Cuenta");
            System.out.println("5. Compra en Establecimiento Físico");
            System.out.println("6. Compra en Página Web");
            System.out.println("7. Retiro en Cajero Automático");
            System.out.println("8. Consultar saldo");
            System.out.println("9. Consultar historial de transacciones");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarNuevaCuenta(sc);
                    break;
                case 2:
                    realizarDeposito(sc, "Sucursal");
                    break;
                case 3:
                    realizarDeposito(sc, "Cajero");
                    break;
                case 4:
                    realizarDeposito(sc, "OtraCuenta");
                    break;
                case 5:
                    realizarCompra(sc, "Fisica");
                    break;
                case 6:
                    realizarCompra(sc, "Web");
                    break;
                case 7:
                    realizarRetiro(sc);
                    break;
                case 8:
                    consultarSaldo(sc);
                    break;
                case 9:
                    consultarHistorial(sc);
                    break;
                case 10:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Seleccionaste una opción incorrecta");
            }
        }
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        System.out.println("Cuenta con número " + cuenta.getNumeroCuenta() + " agregada con éxito");
    }

    public Cuenta buscarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    private void agregarNuevaCuenta(Scanner sc) {

        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        System.out.println("Seleccione el tipo de cuenta:\n1. Cuenta Básica\n2. Cuenta Premium");
        int tipoCuenta = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el saldo inicial:");
        BigDecimal saldoInicial = sc.nextBigDecimal();
        sc.nextLine();

        if (tipoCuenta == 1) {
            CuentaBasica cuentaBasica = new CuentaBasica(saldoInicial, numeroCuenta);
            agregarCuenta(cuentaBasica);
        } else if (tipoCuenta == 2) {
            CuentaPremium cuentaPremium = new CuentaPremium(saldoInicial, numeroCuenta);
            agregarCuenta(cuentaPremium);
        } else {
            System.out.println("Opción no válida. No se creó la cuenta.");
        }
    }
    private void realizarDeposito(Scanner sc, String tipoDeposito) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Ingrese el monto a depositar:");
            BigDecimal monto = sc.nextBigDecimal();

            switch (tipoDeposito) {
                case "Sucursal":
                    cuenta.depositoSucursal(monto);
                    break;
                case "Cajero":
                    cuenta.depositoCajeroAut(monto);
                    break;
                case "OtraCuenta":
                    cuenta.depositoOtraCta(monto);
                    break;
            }
            System.out.println("Depósito realizado. Saldo actual: $" + cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void realizarCompra(Scanner sc, String tipoCompra) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Ingrese el monto de la compra:");
            BigDecimal monto = sc.nextBigDecimal();

            switch (tipoCompra) {
                case "Fisica":
                    cuenta.compraFisica(monto);
                    break;
                case "Web":
                    cuenta.compraWeb(monto);
                    break;
            }
            System.out.println("Compra realizada. Saldo actual: $" + cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void realizarRetiro(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Ingrese el monto a retirar:");
            BigDecimal monto = sc.nextBigDecimal();
            cuenta.retiroCajero(monto);
            System.out.println("Retiro realizado. Saldo actual: $" + cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void consultarSaldo(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual: $" + cuenta.consultarSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    private void consultarHistorial(Scanner sc) {
        System.out.println("Ingrese el número de cuenta:");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            cuenta.historialTrx();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

}
