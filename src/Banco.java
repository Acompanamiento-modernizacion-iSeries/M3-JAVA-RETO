import Cuentas.Cuenta;
import Cuentas.CuentaBasica;
import Cuentas.CuentaPremium;
import Utils.Util;

import java.math.BigDecimal;

public  class Banco {
    // private List<Cuenta> cuentas;

    public Banco() {
        BigDecimal saldo = BigDecimal.ZERO;
        saldo = saldo.add(BigDecimal.valueOf(500.00));

        Util.mostrarSeparador60();
        CuentaBasica cuenta1 = new CuentaBasica(saldo, "1111" ,"Carlos");
        Cuentas.CuentasDB.agregarCuenta(cuenta1);
        Util.mensaje("** Cuenta de prueba Creada Exitosamente: " ,true);
        Util.mensaje(cuenta1.toString());

        CuentaPremium cuenta2 = new CuentaPremium(saldo, "2222" ,"Juan");
        Cuentas.CuentasDB.agregarCuenta(cuenta2);
        Util.mensaje("** Cuenta de prueba Creada Exitosamente: " ,true);
        Util.mensaje(cuenta2.toString());

        CuentaPremium cuenta3 = new CuentaPremium(saldo, "3333" ,"Salomé");
        Cuentas.CuentasDB.agregarCuenta(cuenta3);
        Util.mensaje("** Cuenta de prueba Creada Exitosamente: " ,true);
        Util.mensaje(cuenta3.toString());

        CuentaBasica cuenta4 = new CuentaBasica(saldo, "4444" ,"Tatiana");
        Cuentas.CuentasDB.agregarCuenta(cuenta4);
        Util.mensaje("** Cuenta de prueba Creada Exitosamente: " ,true);
        Util.mensaje(cuenta4.toString());


        Util.mostrarSeparador60();
    }

    private int mostrarMenuBanco(){
        Util.mostrarSeparador100();
        String separador60 =Util.obtenerSeparador60() ;

        String textoMenu= separador60
                + "\n                    Elige una Opción\n"
                +  separador60
                + "\nMenú Principal:\n"
                + "   1. Crear Cuenta Basica\n"
                + "   2. Crear Cuenta Premium\n"
                + "   3. Trabajar con una Cuenta\n"
                + "   4. Calcular Interes Simple\n"
                + "   5. Calcular Interes Compuesto (1 Año)\n"
                + "   6. Verificar Elegibilidad Crediticia\n"
                + "   7. Consultar ultimas 5 transacciones\n"
                + "   8. Salir\n"
                +  separador60;
        Util.mensaje(textoMenu);

        int opcionMenu = 0;
        try {
            Util.mensaje("Opción (1-8): ", true);
            opcionMenu = Util.ingresarEntero();
        }catch (IllegalArgumentException e){
            Util.mensaje(" ** Error al seleccionar una Opción: " + e.getMessage());
            opcionMenu = 0;
        }
        Util.mostrarSeparador100();
        return opcionMenu ;
    }

    private int mostrarMenuCuenta(){
        Util.mostrarSeparador100();
        String separador60 = Util.obtenerSeparador60() ;

        String textoMenu= separador60
                + "\n                    Elige una Opción\n"
                +  separador60
                + "\nMenú Principal:\n"
                + "   1. Consultar Saldo\n"
                + "   2. Realizar Depósito desde sucursal\n"
                + "   3. Realizar Depósito desde cajero automático\n"
                + "   4. Realizar Depósito desde otra cuenta\n"
                + "   5. Realizar Compra en establecimiento físico\n"
                + "   6. Realizar Compra en página web\n"
                + "   7. Realizar Retiro en cajero\n"
                + "   8. Regresar al Menu Principal\n"
                +  separador60;
        Util.mensaje(textoMenu);

        int opcionMenu = 0;
        try {
            Util.mensaje("Opción (1-8): ", true);
            opcionMenu = Util.ingresarEntero();
        }catch (IllegalArgumentException e){
            Util.mensaje(" ** Error al seleccionar una Opción: " + e.getMessage());
            opcionMenu = 0;
        }
        return opcionMenu ;
    }

