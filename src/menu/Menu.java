package menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import banco.Banco;
import clientes.Cliente;
import cuentas.Cuenta;

public class Menu {
    private Banco banco;
    private Scanner scanner;

    public Menu(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear nueva cuenta");
            System.out.println("2. Realizar operaciónes");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    crearNuevaCuenta();
                    break;
                case 2:
                    operarCuentaExistente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    System.out.println("Gracias por usar nuestro sistema!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void crearNuevaCuenta() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = leerString();
        System.out.print("Ingrese el ID del cliente: ");
        String id = leerString();
        String tipoCuenta;
        do {
            System.out.print("Tipo de cuenta (B->basica/ P->premium): ");
            tipoCuenta = leerString().toUpperCase();
        } while (!tipoCuenta.equals("B") && !tipoCuenta.equals("P"));
        System.out.print("Saldo inicial: ");
        BigDecimal saldoInicial = leerBigDecimal();

        banco.agregarCliente(nombre, id, tipoCuenta, saldoInicial);
        System.out.println("Cuenta creada exitosamente.");
    }

    private void operarCuentaExistente() {
        System.out.print("Ingrese el ID del cliente: ");
        String id = leerString();
        Cliente cliente = banco.buscarCliente(id);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Cuenta cuenta = cliente.getCuenta();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Operaciones de Cuenta ---");
            System.out.println("1. Depósito desde sucursal");
            System.out.println("2. Depósito desde cajero automático");
            System.out.println("3. Depósito desde otra cuenta");
            System.out.println("4. Compra en establecimiento físico");
            System.out.println("5. Compra en página web");
            System.out.println("6. Retiro en cajero");
            System.out.println("7. Consultar saldo");
            System.out.println("8. Consultar últimas 5 transacciones");
            System.out.println("9. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            BigDecimal monto;

            switch (opcion) {
                case 1:
                    espacioMenu();
                    System.out.print("Ingrese el monto a depositar: ");
                    monto = leerBigDecimal();
                    cuenta.depositoDesdeSucursal(monto);
                    break;
                case 2:
                    espacioMenu();
                    System.out.print("Ingrese el monto a depositar: ");
                    monto = leerBigDecimal();
                    cuenta.depositoDesdeCajero(monto);
                    break;
                case 3:
                    espacioMenu();
                    System.out.print("Ingrese el monto a depositar: ");
                    monto = leerBigDecimal();
                    cuenta.depositoDesdeOtraCuenta(monto);
                    break;
                case 4:
                    espacioMenu();
                    System.out.print("Ingrese el monto de la compra: ");
                    monto = leerBigDecimal();
                    cuenta.compraEstablecimientoFisico(monto);
                    break;
                case 5:
                    espacioMenu();
                    System.out.print("Ingrese el monto de la compra: ");
                    monto = leerBigDecimal();
                    cuenta.compraPaginaWeb(monto);
                    break;
                case 6:
                    espacioMenu();
                    System.out.print("Ingrese el monto a retirar: ");
                    monto = leerBigDecimal();
                    cuenta.retiroCajero(monto);
                    break;
                case 7:
                    espacioMenu();
                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;
                case 8:
                    espacioMenu();
                    System.out.println("Últimas 5 transacciones:");
                    cuenta.getUltimasTransacciones(5).forEach(System.out::println);
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = banco.obtenerTodosLosClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            System.out.println("\n--- Lista de Clientes ---");
            for (Cliente cliente : clientes) {
                System.out.printf("ID: %s, Nombre: %s, Tipo de Cuenta: %s%n",
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getCuenta().getTipoCuenta());
            }
        }
    }


    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número entero válido: ");
            }
        }
    }

    private BigDecimal leerBigDecimal() {
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número decimal válido: ");
            }
        }
    }

    private String leerString() {
        return scanner.nextLine().trim();
    }

    private void espacioMenu(){
        System.out.print("\n**********************\n");
    }
}