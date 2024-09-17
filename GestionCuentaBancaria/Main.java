import Cuentas.CuentaBasica;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        CuentaBasica cuentaAna = new CuentaBasica("BA123", new BigDecimal(100));


        Banco banco = new Banco();
        banco.agregarCuenta(cuentaAna);
        banco.mostrarMenu();


    }
}
