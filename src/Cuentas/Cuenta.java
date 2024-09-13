package Cuentas;

import java.math.BigDecimal;

public abstract class Cuenta {
       private BigDecimal saldo;
        private String nrocuenta;

        public Cuenta(BigDecimal saldo, String nrocuenta) {
            this.saldo = saldo;
            this.nrocuenta = nrocuenta;
        }


        public BigDecimal consultarSaldo() {
            return this.saldo;
        }
        public void colocarSaldo(BigDecimal saldo) {
            this.saldo = saldo;
        }
        public String consultarNrocuenta() {
            return this.nrocuenta;
        }
        public void colocarNrocuenta(String nrocuenta) {
            this.nrocuenta = nrocuenta;
        }

        public abstract void depositoSucursal (BigDecimal monto);
        public abstract void depositoCajero (BigDecimal monto);
        public abstract void depositoOtraCuenta (BigDecimal monto);
        public abstract void compraEstableFisico (BigDecimal monto);
        public abstract void compraPagWeb (BigDecimal monto);
        public abstract void retiroCajero (BigDecimal monto);

    }

