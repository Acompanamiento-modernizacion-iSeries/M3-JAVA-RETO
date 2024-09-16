import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;



class Main {

    public static void main(String[] args) {
        double nrcuenta=0;
        double saldo;
        int opc = 0;

        llenarDatos();
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
                    InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                    break;
                case 2:
                    try {
                        System.out.println("Ingrese Valor del Depósito \n");
                        saldo = sc.nextDouble();
                        InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo));
                        InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();

                        InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde sucursal",BigDecimal.valueOf(0)));
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del Depósito Correcto");
                        opc = 9;
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese Valor del Depósito \n");
                        saldo = sc.nextDouble();
                        InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo).subtract(calcularcosto(InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()) instanceof CuentaPremium)));
                        InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde cajero automático",calcularcosto(InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()) instanceof CuentaPremium)));
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
                        if (InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+1.5))){
                            InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Retiro desde otra cuenta",BigDecimal.valueOf(1.5)));
                            InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                            try{
                                System.out.println("Ingrese cuenta destino \n");
                                nrcuenta = sc.nextDouble();
                                //deposito tralado
                                InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).depositar(BigDecimal.valueOf(saldo));
                                InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                                InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Depósito desde otra cuenta",BigDecimal.valueOf(0)));
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
                        if (InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo))){
                            InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Compra en establecimiento físico",BigDecimal.valueOf(0)));
                            InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
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
                        if (InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+5))){
                            InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Compra en página web",BigDecimal.valueOf(5)));
                            InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
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
                        if (InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).retirar(BigDecimal.valueOf(saldo+1))){
                            InformacionCuenta.agregarCuenta(new LogTransaccion(BigDecimal.valueOf(saldo),BigDecimal.valueOf(nrcuenta).toBigInteger(),"Retiro en cajero",BigDecimal.valueOf(1)));
                            InformacionCuenta.buscarCuenta(BigDecimal.valueOf(nrcuenta).toBigInteger()).mostrarInformacion();
                        }
                    }catch (InputMismatchException E){
                        System.out.println("Ingrese Valor del retiro Correcto");
                        opc = 9;
                    }
                    break;
                case 8:
                    InformacionCuenta.buscarlog(BigDecimal.valueOf(nrcuenta).toBigInteger());
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
    public static void llenarDatos(){

        InformacionCuenta.agregarCuenta(new CuentaBasica(BigDecimal.valueOf(2000),BigInteger.valueOf(0234) ));
        InformacionCuenta.agregarCuenta(new CuentaBasica(BigDecimal.valueOf(4000),BigInteger.valueOf(0345) ));
        InformacionCuenta.agregarCuenta(new CuentaBasica(BigDecimal.valueOf(6000),BigInteger.valueOf(0456) ));

        InformacionCuenta.agregarCuenta(new CuentaPremium(BigDecimal.valueOf(10000),BigInteger.valueOf(5567) ));
        InformacionCuenta.agregarCuenta(new CuentaPremium(BigDecimal.valueOf(20000),BigInteger.valueOf(5678) ));
        InformacionCuenta.agregarCuenta(new CuentaPremium(BigDecimal.valueOf(30000),BigInteger.valueOf(5789) ));
    }
    public static BigDecimal calcularcosto(boolean sicalcula){
        if (sicalcula){
            return BigDecimal.valueOf(0);
        }else{
            return BigDecimal.valueOf(2);
        }
    }
}
