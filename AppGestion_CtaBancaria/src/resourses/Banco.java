package resourses;

import cuentas.CuentaBasica;
import cuentas.CuentaPremium;
import db.CuentasDB;

import java.util.Scanner;

public class Banco {
    public void opciones(String numCuenta) {
        System.out.println("\n *** BIENVENIDO ***");

        Scanner scanner = new Scanner(System.in);
        int tipoTransacc;
        String tipoCuenta = CuentasDB.buscarCuenta(numCuenta).getClass().getName();
        Double comision;

        while (true) {
            tipoTransacc = Menu.principal(scanner);

            switch (tipoTransacc) {
                case 1:
                    numCuenta = Movimiento.seleccionarCuenta(scanner);
                    tipoCuenta = CuentasDB.buscarCuenta(numCuenta).getClass().getName();
                    break;
                case 2:
                    Movimiento.consultaSaldo(CuentasDB.buscarCuenta(numCuenta));
                    break;
                case 3:
                    Movimiento.consultaTransacciones(numCuenta);
                    break;
                case 4:
                    comision = tipoCuenta.equals("cuentas.CuentaBasica") ?
                            CuentaBasica.comisionDepositoSuc() : CuentaPremium.comisionDepositoSuc();

                    Movimiento.deposito(scanner, CuentasDB.buscarCuenta(numCuenta), "deposito sucursal", comision);
                    break;
                case 5:
                    comision = tipoCuenta.equals("cuentas.CuentaBasica") ?
                            CuentaBasica.comisionDepositoCajAuto() : CuentaPremium.comisionDepositoCajAuto();

                    Movimiento.deposito(scanner, CuentasDB.buscarCuenta(numCuenta), "deposito cajero automatico", comision);
                    break;
                case 6:
                    Movimiento.transferencia(scanner, CuentasDB.buscarCuenta(numCuenta));
                    break;
                case 7:
                    comision = tipoCuenta.equals("cuentas.CuentaBasica") ?
                            CuentaBasica.comisionCompraFisica() : CuentaPremium.comisionCompraFisica();

                    Movimiento.retiro(scanner, CuentasDB.buscarCuenta(numCuenta), "compra fisica", comision);
                    break;
                case 8:
                    comision = tipoCuenta.equals("cuentas.CuentaBasica") ?
                            CuentaBasica.comisionCompraVirtual() : CuentaPremium.comisionCompraVirtual();

                    Movimiento.retiro(scanner, CuentasDB.buscarCuenta(numCuenta), "compra virtual", comision);
                    break;
                case 9:
                    comision = tipoCuenta.equals("cuentas.CuentaBasica") ?
                            CuentaBasica.comisionRetiroCajero() : CuentaPremium.comisionRetiroCajero();

                    Movimiento.retiro(scanner, CuentasDB.buscarCuenta(numCuenta), "retiro cajero", comision);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar nuestro servicio!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tipo de transaccion no reconocida!\n");
            }
        }
    }
}
