import DataValidation.InputDataValidations;
import models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class MenuSistemaBancario {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Cuenta> DBcuentas;
        ArrayList<String> historicoTransacciones = new ArrayList<>();
        DBcuentas = DBCuentas.LlenarCuentas();
        InputDataValidations validardatosentrada = new InputDataValidations();
        String tipoCuentaTexto;
        Cuenta tipoCuenta;
        System.out.println("Ingrese el número de la cuenta");
        while (true) {
            String numeroCuenta = scan.next();
            if (!numeroCuenta.isEmpty()) {
                tipoCuenta = validardatosentrada.buscarCuenta(DBcuentas, numeroCuenta);
                if (tipoCuenta != null) {
                    if (tipoCuenta instanceof CuentaBasica) {
                        tipoCuentaTexto = "Cuenta básica";
                    } else {
                        tipoCuentaTexto = "Cuenta premium";
                    }
                    break;
                } else {
                    System.out.println("El número de cuenta: " + numeroCuenta + " no existe");
                }
            } else {
                System.out.println("Debe ingresar un número de cuenta");
            }
        }

        while (true) {
            Mostrarmenu(tipoCuentaTexto);
            Integer opcionTransaccion = validardatosentrada.ValidarTipoTransaccion();
            if (opcionTransaccion.equals(9)) {
                break;
            } else {
                ejecutaTransaccion(opcionTransaccion, tipoCuenta);
            }

        }
    }


    public static void Mostrarmenu(String tipoCuentaTexto) {
        System.out.println("------------Menu transacciones " + tipoCuentaTexto + "------------\n");
        System.out.println(
                """
                        Ingrese el tipo de transacción
                         1 - Depósito desde sucursal
                         2 - Depósito desde cajero automático
                         3 - Depósito desde otra cuenta
                         4 - Compra en establecimiento físico
                         5 - Compra en página web
                         6 - Retiro en cajero
                         7 - Consultar saldo
                         8 - Historico de movimientos
                         9 - Cancelar Transacción
                        """);
    }

    public static void ejecutaTransaccion(Integer opcionTransaccion, Cuenta cuentaUsuario) {
        while (true) {
            switch (opcionTransaccion) {
                case 1 -> HistorialTransacciones.AsignarHistorialTransacciones(depositoSucursal(cuentaUsuario));
                case 2 -> HistorialTransacciones.AsignarHistorialTransacciones(depositoDesdecajero(cuentaUsuario));
                case 3 -> HistorialTransacciones.AsignarHistorialTransacciones(depositoDesdeOtraCuenta(cuentaUsuario));
                case 4 -> HistorialTransacciones.AsignarHistorialTransacciones(CompraEnEstablecimientoFisico(cuentaUsuario));
                case 5 -> HistorialTransacciones.AsignarHistorialTransacciones(CompraDesdeWeb(cuentaUsuario));
                case 6 -> HistorialTransacciones.AsignarHistorialTransacciones(retirodesdecajero(cuentaUsuario));
                case 7 -> HistorialTransacciones.AsignarHistorialTransacciones(ConsultaSaldo(cuentaUsuario));
                case 8 -> ConsularHistorico(HistorialTransacciones.ConsultarHistorialTransacciones());
            }
            break;
        }
    }

    public static String depositoSucursal(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valordepositotexto;
        double valordeposito;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        Scanner scanValordeposito = new Scanner(System.in);


        while (true) {
            System.out.println("Ingrese el valor del déposito");
            valordepositotexto = scanValordeposito.next();
            try {
                valordeposito = Double.parseDouble(valordepositotexto);
                resultado = cuentaUsuario.DepositoDesdeSucursal(new BigDecimal(valordeposito));
                historiaTransaccion = " Transacción número: " + consecutivo + "\n" +
                        " Fecha transacción: " + fechaTransaccion + "\n" +
                        " Cuenta: " + cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Déposoito desde sucursal" + "\n" +
                        " Valor transacción: " + valordeposito + "\n" +
                        " Costo : Sin costo\n" +
                        " Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String depositoDesdecajero(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valordepositotexto;
        double valordeposito;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        BigDecimal saldAnterior;
        Scanner scanValordeposito = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el valor del déposito");
            valordepositotexto = scanValordeposito.next();
            try {
                valordeposito = Double.parseDouble(valordepositotexto);
                saldAnterior = cuentaUsuario.consultarSado();
                resultado = cuentaUsuario.DepositoDesdecajeroAutomatico(new BigDecimal(valordeposito));
                historiaTransaccion = " Transacción número: " + consecutivo + "\n" +
                        " Fecha transacción: " + fechaTransaccion + "\n" +
                        " Cuenta: " + cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Déposoito desde cajero automático" + "\n" +
                        " Valor transacción: " + valordeposito + "\n" +
                        " Costo : " +
                         resultado.consultarCostoTransaccion() + "\n" +
                        "Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String depositoDesdeOtraCuenta(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valordepositotexto;
        double valordeposito;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        BigDecimal saldAnterior;
        Scanner scanValordeposito = new Scanner(System.in);


        while (true) {
            System.out.println("Ingrese el valor del déposito");
            valordepositotexto = scanValordeposito.next();
            try {
                valordeposito = Double.parseDouble(valordepositotexto);
                saldAnterior = cuentaUsuario.consultarSado();
                resultado = cuentaUsuario.DepositoDesdeOtrasCuentas(new BigDecimal(valordeposito));
                historiaTransaccion = " Transacción número: " +
                        consecutivo +
                        "\n" +
                        " Fecha transacción: " +
                        fechaTransaccion +
                        "\n" + " Cuenta: " +
                        cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Déposoito desde otra cuenta" +
                        "\n" + " Valor transacción: " +
                        valordeposito + "\n" +
                        " Costo : " +
                        resultado.consultarCostoTransaccion() + "\n" +
                        "Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String CompraEnEstablecimientoFisico(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valordepositotexto;
        double valordeposito;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        BigDecimal saldAnterior;
        Scanner scanValordeposito = new Scanner(System.in);


        while (true) {
            System.out.println("Ingrese el valor de la compra");
            valordepositotexto = scanValordeposito.next();
            try {
                valordeposito = Double.parseDouble(valordepositotexto);
                saldAnterior = cuentaUsuario.consultarSado();
                resultado = cuentaUsuario.CompraEnEstablecimientoFisico(new BigDecimal(valordeposito));
                historiaTransaccion = " Transacción número: " +
                        consecutivo + "\n" +
                        " Fecha transacción: " +
                        fechaTransaccion + "\n" +
                        " Cuenta: " +
                        cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Compra desde establecimiento fisico" +
                        "\n" + " Valor transacción: " +
                        valordeposito + "\n" + " Costo : Sin costo\n" +
                        "Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (
                    Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String CompraDesdeWeb(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valordepositotexto;
        double valordeposito;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        BigDecimal saldAnterior;
        Scanner scanValordeposito = new Scanner(System.in);


        while (true) {
            System.out.println("Ingrese el valor de la compra");
            valordepositotexto = scanValordeposito.next();
            try {
                valordeposito = Double.parseDouble(valordepositotexto);
                saldAnterior = cuentaUsuario.consultarSado();
                resultado = cuentaUsuario.CompraEnpaginaWeb(new BigDecimal(valordeposito));
                historiaTransaccion = " Transacción número: " +
                        consecutivo + "\n" +
                        " Fecha transacción: " +
                        fechaTransaccion + "\n" +
                        " Cuenta: " +
                        cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Compra desde sitio web" +
                        "\n" + " Valor transacción: " +
                        valordeposito + "\n" + " Costo : " +
                        resultado.consultarCostoTransaccion() + "\n" +
                        "Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (
                    Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String retirodesdecajero(Cuenta cuentaUsuario) {
        String historiaTransaccion;
        String valorretirotexto;
        double valorretiro;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        ResutadoTransaccion resultado;
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        BigDecimal saldAnterior;
        Scanner scanValordeposito = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese el valor del retiro");
            valorretirotexto = scanValordeposito.next();
            try {
                valorretiro = Double.parseDouble(valorretirotexto);
                saldAnterior = cuentaUsuario.consultarSado();
                resultado = cuentaUsuario.RetiroEnCajero(new BigDecimal(valorretiro));
                historiaTransaccion = " Transacción número: " +
                        consecutivo + "\n" +
                        " Fecha transacción: " +
                        fechaTransaccion + "\n" +
                        " Cuenta: " +
                        cuentaUsuario.consularNumeroCuenta() + "\n" +
                        " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                        " Transacción : Retiro desde cajero automático" +
                        "\n" + " Valor transacción: " +
                        valorretiro + "\n" + " Costo : " +
                        resultado.consultarCostoTransaccion() + "\n" +
                        "Resultado del proceso : " + resultado.consultarResultado() + "\n" +
                        "--------------------------------------------------------";
                break;

            } catch (Exception e) {
                System.out.println("Debe ingresar valores númericos");
            }

        }
        return historiaTransaccion;
    }

    public static String ConsultaSaldo(Cuenta cuentaUsuario){
        System.out.println("El saldo actual de su cuenta es de :" + cuentaUsuario.consultarSado());
        String historiaTransaccion;
        Date fechaTransaccion = new Date();
        Random random = new Random();
        int consecutivo = random.nextInt();
        if (consecutivo < 0) {
            consecutivo *= -1;
        }
        Cuenta cuenta1 = new CuentaBasica("1234", new BigDecimal(500));
        historiaTransaccion = " Transacción número: " +
                consecutivo + "\n" +
                " Fecha transacción: " +
                fechaTransaccion + "\n" +
                " Cuenta: " +
                 cuentaUsuario.consularNumeroCuenta() + "\n" +
                " Saldo actual : " + cuentaUsuario.consultarSado() + "\n" +
                " Transacción : Consulta saldo\n" +
                "--------------------------------------------------------";
        return historiaTransaccion;

    }

    public static void ConsularHistorico(ArrayList<String> historia){
        int posicionInicial;
        int posicionfinal = historia.size() - 1;
        if(posicionfinal >= 0) {
            if (posicionfinal <= 4) {
                posicionInicial = 0;
            } else {
                posicionInicial = posicionfinal - 5;
            }
            for (int i = posicionInicial; i<= posicionfinal; i++) {
                System.out.println(historia.get(i));
            }
        }

    }
}