    public void MenuBanco(){
        int opcionMenu  =  0;
        boolean continuar  = true;

        while (continuar){
            Util.mostrarSeparador100();
            opcionMenu = mostrarMenuBanco();
            switch(opcionMenu) {
                case 1:  crearCuentaBasica();                break;
                case 2:  crearCuentaPremium();               break;
                case 3:  trabajarConCuenta(this);                break;
                case 4:  calcularInteresSimple();            break;
                case 5:  calcularInteresCompuesto();         break;
                case 6:  verificarElegibilidadCredito();     break;
                case 7:  consultar5Transacciones();                     break;
                case 8:  continuar = salirDelMenu();         break;
                default: continuar = opcionDeMenuNoValida(opcionMenu);
            }
        }

    }

    private static void trabajarConCuenta(Banco banco) {
        String nroCuenta;
        int opcionMenu  =  0;
        boolean continuar  = true;

        Util.obtenerSeparador100();
        Util.mensaje("Ingrese el Numero de cuanta con el que desea Trabajar: ", true);
        nroCuenta = Util.ingresarTexto();
        Cuenta cuenta = banco.buscarCuenta(nroCuenta);

        if (cuenta != null){
            while (continuar){
                Util.mostrarSeparador60();
                opcionMenu = banco.mostrarMenuCuenta();
                switch(opcionMenu) {
                    case 1:  banco.consultarSaldo(cuenta);                         break;
                    case 2:  banco.realizarDeposito(cuenta, 2);        break;
                    case 3:  banco.realizarDeposito(cuenta, 3);        break;
                    case 4:  banco.realizarDeposito(cuenta, 4);        break;
                    case 5:  banco.RealizarRetiro(cuenta,   5);        break;
                    case 6:  banco.RealizarRetiro(cuenta,   6);        break;
                    case 7:  banco.RealizarRetiro(cuenta,   7);        break;
                    case 8:  continuar = salirDelMenu();                     break;
                    default: continuar = banco.opcionDeMenuNoValida(opcionMenu);
                }
            }

        }else{
            Util.mensaje("¡Cuenta " + nroCuenta + ", No existe!");
        }
        Util.obtenerSeparador100();
    }

    private void consultar5Transacciones() {
        Logs.LogTrxDB.mostrarUltimosCincoRegistros();
    }

    private void RealizarRetiro(Cuenta cuenta, int tipoDeposito) {
        Util.mensaje("Ingrese el Monto a Retirar:  ",true);
        BigDecimal monto = Util.ingresarDecimal();
        switch(tipoDeposito) {
            case 5: cuenta.compraFisico(monto);    break;
            case 6: cuenta.compraWeb(monto);       break;
            case 7: cuenta.retiroCajero(monto);    break;
        }
    }

    private void realizarDeposito(Cuenta cuenta, int tipoDeposito) {
        Util.mensaje("Ingrese el Monto a Depositar:  ", true);
        BigDecimal monto = Util.ingresarDecimal();

        switch(tipoDeposito) {
            case 2: cuenta.depositoSucursal(monto);    break;
            case 3: cuenta.depositoCajero(monto);      break;
            case 4: cuenta.depositoOtraCuenta(monto);  break;
        }

    }

    private void consultarSaldo(Cuenta cuenta) {
        Util.mensaje("\nTu saldo actual es: $" + cuenta.getSaldo().doubleValue() + "\n");
    }

    private void crearCuentaBasica() {
        BigDecimal saldo;
        String titular ;
        String nroCuenta ;

        Util.mostrarSeparador60();
        Util.mensaje("Ingrese el Titular: ",true);
        titular = Util.ingresarTexto();
        Util.mensaje("Ingrese el numero de cuenta: ",true);
        nroCuenta = Util.ingresarTexto();
        Util.mensaje("Ingrese el Saldo Inicial: ",true);
        saldo = Util.ingresarDecimal();

        Util.mostrarSeparador60();

        CuentaBasica cuenta = new CuentaBasica(saldo, nroCuenta ,titular);

        Cuentas.CuentasDB.agregarCuenta(cuenta);

        Util.mensaje("** Cuenta Creada Exitosamente: ");
        Util.mensaje(cuenta.toString());
        Util.mostrarSeparador60();
    }

