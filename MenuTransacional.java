package RetoFinal;

import cuentas.CuentaAhorros;

import java.math.BigDecimal;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

import static RetoFinal.AccesoData.agregarCuenta;

public class MenuTransacional {


    public static void main(String[] args) {
        double nrcuenta=0;
        double saldo;
        int opc = 0;
        int tipocuenta ;
        llenardat();
        Scanner sc = new Scanner(System.in);
        while (opc < 10){
            System.out.println("Opciones: \n" );
            System.out.println("1        Consultar saldo " );
            System.out.println("2        Depósito desde sucursal" );
            System.out.println("3        Depósito desde cajero automático" );
            System.out.println("4        Depósito desde otra cuenta " );
            System.out.println("5        Compra en establecimiento físico " );
            System.out.println("6        Compra en página web " );
            System.out.println("7        Retiro en cajero " );
            System.out.println("8        Historial transacciones" );
            System.out.println("10       Salir del menu: " );
            opc = sc.nextInt();
            if (opc < 10) {
                try {
                    System.out.println("Ingrese numero de cuenta: \n");
                    nrcuenta = sc.nextDouble();
                } catch (InputMismatchException E) {
                    System.out.println("Ingrese dato valilido en el numero de cuenta");
                    opc = 9;
                }
            }
            switch (opc){
                case 1:
                    AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                    break;
                case 2:
                    try {
                        System.out.println("Ingrese Valor del Depósito \n");
                        saldo = sc.nextDouble();
                        AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo));
                        AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde sucursal",BigDecimal.valueOf(0)));
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del Depósito Correcto");
                        opc = 9;
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese Valor del Depósito \n");
                        saldo = sc.nextDouble();
                        AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo).subtract(calcularcosto(AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()) instanceof CuentaPremium)));
                        AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde cajero automático",calcularcosto(AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()) instanceof CuentaPremium)));
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del Depósito Correcto");
                        opc = 9;
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Ingrese Valor del Depósito \n");
                        saldo = sc.nextDouble();
                        //retiro
                        if (AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+1.5))){
                            agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Retiro desde otra cuenta",BigDecimal.valueOf(1.5)));
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                            try{
                            System.out.println("Ingrese cuenta destino \n");
                            nrcuenta = sc.nextDouble();
                          //deposito tralado
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo));
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                            agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde otra cuenta",BigDecimal.valueOf(0)));
                            }catch (InputMismatchException E){
                                System.out.println("Ingrese numero cuenta destino Correcto");
                                opc = 9;
                            }
                        }
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del Depósito Correcto");
                        opc = 9;
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Ingrese Valor de la compra \n");
                        saldo = sc.nextDouble();
                        if (AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo))){
                            agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Compra en establecimiento físico",BigDecimal.valueOf(0)));
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                         }
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del compra Correcto");
                        opc = 9;
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Ingrese Valor de la compra \n");
                        saldo = sc.nextDouble();
                        if (AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+5))){
                            agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Compra en página web",BigDecimal.valueOf(5)));
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        }
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del Compra Correcto");
                        opc = 9;
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Ingrese Valor a retirar en cajero  \n");
                        saldo = sc.nextDouble();
                        if (AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+1))){
                            agregarCuenta(new Logtransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Retiro en cajero",BigDecimal.valueOf(1)));
                            AccesoData.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        }
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del retiro Correcto");
                        opc = 9;
                    }
                    break;
                case 8:
                    AccesoData.buscarlog(BigDecimal.valueOf(nrcuenta).toBigInteger());
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Opción no valida: \n" );
                    break;
            }

        }
        sc.close();
    }
    public static void llenardat(){

        agregarCuenta(new CuentaBásica(BigDecimal.valueOf(1000),BigInteger.valueOf(1234) ));
        agregarCuenta(new CuentaBásica(BigDecimal.valueOf(2000),BigInteger.valueOf(1238) ));
        agregarCuenta(new CuentaBásica(BigDecimal.valueOf(4000),BigInteger.valueOf(1244) ));

        agregarCuenta(new CuentaPremium(BigDecimal.valueOf(8000),BigInteger.valueOf(8234) ));
        agregarCuenta(new CuentaPremium(BigDecimal.valueOf(9000),BigInteger.valueOf(8238) ));
        agregarCuenta(new CuentaPremium(BigDecimal.valueOf(12000),BigInteger.valueOf(8244) ));
    }
    public static BigDecimal calcularcosto(boolean sicalcula){
        if (sicalcula){
            return BigDecimal.valueOf(0);
        }else{
            return BigDecimal.valueOf(2);
        }


    }


}
