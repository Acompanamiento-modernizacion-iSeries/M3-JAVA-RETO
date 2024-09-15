package Cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Banco
{
    private List<Cuenta> cuentas = new ArrayList<>();

    public void Menu() {
        int seleccion = 0;
        Scanner sc = new Scanner(System.in);
        cargaDatos();
        while (seleccion != 9) {
            opciones();
            seleccion = sc.nextInt();
            switch (seleccion) {
                case 1:// Consultar saldo
                    System.out.println("=".repeat(30));
                    consultarSaldo(sc);
                    System.out.println("=".repeat(30));
                    break;
                case 2:// Deposito sucursal
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta2 = sc.next();
                    Cuenta cuenta2 = encontrarCuenta(cuentas, numeroCuenta2);
                    if (cuenta2 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        DepositoSucursal(cuenta2, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;

                case 3: // Deposito cajero
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta3 = sc.next();
                    Cuenta cuenta3 = encontrarCuenta(cuentas, numeroCuenta3);
                    if (cuenta3 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        DepositoCajero(cuenta3, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 4:// Deposito otra cuenta
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta4 = sc.next();
                    Cuenta cuenta4 = encontrarCuenta(cuentas, numeroCuenta4);
                    if (cuenta4 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        DepositoOtraCuenta(cuenta4, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 5:// Compra establecimiento fisico
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta5 = sc.next();
                    Cuenta cuenta5 = encontrarCuenta(cuentas, numeroCuenta5);
                    if (cuenta5 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        CompraFisica(cuenta5, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 6:// Compra establecimiento vitual
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta6 = sc.next();
                    Cuenta cuenta6 = encontrarCuenta(cuentas, numeroCuenta6);
                    if (cuenta6 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        CompraVitual(cuenta6, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 7:// Retiro de cajero
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta7 = sc.next();
                    Cuenta cuenta7 = encontrarCuenta(cuentas, numeroCuenta7);
                    if (cuenta7 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        RetiroCajero(cuenta7, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 8: // historia transacciones por cuenta
                    System.out.println("=".repeat(30));
                    System.out.println("Ingrese el número de cuenta:");
                    String numeroCuenta8 = sc.next();
                    Cuenta cuenta8 = encontrarCuenta(cuentas, numeroCuenta8);
                    if (cuenta8 == null)
                    {
                        System.out.println("Cuenta no encontrada.");
                        continue;
                    }
                    else
                    {
                        historiaTrans(cuenta8, sc);
                    }
                    System.out.println("=".repeat(30));
                    break;
                case 9:
                    System.out.println("=".repeat(30));
                    System.out.println("Saliendo ... ");
                    System.out.println("=".repeat(30));
                    sc.close();
                    break;
                default:
                    System.out.println("Opción especificada no es correcta: ");
                    break;
            }
        }
    }

    private void historiaTrans(Cuenta cuenta,  Scanner sc)
    {

        System.out.println("Ingrese el numero de transacciones a consultar :");
        int trans = sc.nextInt();
        ArrayList log = (ArrayList) cuenta.obtenerHistorial(trans);
        if ( log.size()== 0)
        {
            System.out.println("Lista vacia de transacciones");
        }
        else
        {
            for (int i= 0; i < log.size() ; i++)
            {
                System.out.println(i);
                System.out.println(log.toString());
            }
        }

    }

    private void RetiroCajero(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor retiro :");
        BigDecimal monto = sc.nextBigDecimal();
        cuenta.retiroCajero(monto);
        System.out.println("Retiro realizada.");

    }

    private void CompraVitual(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor compra :");
        BigDecimal monto = sc.nextBigDecimal();
        cuenta.compraWeb(monto);
        System.out.println("Compra realizada.");
    }

    private void CompraFisica(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor compra :");
        BigDecimal monto = sc.nextBigDecimal();
        cuenta.compraEstablecimiento(monto);
        System.out.println("Compra realizada.");

    }

    private void DepositoOtraCuenta(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor a depositar:");
        BigDecimal monto = sc.nextBigDecimal();
        cuenta.depositoOtraCuenta(monto);
        System.out.println("Depósito realizado.");
    }

    private void DepositoCajero(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor a depositar:");
        BigDecimal monto = sc.nextBigDecimal();
        if (cuenta instanceof CuentaBasica)
        {
            cuenta.depositoCajero(monto);
            System.out.println("Depósito realizado.");
        }
        else
        {
            cuenta.depositoCajero(monto);
            System.out.println("Depósito realizado.");
        }
        cuenta.depositoSucursal(monto);
    }


    private Cuenta encontrarCuenta(List<Cuenta> cuentas, String numeroCuenta)
    {
        return cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst()
                .orElse(null);
    }

    private void DepositoSucursal(Cuenta cuenta, Scanner sc)
    {
        System.out.println("Ingrese el valor a depositar:");
        BigDecimal monto = sc.nextBigDecimal();
        cuenta.depositoSucursal(monto);
        System.out.println("Depósito realizado.");
    }

    private void cargaDatos() {
        // Se añaden algunas cuentas de ejemplo
        cuentas.add(new CuentaBasica("123", BigDecimal.valueOf(1000.0)));
        cuentas.add(new CuentaPremium("456", BigDecimal.valueOf(2000.0)));

    }

    private void consultarSaldo(Scanner sc) {
        System.out.println("=".repeat(30));
        Boolean existe = false;
        System.out.println("Ingrese cuenta a consultar: \n");
        String numeroCuenta = sc.next();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                System.out.println("Numero de cuenta:  \n" + cuenta.getNumeroCuenta());
                System.out.println("Saldo:  \n" + cuenta.getSaldo());
                existe = true;
            }
        }
        if (!existe) {
            System.out.println("La cuenta :" + numeroCuenta + " No existe: \n");
        }
    }

    public void opciones() {
        System.out.println("=".repeat(30));
        System.out.println("Sistema Bancario taller final - JMSL\n");
        System.out.println("Opciones: \n");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depósito desde sucursal");
        System.out.println("3. Depósito desde cajero automático");
        System.out.println("4. Depósito desde otra cuenta");
        System.out.println("5. Compra en establecimiento físico");
        System.out.println("6. Compra en página web");
        System.out.println("7. Retiro en cajero");
        System.out.println("8. Consultar historial de transacciones");
        System.out.println("9.  Salir del menu: ");
        System.out.println("=".repeat(30));
    }
}