    private void crearCuentaPremium() {
        BigDecimal saldo;
        String titular ;
        String nroCuenta ;

        Util.mostrarSeparador60();
        Util.mensaje("Ingrese el Titular: ",true);
        titular = Util.ingresarTexto();
        Util.mensaje("Ingrese el numero de cuenta: ",true);
        nroCuenta = Util.ingresarTexto();
        Util.mensaje("Ingrese el Saldo Inicial: ",true);
        saldo = Util.ingresarDecimal();

        Util.mostrarSeparador60();

        CuentaPremium cuenta = new CuentaPremium(saldo, nroCuenta ,titular);
        Cuentas.CuentasDB.agregarCuenta(cuenta);

        Util.mensaje("** Cuenta Creada Exitosamente: ");
        Util.mensaje(cuenta.toString());
        Util.mostrarSeparador60();
    }

    private  boolean opcionDeMenuNoValida(int opcion) {
        Util.mostrarSeparador60();
        Util.mensaje(" *** *** ALERTA - La Opción de Menú " + opcion + " NO es valida. *** ***");
        Util.mostrarSeparador60();
        return  true;
    }

    private static boolean salirDelMenu() {
        Util.mostrarSeparador60();
        Util.mensaje( "** Saliendo - Gracias por usar nuestro servicio.");
        return false;
    }

    private static void verificarElegibilidadCredito() {
        Util.mensaje("Ingrese el ingreso anual: $", true);
        BigDecimal ingresoAnual = Util.ingresarDecimal();

        Util.mensaje("Ingrese la deuda total: $", true);
        BigDecimal deudaTotal = Util.ingresarDecimal();
        boolean elegible;
        try {
            BigDecimal limite = ingresoAnual.multiply(BigDecimal.valueOf(0.5));
            elegible = ingresoAnual.compareTo(deudaTotal) > 0 && deudaTotal.compareTo(limite) <= 0;
        } catch (ArithmeticException e) {
            Util.mensaje("Error en la validación de elegibilidad para crédito: " + e.getMessage());
            elegible=  false;
        }
        System.out.println (elegible ? "Elegible para crédito." : "No elegible para crédito.");
    }

    private static void calcularInteresCompuesto() {
        Util.mensaje("Ingrese el capital:", true);
        BigDecimal capital = Util.ingresarDecimal();
        Util.mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = Util.ingresarDecimal();
        Util.mensaje("Ingrese el tiempo en años:", true);
        int tiempo = Util.ingresarEntero();

        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular (1 + tasaInteres)
        BigDecimal base = BigDecimal.ONE.add(tasaInteresDecimal);

        // Calcular (1 + tasaInteres)^tiempo
        BigDecimal interesCompuesto = base.pow(tiempo);

        // Calcular capital * (1 + tasaInteres)^tiempo
        interesCompuesto = capital.multiply(interesCompuesto);

        Util.mensaje("El interes compuesto es: " + interesCompuesto.doubleValue());

    }

    private static void calcularInteresSimple() {
        Util.mensaje("Ingrese el capital:", true);
        BigDecimal capital = Util.ingresarDecimal();
        Util.mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = Util.ingresarDecimal();
        Util.mensaje("Ingrese el tiempo en años:", true);
        int tiempo = Util.ingresarEntero();
        // BigDecimal interes = capital.multiply(tasaInteres).multiply(BigDecimal.valueOf(tiempo));

        BigDecimal interesSimple;
        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular el interés simple: capital * tasaInteres * tiempo
        interesSimple = capital.multiply(tasaInteresDecimal).multiply(BigDecimal.valueOf(tiempo));

        Util.mensaje("El interes simple es: " + interesSimple.doubleValue());
    }

    private Cuenta buscarCuenta(String numeroCuenta) {
        Cuenta  cuenta ;
        cuenta = Cuentas.CuentasDB.buscarCuentaPorNumero(numeroCuenta);
        return cuenta;
    }

}

