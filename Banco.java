import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {
    // Lista de cuentas gestionadas por el banco
    private List<Cuenta> cuentas = new ArrayList<>();

    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- Menú del Banco ---");
            System.out.println("1. Agregar cuenta");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Realizar depósito");
            System.out.println("4. Realizar retiro");
            System.out.println("5. Realizar compra");
            System.out.println("6. Mostrar historial completo de transacciones");
            System.out.println("7. Mostrar ultimas 5 transacciones");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            String numero;

            switch (opcion) {
                case 1:
                    agregarCuentaDesdeMenu(scanner);
                    break;
                case 2:
                    manejarConsultaSaldo(scanner);
                    break;
                case 3:
                    manejarDeposito(scanner);
                    break;
                case 4:
                    manejarRetiro(scanner);
                    break;
                case 5:
                    manejarCompra(scanner);
                    break;
                case 6:
                    manejarHistorialTransacciones(scanner);
                    break;
                case 7:
                    System.out.println("Ingrese el número de cuenta para ver las últimas 5 transacciones:");
                    numero = scanner.next();
                    mostrarUltimasTransacciones(numero);
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 8);

        scanner.close();
    }

    public void agregarCuentaDesdeMenu(Scanner scanner) {
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Básica");
        System.out.println("2. Cuenta Premium");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        BigDecimal saldoInicial = scanner.nextBigDecimal();

        if (tipoCuenta == 1) {
            cuentas.add(new CuentaBasica(numeroCuenta, saldoInicial));
        } else if (tipoCuenta == 2) {
            cuentas.add(new CuentaPremium(numeroCuenta, saldoInicial));
        } else {
            System.out.println("Tipo de cuenta inválido.");
        }
    }

    public void manejarConsultaSaldo(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.println("Saldo actual: " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void manejarDeposito(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto del depósito: ");
            BigDecimal monto = scanner.nextBigDecimal();
            System.out.println("Seleccione el tipo de depósito:");
            System.out.println("1. Desde sucursal (Sin Costo)");
            System.out.println("2. Desde cajero automático (2 USD solo CuentaBasica)");
            System.out.println("3. Desde otra cuenta (costo 1.5 USD)");
            int tipoDeposito = scanner.nextInt();

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
                    System.out.println("Opción de depósito inválida.");
                    break;
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void manejarRetiro(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto del retiro: ");
            BigDecimal monto = scanner.nextBigDecimal();
            cuenta.retiroCajero(monto);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void manejarCompra(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            System.out.print("Ingrese el monto de la compra: ");
            BigDecimal monto = scanner.nextBigDecimal();
            System.out.println("Seleccione el tipo de compra:");
            System.out.println("1. Establecimiento físico");
            System.out.println("2. Página web");
            int tipoCompra = scanner.nextInt();

            switch (tipoCompra) {
                case 1:
                    cuenta.compraEstablecimiento(monto);
                    break;
                case 2:
                    cuenta.compraPaginaWeb(monto);
                    break;
                default:
                    System.out.println("Opción de compra inválida.");
                    break;
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void manejarHistorialTransacciones(Scanner scanner) {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.next();
        mostrarHistorial(numeroCuenta);
    }

    public void mostrarUltimasTransacciones(String numeroCuenta) {
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            int totalTransacciones = cuenta.historialTransacciones.size();

            if (totalTransacciones == 0) {
                System.out.println("No hay transacciones registradas.");
            } else {
                System.out.println("Últimas 5 transacciones:");
                int startIndex = Math.max(0, totalTransacciones - 5);
                for (int i = startIndex; i < totalTransacciones; i++) {
                    System.out.println(cuenta.historialTransacciones.get(i));
                }
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }


    private Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public void mostrarHistorial(String numeroCuenta) {
        Cuenta cuenta = obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            cuenta.mostrarHistorialTransacciones();
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}
