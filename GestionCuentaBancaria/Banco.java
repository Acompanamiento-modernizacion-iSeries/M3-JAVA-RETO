import Cuentas.Cuenta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Cuenta> listCuentas = new ArrayList<>();
    //private Cuenta cuenta;
    private Scanner sc;

    public Banco() {
        sc = new Scanner(System.in);
    }

    public Boolean existeCuenta(List<Cuenta> cuentas, String numeroCuenta) {

        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta().equals(numeroCuenta)) {
                return true;

            }
        }
        return false;
    }


    public Cuenta obtenerCuenta(List<Cuenta> cuentas, String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.numeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }


    public void agregarCuenta(Cuenta cuenta) {
        listCuentas.add(cuenta);
    }

    public List<Cuenta> getListCuentas() {
        return listCuentas;
    }


    public void depositarDinero(Cuenta cuenta, BigDecimal monto, int tipo) {
        if (cuenta.numeroCuenta() != null) {
            cuenta.Depositar(monto, tipo);
            Transaccion transaccion = new Transaccion("Deposito", monto);
            agregarTransaccion(transaccion);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void retirarDinero(Cuenta cuenta, BigDecimal monto) {

        if (cuenta.numeroCuenta() != null) {
            cuenta.Retirar(monto);
            System.out.println("Retiro realizado.");
            Transaccion transaccion = new Transaccion("Retiro", monto);
            agregarTransaccion(transaccion);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void consultarSaldo(Cuenta cuenta) {
        if (cuenta.numeroCuenta() != null) {
            System.out.println("Saldo: " + cuenta.obtenerSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }

    }

    public void comprar(Cuenta cuenta, BigDecimal cantidad, Boolean esWeb) {
        if (cuenta.numeroCuenta() != null) {
            cuenta.Comprar(cantidad, esWeb);
            Transaccion transaccion = new Transaccion("Compra", cantidad);
            agregarTransaccion(transaccion);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }


    public List<Transaccion> getTransacciones() {
        return transacciones;
    }


    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public void mostrarMenu() {
        System.out.println("Ingrese su numero de cuenta");
        String numeroCuenta = sc.next();
        List<Cuenta> cuentas = getListCuentas();
        Boolean existe = existeCuenta(cuentas, numeroCuenta);
        if (existe) {
            Cuenta cuenta = obtenerCuenta(cuentas, numeroCuenta);
            while (true) {
                System.out.print("***Que opcion desea realizar***\n ");
                System.out.print("1.Depositar dinero\n ");
                System.out.print("2.Realizar compra\n ");
                System.out.print("3.Consultar saldo\n ");
                System.out.print("4.Historial Transacciones\n ");
                System.out.print("5.Salir ");
                sc.nextLine();
                int opc = sc.nextInt();
                //sc.nextLine();
                if (opc == 5) {
                    break;
                }
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese el deposito que desea reealizar:\n ");
                        System.out.println("Digite 1.Deposito desde sucursal \n");
                        System.out.println("Digite 2.Deposito desde cajero automatico \n");
                        System.out.println("Digite 3.Deposito desde otra cuenta");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Ingrese la cantidad a depositar: ");
                        BigDecimal cantidadDeposito = sc.nextBigDecimal();
                        depositarDinero(cuenta, cantidadDeposito, opcion);
                        break;
                    case 2:
                        System.out.println("Ingrese el tipo de compra que desea reealizar:\n ");
                        System.out.println("Digite 1.Compra en establecimiento fisico \n");
                        System.out.println("Digite 2.Compra en pagina web ");
                        int compra = sc.nextInt();
                        Boolean esWeb = true;
                        if (compra != 2) {
                            esWeb = false;
                        }
                        BigDecimal valorCompra = sc.nextBigDecimal();
                        comprar(cuenta,valorCompra,esWeb);
                        break;
                    case 3:
                        System.out.println("El saldo de su cuenta es: " + cuenta.obtenerSaldo());
                        break;
                    case 4:
                        List<Transaccion> transaciones = getTransacciones();
                        for (Transaccion transaccion : transaciones) {
                            transaccion.MostrarInformacion();
                        }
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
        } else {
            System.out.println("Cuenta no existe");
        }
    }
}


