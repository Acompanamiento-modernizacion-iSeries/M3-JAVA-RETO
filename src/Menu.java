import BaseDatos.CuentasDB;
import BaseDatos.TransaccionesDB;
import Cuentas.Cuenta;
import Cuentas.CuentaBasica;
import Cuentas.Transaccion;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    /*Capturo tdodo el menu*/
    public void Menu() {
        Cuenta ctavalida = null;
        int i = 0;
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        while (continuar) {

            System.out.println("Ingrese cuenta: ");
            String nrocuenta = sc.nextLine();
            if (nrocuenta.isEmpty()) {
                System.out.println(" Nro cuenta no esta diligenciado: ");
              }
            else {
                // validar cuenta en la lista de cuentas
                ctavalida = CuentasDB.buscarCuenta(nrocuenta);
                if (ctavalida == null) {
                    System.out.println(" Cuenta no existe ");
                 }
               else
                {
                  continuar = false;
                }
            }
        }
        while (i < 9) {
            System.out.println("Opciones: \n");
            System.out.println("1        Deposito desde sucursal: ");
            System.out.println("2        Deposito desde cajero automático: ");
            System.out.println("3        Deposito desde otra cuenta: ");
            System.out.println("4        Compre establecimiento Fisico: ");
            System.out.println("5        Compre en Pagina Web: ");
            System.out.println("6        Retiro en cajero : ");
            System.out.println("7        Consultar saldo cuenta: ");
            System.out.println("8        Consultar Historico de las ultimas 5 transacciones : ");
            System.out.println("9        Salir del Sistema: ");
            int opc = sc.nextInt();
            switch (opc) {
                case 1:
                    depositoSucursal(sc,ctavalida);
                    break;
                case 2:
                    depositoCajero(sc,ctavalida);
                     break;
                case 3:
                    depositoOtraCuenta(sc,ctavalida);
                    break;
                case 4:
                    compraEstablecimiento(sc,ctavalida);
                    break;
                case 5:
                    compraWeb(sc,ctavalida);
                    break;
                case 6:
                    retiroCajero(sc,ctavalida);
                    break;
                case 7:
                    consultarSaldo(ctavalida);
                    break;
                case 8:
                     TransaccionesDB.buscarHistorialTrx();
                     break;
                case 9:
                    return;

            }
        }

    }

    public void  depositoSucursal (Scanner sc,Cuenta ctavalida)
    {
      System.out.println("Ingrese monto: ");
      BigDecimal monto = sc.nextBigDecimal();
      if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
          System.out.println("Monto debe ser mayor a 0 ");
          return;
      }
        ctavalida.depositoSucursal(monto);
        System.out.println("Deposito realizado. ");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Deposito desde Suc", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);


    }

    public void  depositoCajero (Scanner sc,Cuenta ctavalida)
    {
        System.out.println("Ingrese monto: ");
        BigDecimal monto = sc.nextBigDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
            System.out.println("Monto debe ser mayor a 0 ");
            return;
        }
        ctavalida.depositoCajero(monto);
        System.out.println("Deposito Cajero realizado. ");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Deposito Cajero realizado", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);

    }

    public void  depositoOtraCuenta (Scanner sc,Cuenta ctavalida)
    {
        // Cuenta destino
        Cuenta ctadestino = null;
        System.out.println("Ingrese cuenta Destino: ");
        String cuentad = sc.nextLine();
        if (cuentad.isEmpty())
        {
            System.out.println(" Nro cuenta destino  no esta diligenciado: ");
            return;
        }
        else {
            // validar cuenta en la lista de cuentas
            ctadestino = CuentasDB.buscarCuenta(cuentad);
            if (ctadestino == null) {
                System.out.println(" Cuenta destino no existe ");
                return;
            }
        }

        System.out.println("Ingrese monto: ");
        BigDecimal monto = sc.nextBigDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
            System.out.println("Monto debe ser mayor a 0 ");
            return;
        }
        ctavalida.depositoOtraCuenta(monto);
        ctadestino.colocarSaldo(ctadestino.consultarSaldo().add(monto));
        System.out.println("Deposito a cuenta Nro : " + cuentad + " realizado");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Deposito a Otra cuenta", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);


    }

    public void  compraEstablecimiento (Scanner sc,Cuenta ctavalida)
    {
        System.out.println("Ingrese monto: ");
        BigDecimal monto = sc.nextBigDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
            System.out.println("Monto debe ser mayor a 0 ");
            return;
        }
        ctavalida.compraEstableFisico(monto);
        System.out.println("Compra establecimiento realizado. ");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Compra establecimiento realizado", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);


    }

    public void  compraWeb (Scanner sc,Cuenta ctavalida)
    {
        System.out.println("Ingrese monto: ");
        BigDecimal monto = sc.nextBigDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
            System.out.println("Monto debe ser mayor a 0 ");
            return;
        }
        ctavalida.compraPagWeb(monto);
        System.out.println("Compra Web realizado. ");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Compra Web realizado", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);


    }

    public void  retiroCajero (Scanner sc,Cuenta ctavalida)
    {
        System.out.println("Ingrese monto: ");
        BigDecimal monto = sc.nextBigDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0 ) {
            System.out.println("Monto debe ser mayor a 0 ");
            return;
        }
        ctavalida.retiroCajero(monto);
        System.out.println("Retiro Cajero realizado. ");
        // creo un objeto con los datos de la trx
        Transaccion trx = new Transaccion("Retiro Cajero realizado", monto);
        // registrar trx
        TransaccionesDB.LlenarTransaccion(trx);


    }

    public void  consultarSaldo (Cuenta ctavalida)
    {
        String tipocuenta = " " ;
        if (ctavalida instanceof CuentaBasica) {
            tipocuenta = "Básica";
                }
        else
        {
            tipocuenta = "Premium";
        }
        System.out.println("Nro cuenta: " + ctavalida.consultarNrocuenta());
        System.out.println("Saldo: $" + ctavalida.consultarSaldo());
        System.out.println("Tipo cuenta: " + tipocuenta);


    }
}

